package com.shilymily.bms.payload;

import lombok.Data;

/**
 * Created by hmaskai on 12/19/18.
 */
@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
