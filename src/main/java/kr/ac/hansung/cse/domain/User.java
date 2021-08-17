package kr.ac.hansung.cse.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Lid;

	@Column(name = "user_id", nullable = false, updatable = false, unique = true)
	private String id;

	private String email;
	
	private String password;

	
	private String name;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Role> roles;
	/*@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "role")
	private Role role;*/
	
	/*@Builder
	public Member(String id, String email, String password, String name, String auth) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.auth = auth;
	}*/

	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
	    for (String role : auth.split(",")) {
	      roles.add(new SimpleGrantedAuthority(role));
	    }
	    return roles;
		
	}*/

	/*@Override
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
	}*/


}
