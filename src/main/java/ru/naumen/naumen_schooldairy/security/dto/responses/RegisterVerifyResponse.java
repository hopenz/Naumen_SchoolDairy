package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVerifyResponse {
    private String accessToken ;
    private String refreshToken ;
    private String email ;
    private Role role;
    private boolean isVerified;
}
