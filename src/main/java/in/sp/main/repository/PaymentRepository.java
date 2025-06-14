package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{
	 Payment findTopByEmailOrderByIdDesc(String email);
}
