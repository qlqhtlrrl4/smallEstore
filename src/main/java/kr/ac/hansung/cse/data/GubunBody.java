package kr.ac.hansung.cse.data;

import java.util.List;

import kr.ac.hansung.cse.domain.CovidGubunData;
import lombok.Data;

@Data
public class GubunBody {
	
	private List<CovidGubunData> items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
	
	

}
