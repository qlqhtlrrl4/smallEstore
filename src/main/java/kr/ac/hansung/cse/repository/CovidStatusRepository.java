package kr.ac.hansung.cse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.ac.hansung.cse.domain.CovidStatusData;

public interface CovidStatusRepository extends JpaRepository<CovidStatusData, Long> {
	
	@Modifying
	@Query(value = "truncate table vaccination", nativeQuery = true)
	void deleteAll();
	
}
