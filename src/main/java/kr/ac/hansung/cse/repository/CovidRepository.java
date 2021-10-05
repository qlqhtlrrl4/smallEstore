package kr.ac.hansung.cse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.domain.CovidData;


@Repository
public interface CovidRepository extends JpaRepository<CovidData, Integer>{
	
	@Modifying
	@Query(value = "truncate table covidhospital", nativeQuery = true)
	void deleteAll(); 
	
	

}
