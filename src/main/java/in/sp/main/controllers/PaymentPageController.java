package in.sp.main.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entities.Payment;
import in.sp.main.entities.User;
import in.sp.main.service.AuthService;
import in.sp.main.service.EmailService;
import in.sp.main.service.PaymentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentPageController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    // ✅ Submit Payment (POST)
    @PostMapping("/submit-payment")
    public String handlePaymentSubmission(@ModelAttribute Payment payment, HttpSession session) {
        String userEmail = (String) session.getAttribute("email");

        // ✅ Session Check
        if (userEmail == null) {
            return "redirect:/"; // Or redirect:/login if that exists
        }

        payment.setEmail(userEmail);

        System.out.println("Saving Payment:");
        System.out.println("Plan: " + payment.getPlanName());
        System.out.println("Amount: " + payment.getAmountPaid());
        System.out.println("Duration: " + payment.getDuration());
        System.out.println("Mode: " + payment.getPaymentMode());
        System.out.println("Email: " + payment.getEmail());

        User user = authService.findByEmail(userEmail);

        if (user == null) {
            return "redirect:/";
        }

        // ✅ Save with error handling
        try {
            paymentService.savePayment(payment);
            System.out.println("✅ Payment saved to DB successfully.");
        } catch (Exception e) {
            System.out.println("❌ Error saving payment: " + e.getMessage());
            return "redirect:/error";
        }

        // ✅ Send Invoice
        String userName = user.getName();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        String invoice = "Dear " + userName + ",\n\n"
                + "Thank you for your payment and for being a valued member of BodyTuning GYM**! 🏋️‍♂️\n\n"
                + "📄 *Your Payment Details:*\n"
                + "-------------------------------------\n"
                + "📅 Date: " + currentDate + "\n"
                + "📋 Plan: " + payment.getPlanName() + "\n"
                + "💸 Amount Paid: ₹" + payment.getAmountPaid() + "\n"
                + "⏳ Duration: " + payment.getDuration() + " month(s)\n"
                + "💳 Payment Mode: " + payment.getPaymentMode() + "\n"
                + "-------------------------------------\n\n"
                + "We are excited to be part of your fitness journey and are committed to helping you achieve your goals.\n\n"
                + "Tip: Consistency is the key to success. Keep showing up and the results will follow!\n\n"
                + "Thank you for choosing BodyTuning Gym.\n"
                + "Stay strong, stay healthy! \n\n"
                + "Warm regards,\n"
                + "Team BodyTuning Gym";

        emailService.sendInvoiceEmail(userEmail, "🏋️ Your BodyTuning Gym Invoice", invoice);

        return "redirect:/paymentsucces";
    }

    // ✅ Show Payment Success Page (GET)
    @GetMapping("/paymentsucces")
    public String showPaymentSuccessPage(HttpSession session) {
        if (session.getAttribute("email") == null) {
            return "redirect:/"; // block access after logout
        }
        return "paymentsucces";
    }
}
