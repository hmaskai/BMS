package com.shilymily.apigatewayservice.payloads;

/**
 * Created by hmaskai
 * 5/24/20.
 */

public class AuthenticationResponse {

  private final String jwt;

  public AuthenticationResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }
}
