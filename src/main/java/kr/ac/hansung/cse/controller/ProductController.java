package kr.ac.hansung.cse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.cse.domain.CovidStatusData;
import kr.ac.hansung.cse.domain.Product;
import kr.ac.hansung.cse.service.ApiRestService;
import kr.ac.hansung.cse.service.ProductService;

@Controller
public class ProductController { // controller-> service-> dao->db

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ApiRestService restService;


	@RequestMapping("/products")
	public String getAllProduct(Model model, Locale locale) {

		List<Product> products = productService.getAllProduct(locale);
		model.addAttribute("products", products);
		
		return "/products.do"; // view's logical name
	}
	
	@GetMapping(value = "/covid",produces = "application/json; charset=utf8")
	@ResponseBody
	public List<Map<String, Object>> jsonData() {
		
		List<Map<String, Object>> vaccinList= new ArrayList<>();
		
		vaccinList = restService.getVaccinData();
		System.out.println(vaccinList);
		return vaccinList;
		
	}
	
	@GetMapping(value="/covidstatus", produces ="application/json; charset=utf8")
	@ResponseBody
	public List<CovidStatusData> statusJsonData() {
		
		List<CovidStatusData> statusDataList = new ArrayList<>();
		statusDataList = restService.getStatusData();
		
		return statusDataList;
	}
	
}

/* JSONArray vaccinArr = new JSONArray();
		//JSONObject list = new JSONObject();
	    
		for (int i=0;i<vaccinList.size();i++) {
			JSONObject vaccinObj = new JSONObject();
			vaccinObj.put("accumulatedFirstCnt", vaccinList.get(i).getAccumulatedFirstCnt());
			vaccinObj.put("sido", vaccinList.get(i).getSido());
			vaccinArr.put(vaccinObj);				
			//System.out.println(vaccinArr.get(i));		
		}		
		//System.out.println(vaccinArr);
		//String str = String.valueOf(vaccinArr);
		//System.out.println(str);
		
		
		return vaccinArr.toString(); */