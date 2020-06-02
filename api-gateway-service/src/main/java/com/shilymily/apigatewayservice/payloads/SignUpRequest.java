package com.shilymily.apigatewayservice.payloads;

/**
 * Created by hmaskai
 * 5/25/20.
 */
public class SignUpRequest {
  private String userName;
  private String password;

  public SignUpRequest() {
  }

  public SignUpRequest(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
