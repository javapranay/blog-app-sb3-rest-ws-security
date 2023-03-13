package com.smedia.blogsapp.service;

import com.smedia.blogsapp.payload.LoginDto;
import com.smedia.blogsapp.payload.RegisterDto;

public interface AuthService {
	String login(LoginDto loginDto);
	
	String register(RegisterDto registerDto);
}
