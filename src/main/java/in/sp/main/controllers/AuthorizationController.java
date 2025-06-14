package in.sp.main.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.dtos.LoginRequest;
import in.sp.main.dtos.OtpRequest;
import in.sp.main.dtos.RegisterRequest;
import in.sp.main.service.AuthService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerWithOtp(request));
    }
    // --- Send OTP ---
    @PostMapping("/request-otp-registration")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        return ResponseEntity.ok(authService.generateAndSendOtpForRegistration(request.getEmail()));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request, HttpSession session) {
        String result = authService.loginUser(request);

        if (result.toLowerCase().contains("login successful")) {
            // ✅ Store email in session
            session.setAttribute("email", request.getEmail());
        }

        return ResponseEntity.ok(result);
    }

    
}






