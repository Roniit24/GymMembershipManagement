package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Payment;

public interface PaymentService
{
	Payment savePayment(Payment payment);
	List<Payment>getAllPayments();
	Payment getPaymentById(Long id);
	void deletePayment(Long id);
	 Payment findLatestPaymentByEmail(String email);

}
