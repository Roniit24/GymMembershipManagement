package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendInvoiceEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("rohit4singh25@gmail.com"); // ✅ Should match spring.mail.username
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Invoice email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ Failed to send invoice email: " + e.getMessage());
            e.printStackTrace(); // Shows full error
        }
    }


	@Override
	public void sendOtpEmail(String toEmail, String otp) {
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);

	            helper.setTo(toEmail);
	            helper.setSubject("Your One-Time Password (OTP) - BodyTuning Gym");

	            String Msg = "<p>Dear User,</p>"
	                    + "<p>We received a request to verify your identity using a One-Time Password (OTP).</p>"
	                    + "<p>🔐 Your OTP is: <b>" + otp + "</b></p>"
	                    + "<p>Please enter this OTP in the application to complete your verification.<br>"
	                    + "This code is valid for the next 5 minutes and can only be used once.</p>"
	                    + "<p><strong>⚠️ Do not share this OTP with anyone. Our team will never ask for it.</strong></p>"
	                    + "<p>If you did not request this OTP, please disregard this email or contact our support team immediately.</p>"
	                    + "Best Regards,Rohit<br>"
	                    + "Security Team BodyTuning<br>"
	                    + "www.BodyTuning.com";

	            helper.setText(Msg, true); 

	            mailSender.send(message);

	            System.out.println("OTP email sent successfully to " + toEmail);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Failed to send OTP email: " + e.getMessage());
	        }
	    
	}
}
