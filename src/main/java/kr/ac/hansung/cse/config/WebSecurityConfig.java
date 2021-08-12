package kr.ac.hansung.cse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");

		http.authorizeRequests().antMatchers("/admin/**").access("ROLE_ADMIN").antMatchers("/**").permitAll()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated().and().formLogin()// Form 로그인 인증 기능이 작동함
				.loginPage("/login.jsp")// 사용자 정의 로그인 페이지
				.defaultSuccessUrl("/home")// 로그인 성공 후 이동 페이지
				.failureUrl("/login?error")// 로그인 실패 후 이동 페이지
				.usernameParameter("username")// 아이디 파라미터명 설정
				.passwordParameter("password")// 패스워드 파라미터명 설정
				.loginProcessingUrl("/login");// 로그인 Form Action Url

	}

}