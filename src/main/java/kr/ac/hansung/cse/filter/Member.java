package kr.ac.hansung.cse.filter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class Member implements UserDetails {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Lid;

	@Column(name = "user_id", nullable = false, updatable = false, unique = true)
	//@Size(min = 6, max = 15, message = "id는 최소6글자 최대15글자로 적어주세요")
	private String id;

	//@NotEmpty(message = "The Email must not be null")
	//@Email(message = "이메일 형식으로 적어주세요")
	private String email;

	//@NotEmpty(message = "비밀번호를 적어주세요")

	//@Size(min = 8, max = 20, message = "pw는 최소 8글자 최대 20글자로 적어주세요.")
	private String password;

	//@NotEmpty(message = "이름을 적어주세요")

	//@Size(min = 2, max = 5, message = "이름은 2~5글자로 적어주세요")
	private String name;
	private String auth;
	
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "role")
	private Role role;*/
	
	@Builder
	public Member(String id, String email, String password, String name, String auth) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
	    for (String role : auth.split(",")) {
	      roles.add(new SimpleGrantedAuthority(role));
	    }
	    return roles;
		/*return this.auth;*/
	}

	@Override
	public String getUsername() { // TODO Auto-generated method stub
		return id;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { // TODO Auto-generated method stub
		return true;
	}


}
