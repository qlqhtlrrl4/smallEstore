package kr.ac.hansung.cse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "vaccination")
public class VaccinationData {
	
	@Id
	@Column(name = "vaccin_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int accumulatedFirstCnt; //전일까지 누적 통계1차
	private int accumulatedSecondCnt; //전일까지  누적 통계 2차
	private String baseDate; //통계일자
	private int firstCnt; // 당일 통계(1차)
	private int secondCnt; // 당일 통계 (2차)
	private String sido; // 지역명칭
	private int totalFirstCnt; //전체 누적 통계1차
	private int totalSecondCnt; //전체 누적 통계 2차
	
	

}

