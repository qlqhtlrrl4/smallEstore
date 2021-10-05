package kr.ac.hansung.cse.data;

import java.util.List;

import kr.ac.hansung.cse.domain.CovidStatusData;
import lombok.Data;

@Data
public class CovidStatusBody {
	
	private List<CovidStatusData> items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
	
	
	
}
