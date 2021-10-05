package kr.ac.hansung.cse.data;

import java.util.List;

import kr.ac.hansung.cse.domain.CityData;
import lombok.Data;

@Data
public class CityBody {
	
	private List<CityData> items;
	
	private int numOfRows;
	
	private int pageNo;
	
	private int totalCount;

}
