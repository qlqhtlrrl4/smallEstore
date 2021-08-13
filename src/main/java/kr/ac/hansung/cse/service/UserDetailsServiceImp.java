package kr.ac.hansung.cse.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.model.Member;
import kr.ac.hansung.cse.model.MemberDto;
import kr.ac.hansung.cse.repository.UserRepository;

@ComponentScan("kr.ac.hansung.cse.config")
@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userDao;

	/*
	 * @Autowired(required=false) private void setUserRepository(UserRepository
	 * userDao) { this.userDao = userDao; }
	 */

	@Override
	public Member loadUserByUsername(String id) throws UsernameNotFoundException {

		return userDao.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
	}
	
	@Transactional
	public void save(MemberDto memberDto) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		memberDto.setPassword(encoder.encode(memberDto.getPassword()));

		Member member = new Member();

		
		member.setId(memberDto.getId());
		member.setEmail(memberDto.getEmail());
		member.setPassword(memberDto.getPassword());
		member.setName(memberDto.getName());
		member.setAuth(memberDto.getAuth());

		System.out.println(member);

		userDao.save(member);

	}
}
