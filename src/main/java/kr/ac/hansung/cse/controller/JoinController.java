package kr.ac.hansung.cse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.domain.UserDto;
import kr.ac.hansung.cse.service.CustomUserDetailsService;

@Controller
public class JoinController {
	
	@Autowired
	@Qualifier("userDetailsService")
	private CustomUserDetailsService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String doJoin(Model model) {

		UserDto userInfomation = new UserDto();
		model.addAttribute("userInfomation", userInfomation);
		

		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String doJoin(@ModelAttribute("userInfomation")@Valid UserDto userInfomation, BindingResult result) {
		
		if(result.hasErrors()) {
			return "join";
		}
		
		userService.save(userInfomation);
		
		return "redirect:/login"; 
	
	}
}
