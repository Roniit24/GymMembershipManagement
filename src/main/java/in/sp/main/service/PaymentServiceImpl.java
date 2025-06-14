package in.sp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Payment;
import in.sp.main.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	public PaymentRepository repo;

	@Override
	public Payment savePayment(Payment payment)
	{
		return repo.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return repo.findAll();
	}

	@Override
	public Payment getPaymentById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void deletePayment(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Payment findLatestPaymentByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findTopByEmailOrderByIdDesc(email);
	}

}
