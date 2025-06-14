package in.sp.main.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.dtos.LoginRequest;
import in.sp.main.dtos.RegisterRequest;
import in.sp.main.entities.Otp;
import in.sp.main.entities.User;
import in.sp.main.repository.OtpRepository;
import in.sp.main.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private static final long OTP_EXPIRY_MINUTES = 5;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    // OTP for registration (only for new users)
    @Transactional
    @Override
    public String generateAndSendOtpForRegistration(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists. Please login instead.";
        }

        // generate OTP
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        // delete previous OTPs
        otpRepository.deleteByEmail(email);

        // save new OTP
        Otp otp = new Otp(email, otpCode, expiry);
        otpRepository.save(otp);

        // send OTP
        emailService.sendOtpEmail(email, otpCode);

        return "Registration OTP sent to email";
    }

   

    // Registration with OTP verification
    @Override
    public String registerWithOtp(RegisterRequest request) {
    	String email = request.getEmail();
        String otpCode = request.getOtp();

        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists.";
        }

        Optional<Otp> otpOpt = otpRepository.findByEmailAndOtpCode(email, otpCode);
        if (otpOpt.isEmpty()) return "Invalid OTP";
        if (otpOpt.get().getExpiryTime().isBefore(LocalDateTime.now())) {
            return "OTP expired";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(email);
        user.setPassword(request.getPassword());
        user.setVerified(true);

        userRepository.save(user);
        otpRepository.delete(otpOpt.get());

        return "User registered and verified successfully";
    }



	@Override
	public User findByEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(userEmail).orElse(null);
    }
	@Override
	public String loginUser(LoginRequest request) {
	    String email = request.getEmail();
	    String password = request.getPassword();

	    Optional<User> userOpt = userRepository.findByEmail(email);
	    if (userOpt.isEmpty()) {
	        return "User not found";
	    }

	    User user = userOpt.get();

	    if (!user.getPassword().equals(password)) {
	        return "Invalid password";
	    }

	    if (!user.isVerified()) {
	        return "User not verified. Please verify via registration OTP.";
	    }

	    return "Login successful";
	}

}
