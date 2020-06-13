package com.lab.jobportal.filter;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 72_00; // 2 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String JWT_CONTEXT_PATH = "/auth/webapi";
    public static final String USER_CONTEXT_PATH = "/users";
}
