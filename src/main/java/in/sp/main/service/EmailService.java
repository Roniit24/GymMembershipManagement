package in.sp.main.service;

public interface EmailService {
	void sendInvoiceEmail(String toEmail, String subject, String body);
	void sendOtpEmail(String toEmail, String otp);
}
