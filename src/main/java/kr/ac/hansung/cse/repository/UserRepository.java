package kr.ac.hansung.cse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.domain.Member;

@Repository
public interface UserRepository extends JpaRepository<Member, Long>{
	//Member findMemberByMemberId(String id);
	Optional<Member> findById(String id);

	@Query("SELECT m FROM Member m  WHERE m.id = (:userId)")
	Member findAdmin(@Param("userId") String userId);
	
}

