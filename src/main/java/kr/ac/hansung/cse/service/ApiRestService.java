package kr.ac.hansung.cse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.data.CovidHospital;
import kr.ac.hansung.cse.data.CovidStatusResponse;
import kr.ac.hansung.cse.data.GubunResponse;
import kr.ac.hansung.cse.data.VaccinationStatus;
import kr.ac.hansung.cse.domain.CovidData;
import kr.ac.hansung.cse.domain.CovidGubunData;
import kr.ac.hansung.cse.domain.CovidStatusData;
import kr.ac.hansung.cse.domain.VaccinationData;
import kr.ac.hansung.cse.repository.CovidGubunRepository;
import kr.ac.hansung.cse.repository.CovidRepository;
import kr.ac.hansung.cse.repository.CovidStatusRepository;
import kr.ac.hansung.cse.repository.VaccinRepository;
import kr.ac.hansung.cse.util.RestClient;
import lombok.extern.slf4j.Slf4j;

@Service
@PropertySource("classpath:db/props/jdbc.properties")
@Slf4j
public class ApiRestService {

	@Autowired
	private CovidRepository covidRepository;

	@Autowired
	private VaccinRepository vaccinRepository;

	@Autowired
	private CovidStatusRepository covidStatusRepository;
	
	@Autowired
	private CovidGubunRepository covidGubunRepository;

	private List<CovidData> covidList;
	private List<VaccinationData> vaccinList;
	private List<CovidStatusData> itemList;
	private List<CovidGubunData> gubunList;

	@Value("${api.covidHospitalKey}")
	private String covidHospitalKey;

	@Value("${api.covidstatusKey}")
	private String covidstatusKey;

	@Value("${api.vaccinationKey}")
	private String vaccinationKey;
	
	@Value("${api.covidGubunKey}")
	private String gubunKey;

	private Date date = new Date();

	private static final Logger logger = LoggerFactory.getLogger(ApiRestService.class);
	private static final String apiRestServiceTag = "------------------[[apiRestServiceTag]]-----------------";

	public void callApi() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String nowDate = dateFormat.format(date);

		logger.debug(apiRestServiceTag + "callApi");

		if (covidList == null) {
			covidList = new ArrayList<>();
		}

		if (vaccinList == null) {
			vaccinList = new ArrayList<>();
		}

		if (itemList == null) {
			itemList = new ArrayList<>();
		}
		
		if(gubunList == null) {
			gubunList = new ArrayList<>();
		}

		CovidHospital result = null;
		VaccinationStatus result2 = null;
		CovidStatusResponse parseResult = null;
		GubunResponse gubunResult = null;

		RestClient restClient = new RestClient();

		JSONObject jsonObject = new JSONObject();

		String hospitalUrl = covidHospitalKey;
		String vaccinUrl = vaccinationKey;
		String covidStatus = covidstatusKey + nowDate;
		String gubunUrl = gubunKey+ nowDate;

		result = restClient.call(HttpMethod.GET, hospitalUrl, jsonObject.toString(), CovidHospital.class);
		result2 = restClient.call(HttpMethod.GET, vaccinUrl, jsonObject.toString(), VaccinationStatus.class);
		parseResult = (CovidStatusResponse) restClient.parser(covidStatus,CovidStatusResponse.class);
		gubunResult = (GubunResponse) restClient.parser(gubunUrl,GubunResponse.class);

		if (covidList != null) {
			covidRepository.deleteAll();
		}

		if (vaccinList != null) {
			vaccinRepository.deleteAll();
		}

		if (itemList != null) {
			covidStatusRepository.deleteAll();
		}
		
		if(gubunList != null) {
			covidGubunRepository.deleteAll();
		}

		for (int i = 0; i < result.getData().size(); i++) {
			covidList.add(result.getData().get(i));
		}

		for (int i = 0; i < result2.getData().size(); i++) {
			vaccinList.add(result2.getData().get(i));
		}

		for (int i = 0; i < parseResult.getBody().getItems().size(); i++) {
			itemList.add(parseResult.getBody().getItems().get((parseResult.getBody().getItems().size() - 1) - i));

		}
		
		for(int i=0; i<gubunResult.getBody().getItems().size();i++) {
			gubunList.add(gubunResult.getBody().getItems().get((gubunResult.getBody().getItems().size()-1)-i));
		}

		logger.debug(apiRestServiceTag + "CovidData saveAll Start");

		covidRepository.saveAll(covidList);

		logger.debug(apiRestServiceTag + "CovidData saveAllEnd");
		// covidRepository.saveAll(covidList);

		logger.debug(apiRestServiceTag + "vaccinationData saveAll Start");
		vaccinRepository.saveAll(vaccinList);
		logger.debug(apiRestServiceTag + "vaccinationData saveAll End");

		logger.debug(apiRestServiceTag + "covidStatusData saveAll Start");
		covidStatusRepository.saveAll(itemList);
		logger.debug(apiRestServiceTag + "covidStatusData saveAll End");
		
		logger.debug(apiRestServiceTag + "covidGubunRepository saveAll Start");
		covidGubunRepository.saveAll(gubunList);
		logger.debug(apiRestServiceTag + "covidGubunRepository saveAll End");

		// System.out.println(vaccinRepository.findFirstChart());

	}

	public List<Map<String, Object>> getVaccinData() {

		return vaccinRepository.findFirstChart();
	}

	public List<CovidStatusData> getStatusData() {
		// TODO Auto-generated method stub
		return covidStatusRepository.findAll();
	}

	public List<Map<String, Object>> getLeastDay() {

		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -13);
		
		String endNowDate = dateFormat.format(cal.getTime());
		
		String startNowDate = dateFormat.format(date);*/
		
		
		return covidStatusRepository.leastDayData();
	}

	public List<CovidStatusData> getLeastDayData() {
		// TODO Auto-generated method stub
		return covidStatusRepository.leastDayFullData();
	}

}
