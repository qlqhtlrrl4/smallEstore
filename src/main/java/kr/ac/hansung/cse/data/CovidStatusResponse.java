package kr.ac.hansung.cse.data;

import lombok.Data;

@Data
public class CovidStatusResponse {
	
	private CovidStatusHeader header;
	
	private CovidStatusBody body;

}
