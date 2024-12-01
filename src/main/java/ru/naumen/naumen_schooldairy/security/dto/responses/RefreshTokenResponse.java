package ru.naumen.naumen_schooldairy.security.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.naumen.naumen_schooldairy.security.entity.Role;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponse {
    private String accessToken;
    private String email;
    private Role role;
}
