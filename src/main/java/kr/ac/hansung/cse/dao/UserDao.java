package kr.ac.hansung.cse.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.cse.domain.User;


public interface UserDao extends JpaRepository<User, Long>{
	
	Optional<User> findById(String id);

	
}
