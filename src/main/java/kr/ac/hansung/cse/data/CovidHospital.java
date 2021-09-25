package kr.ac.hansung.cse.data;

import java.util.List;

import kr.ac.hansung.cse.domain.CovidData;
import lombok.Data;

@Data
public class CovidHospital {
	
	private int currentCount;
	private List<CovidData> data;
	private int matchCount;
	private int page;
	private int perPage;
	private int totalCount;

}
	