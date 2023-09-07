package com.sangjin.oauthback.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sangjin.oauthback.dto.request.auth.SignUpRequestDto;
import com.sangjin.oauthback.dto.response.auth.SignUpResponseDto;
import com.sangjin.oauthback.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  
  private final AuthService authService;

  @PostMapping("sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
  @RequestBody @Valid SignUpRequestDto requestBody
  ){
    ResponseEntity<? super SignUpResponseDto> response =
    authService.signUp(requestBody);
    return response;
  }
}
 