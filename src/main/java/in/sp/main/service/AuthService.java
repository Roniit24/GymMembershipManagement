package in.sp.main.service;

import in.sp.main.dtos.LoginRequest;
import in.sp.main.dtos.RegisterRequest;
import in.sp.main.entities.User;

public interface AuthService {
	String generateAndSendOtpForRegistration(String email);
    String registerWithOtp(RegisterRequest request);
	User findByEmail(String userEmail);   
	String loginUser(LoginRequest request);
    
}
