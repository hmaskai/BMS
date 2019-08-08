package com.shilymily.bms.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by hmaskai on 12/19/18.
 */
@Data
public class LoginRequest {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}
