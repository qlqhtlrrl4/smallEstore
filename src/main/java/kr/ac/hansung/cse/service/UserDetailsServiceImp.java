package kr.ac.hansung.cse.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.domain.Role;
import kr.ac.hansung.cse.domain.User;
import kr.ac.hansung.cse.domain.UserDto;
import kr.ac.hansung.cse.repository.RoleRepository;
import kr.ac.hansung.cse.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImp implements CustomUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		System.out.println(userRepository.findById(id));
		
		User user = userRepository.findById(id);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}

		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),
				grantedAuthorities);
	}

	@Transactional("jpatransactionManager")
	public void save(UserDto memberDto) {

		memberDto.setPassword(encoder.encode(memberDto.getPassword()));

		User member = new User();
		member.setPassword(memberDto.getPassword());
		member.setId(memberDto.getId());
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
		userRepository.save(member);

		Role role = new Role();
		role.setUser(member);
		role.setRole("ROLE_USER");
		roleRepository.save(role);

	}

	@PostConstruct
	@Transactional("jpatransactionManager")
	public void initialize() {
		User admin = userRepository.findAdmin("admin");

		String password = encoder.encode("123456789");

		if (admin == null) {

			admin = new User();
			admin.setId("admin");
			admin.setEmail("admin@test");

			admin.setName("admin");
			admin.setPassword(password);
			System.out.println(admin);
			userRepository.save(admin);

			Role role = new Role();
			role.setUser(admin);
			role.setRole("ROLE_ADMIN");
			roleRepository.save(role);

		}
	}
}
