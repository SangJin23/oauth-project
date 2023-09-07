package com.sangjin.oauthback.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sangjin.oauthback.filter.JwtAuthenticationFilter;
import com.sangjin.oauthback.handler.OAuth2SuccesHandler;

import lombok.RequiredArgsConstructor;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final DefaultOAuth2UserService oAuth2UserService;
  private final OAuth2SuccesHandler oAuth2SuccesHandler;
  @Bean
  protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{

    httpSecurity   
      .cors().and()  
      .csrf().disable()
      .httpBasic().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests()
      .antMatchers("/", "/api/vi/auth/** ","/oauth2/**/").permitAll()
      .anyRequest().authenticated().and()
      .oauth2Login()
      .redirectionEndpoint().baseUri("/oauth2/callback/*").and()
      .authorizationEndpoint().baseUri("/api/vi/auth/social").and()
      .userInfoEndpoint().userService(oAuth2UserService).and()
      .successHandler(oAuth2SuccesHandler);

    httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build(); 
  }

}