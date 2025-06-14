package in.sp.main.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.sp.main.entities.Payment;
import in.sp.main.entities.User;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.PaymentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
	    String email = (String) session.getAttribute("email");

	    if (email == null) {
	        return "redirect:/login.html"; // session expired or not logged in
	    }

	    Optional<User> userOptional = userRepository.findByEmail(email);

	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        model.addAttribute("name", user.getName());
	        return "dashboard";
	    } else {
	        model.addAttribute("error", "User not found.");
	        return "error"; // Optional: add error.html
	    }
	}
	 @GetMapping("/my-plan")
	    public String showCurrentUserPlan(HttpSession session, Model model) {
	        String userEmail = (String) session.getAttribute("email");

	        if (userEmail != null) {
	            Payment payment = paymentService.findLatestPaymentByEmail(userEmail);

	            if (payment != null) {
	                model.addAttribute("planName", payment.getPlanName());
	                model.addAttribute("amount", payment.getAmountPaid());
	                model.addAttribute("duration", payment.getDuration());
	                model.addAttribute("paymentMode", payment.getPaymentMode());
	                model.addAttribute("activationDate", payment.getActivationDate());
	            } else {
	                model.addAttribute("noPlan", true);
	            }
	        }
	        return "myPlan";
	    }
	 @GetMapping("/index")
	 public String openIndex() {
		 	return "index";
	 }
}
	
