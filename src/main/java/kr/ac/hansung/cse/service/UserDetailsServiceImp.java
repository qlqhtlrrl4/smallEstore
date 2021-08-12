package kr.ac.hansung.cse.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.UserDao;
import kr.ac.hansung.cse.model.Member;
import kr.ac.hansung.cse.model.MemberDto;



@Service("userDetailsService")

@ComponentScan("kr.ac.hansung.cse.config")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public Member loadUserByUsername(String id) throws UsernameNotFoundException {
		
		return userDao.findById(id).orElseThrow(()-> new UsernameNotFoundException(id));
	}
	
	
	public Long save(MemberDto memberDto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		memberDto.setPassword(encoder.encode(memberDto.getPassword()));
		
		return userDao.save(Member.builder().id(memberDto.getId())
				.email(memberDto.getEmail())
				.password(memberDto.getPassword())
				.name(memberDto.getName())
				.auth(memberDto.getAuth()).build()).getLid();
				
		
	}
}
