package kr.ac.hansung.cse.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import kr.ac.hansung.cse.domain.UserDto;

public interface CustomUserDetailsService extends UserDetailsService {
	public void save(UserDto userDto);
}
