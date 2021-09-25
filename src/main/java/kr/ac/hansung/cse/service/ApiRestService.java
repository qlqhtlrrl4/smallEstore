package kr.ac.hansung.cse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.data.CovidHospital;
import kr.ac.hansung.cse.data.Response;
import kr.ac.hansung.cse.data.VaccinationStatus;
import kr.ac.hansung.cse.domain.CovidData;
import kr.ac.hansung.cse.domain.CovidStatusData;
import kr.ac.hansung.cse.domain.VaccinationData;
import kr.ac.hansung.cse.repository.CovidRepository;
import kr.ac.hansung.cse.repository.CovidStatusRepository;
import kr.ac.hansung.cse.repository.VaccinRepository;
import kr.ac.hansung.cse.util.RestClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiRestService {

	@Autowired
	private CovidRepository covidRepository;

	@Autowired
	private VaccinRepository vaccinRepository;

	@Autowired
	private CovidStatusRepository covidStatusRepository;
	
	private List<CovidData> covidList;
	private List<VaccinationData> vaccinList;
	private List<CovidStatusData> itemList;

	private static final Logger logger = LoggerFactory.getLogger(ApiRestService.class);
	private static final String apiRestServiceTag = "------------------[[apiRestServiceTag]]-----------------";

	public void callApi() {
		
		logger.debug(apiRestServiceTag+"callApi");
		
		if (covidList == null) {
			covidList = new ArrayList<>();
		}

		if (vaccinList == null) {
			vaccinList = new ArrayList<>();
		}
		
		if(itemList ==null) {
			itemList = new ArrayList<>();
		}

		CovidHospital result = null;
		VaccinationStatus result2 = null;
		Response parseResult = null;
		
		RestClient restClient = new RestClient();

		JSONObject jsonObject = new JSONObject();
		String hospitalUrl = "https://api.odcloud.kr/api/apnmOrg/v1/list?page=1&perPage=100&serviceKey=A6i7Rbg0iMaTsVBak5wYXpvZZCBfQPWpq7s3gbFRHZn8YHVBpjkGTTpeF2qbJ3ZGcTATa3I11G4rpTiJWPwqcg==";
		String vaccinUrl = "https://api.odcloud.kr/api/15077756/v1/vaccine-stat?page=1&perPage=100&serviceKey=A6i7Rbg0iMaTsVBak5wYXpvZZCBfQPWpq7s3gbFRHZn8YHVBpjkGTTpeF2qbJ3ZGcTATa3I11G4rpTiJWPwqcg==";
		String covidStatus = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=A6i7Rbg0iMaTsVBak5wYXpvZZCBfQPWpq7s3gbFRHZn8YHVBpjkGTTpeF2qbJ3ZGcTATa3I11G4rpTiJWPwqcg==&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";

		result = restClient.call(HttpMethod.GET, hospitalUrl, jsonObject.toString(), CovidHospital.class);
		result2 = restClient.call(HttpMethod.GET, vaccinUrl, jsonObject.toString(), VaccinationStatus.class);
		parseResult = restClient.parser(covidStatus);
			

		if (covidList != null) {
			covidRepository.deleteAll();
		}

		if (vaccinList != null) {
			vaccinRepository.deleteAll();
		}
		
		if(itemList != null) {
			covidStatusRepository.deleteAll();
		}

		for (int i = 0; i < result.getData().size(); i++) {
			covidList.add(result.getData().get(i));
		}

		for (int i = 0; i < result2.getData().size(); i++) {
			vaccinList.add(result2.getData().get(i));
		}
		
		for (int i=0;i<parseResult.getBody().getItems().size();i++) {
			itemList.add(parseResult.getBody().getItems().get(i));
		}

		
		logger.debug(apiRestServiceTag+"CovidData saveAll Start");
		
		covidRepository.saveAll(covidList);
		
		logger.debug(apiRestServiceTag+"CovidData saveAllEnd");
		// covidRepository.saveAll(covidList);
		
		logger.debug(apiRestServiceTag+"vaccinationData saveAll Start");
		vaccinRepository.saveAll(vaccinList);
		logger.debug(apiRestServiceTag+"vaccinationData saveAll End");
		
		logger.debug(apiRestServiceTag+"covidStatusData saveAll Start");
		covidStatusRepository.saveAll(itemList);
		logger.debug(apiRestServiceTag+"covidStatusData saveAll End");
		
		//System.out.println(vaccinRepository.findFirstChart());

	}

	public List<Map<String, Object>> getVaccinData() {

		return vaccinRepository.findFirstChart();
	}

	public List<CovidStatusData> getStatusData() {
		// TODO Auto-generated method stub
		return covidStatusRepository.findAll();
	}

	

}
