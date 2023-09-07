package com.sangjin.oauthback.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sangjin.oauthback.dto.request.auth.SignUpRequestDto;
import com.sangjin.oauthback.dto.response.ResponseDto;
import com.sangjin.oauthback.dto.response.auth.SignUpResponseDto;
import com.sangjin.oauthback.entity.UserEntity;
import com.sangjin.oauthback.repository.UserRepository;
import com.sangjin.oauthback.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthServiceImplement implements AuthService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
    
    SignUpResponseDto result = null;

    String id = dto.getId();

    try {
      
      boolean hasId = userRepository.existsById(id);
      if(hasId) return SignUpResponseDto.existedId();

      UserEntity userEntity = new UserEntity(dto);
      userRepository.save(userEntity);

    } catch (Exception exception) {
     exception.printStackTrace();
     return ResponseDto.databaseError(); 
    }

    return SignUpResponseDto.success();

  }
  
}
