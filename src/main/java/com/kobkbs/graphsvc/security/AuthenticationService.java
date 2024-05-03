package com.kobkbs.graphsvc.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationService {

  private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEYA";
  private static final String AUTH_TOKEN = "6qWpNYkRWDT7tbop1DTaNUDWacOOl9pwRRi3hiuoPDLKirDIXWbproFyMxIp4dv8Nrqhjqtz9G3BEsFhNl3yCHuWqrXUVXaMwwiCVhDrKx6MYHwRp0o4lt8xMXozLqQBX8jDCBuVFGRdSrYplt6EzI4pL5auCrqTneajtUMrQQd6oWo8ijdgZabM5nv2Cj4GPfcsvhr6HiYREAk7HdK2I2UZTE8wuYd1GjVGoyV8g1quqKs0mgUzAxUfPa16v0WP ";

  public static Authentication getAuthentication(HttpServletRequest request) {
    String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
    if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
      throw new BadCredentialsException("Invalid API Key Provided");
    }

    return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
  }
}
