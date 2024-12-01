package ru.naumen.naumen_schooldairy.security.service;

import org.springframework.http.ResponseEntity;
import ru.naumen.naumen_schooldairy.security.dto.requests.*;
import ru.naumen.naumen_schooldairy.security.dto.responses.RegisterResponse;


public interface AuthenticationService {
      ResponseEntity<RegisterResponse> registerUser(RegisterRequest registerRequest);
      ResponseEntity<?> verifyUserRegistration(RegisterVerifyRequest registerVerifyRequest);
      ResponseEntity<?> loginUser(LoginRequest loginRequest);
      ResponseEntity<?> resendOtp(ForgotPasswordRequest forgotPasswordRequest);
      ResponseEntity<?> verifyOtp(RegisterVerifyRequest registerVerifyRequest);
      ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest);
     ResponseEntity<?> myProfile(ForgotPasswordRequest forgotPasswordRequest);


}
