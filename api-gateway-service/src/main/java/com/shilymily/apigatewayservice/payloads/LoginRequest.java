package com.shilymily.apigatewayservice.payloads;

/**
 * Created by hmaskai
 * 5/24/20.
 */


public class LoginRequest {

  private String userName;
  private String password;

  public LoginRequest() {
  }

  public LoginRequest(String userName, String password) {
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
