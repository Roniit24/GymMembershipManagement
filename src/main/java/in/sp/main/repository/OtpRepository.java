package in.sp.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
	Optional<Otp>findByEmailAndOtpCode(String email,String otpCode);
	void deleteByEmail(String email);

}
