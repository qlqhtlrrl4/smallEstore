package kr.ac.hansung.cse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.ac.hansung.cse.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	
}
