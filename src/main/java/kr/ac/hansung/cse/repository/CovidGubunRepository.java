package kr.ac.hansung.cse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kr.ac.hansung.cse.domain.CovidGubunData;

public interface CovidGubunRepository extends JpaRepository<CovidGubunData, Long>{
	
	@Modifying
	@Query(value = "truncate table covidgubun", nativeQuery = true)
	void deleteAll();

}
