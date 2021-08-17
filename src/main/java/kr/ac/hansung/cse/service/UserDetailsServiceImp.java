package kr.ac.hansung.cse.service;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.domain.Member;
import kr.ac.hansung.cse.domain.MemberDto;
import kr.ac.hansung.cse.repository.UserRepository;

@ComponentScan("kr.ac.hansung.cse.config")
@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;


	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		System.out.println(userRepository.findById(id));
		
		/*Optional<Member> member = userDao.findById(id);
		System.out.println(member);
		
		UserBuilder builder = null;
		
		if(member != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(id);
			builder.authorities(member.get().getAuthorities());
			
			builder.password(member.get().getPassword());
			System.out.println(member.get().getAuthorities());
		}
		else {
			new UsernameNotFoundException("User not found.");
		}
		return builder.build();*/
		
		return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
	}
	
	@Transactional("jpatransactionManager")
	public void save(MemberDto memberDto) {
		
		memberDto.setPassword(encoder.encode(memberDto.getPassword()));
		
		Member member = new Member();
		member.setPassword(memberDto.getPassword());
		member.setId(memberDto.getId());
		member.setAuth(memberDto.getAuth());
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
		userRepository.save(member);
		
		
		
		//String id, String email, String password, String name, String auth
		/*return userRepository.save(Member.builder()
				.id(memberDto.getId())
				.email(memberDto.getEmail())
				.password(memberDto.getPassword())
				.name(memberDto.getName())
				.auth(memberDto.getAuth()).build()).getLid();*/
		
		
				
	}
	
	@PostConstruct
	@Transactional("jpatransactionManager")
	public void initialize() {
		Member admin = userRepository.findAdmin("admin");
		
		String password = encoder.encode("123456789");
		
		if(admin == null) {
			
//			admin = new Member("admin", "admin@test.com", password, "admin", "USER_ADMIN");
//			userDao.save(admin);
			
			admin = new Member();
			admin.setId("admin");
			admin.setEmail("admin@test");
			admin.setAuth("ROLE_ADMIN");
			admin.setName("admin");
			admin.setPassword(password);
			System.out.println(admin);
			userRepository.save(admin);
			
		}
	}
	/*	public void save(MemberDto memberDto) {

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
	*/
	
}
