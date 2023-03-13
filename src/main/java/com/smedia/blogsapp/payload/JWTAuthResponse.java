package com.smedia.blogsapp.payload;

public class JWTAuthResponse {
	private String accessToken;
    private String tokenType = "Bearer";
    
	public JWTAuthResponse(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
	
	public JWTAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
    
    
}
