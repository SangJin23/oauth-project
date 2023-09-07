package com.sangjin.oauthback.service;

import org.springframework.http.ResponseEntity;

import com.sangjin.oauthback.dto.request.auth.SignUpRequestDto;
import com.sangjin.oauthback.dto.response.auth.SignUpResponseDto;

public interface AuthService {

  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);


}
