package kr.ac.hansung.cse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.cse.filter.Member;


public interface UserRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findById(String id);

	
}

