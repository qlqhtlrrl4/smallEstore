package kr.ac.hansung.cse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.service.ApiRestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ApiRestService restService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "/home.do";
		
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> test() throws Exception {
		
		
		restService.callApi();
		//restService.vaccionCallApi();
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
