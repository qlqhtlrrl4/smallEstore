package kr.ac.hansung.cse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.captcha.CaptchaUtil;
import kr.ac.hansung.cse.domain.UserDto;
import kr.ac.hansung.cse.service.CustomUserDetailsService;
import kr.ac.hansung.cse.service.UserDtoValidator;

@Controller
public class JoinController {
	
	@Autowired
	@Qualifier("userDetailsService")
	private CustomUserDetailsService userService;
	
	@Autowired
	private UserDtoValidator userDtoValidator = new UserDtoValidator();
	
	@Autowired
	private HttpServletRequest req;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String doJoin(Model model) {

		UserDto userInfomation = new UserDto();
		model.addAttribute("userInfomation", userInfomation);
		

		return "/join.do";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String doJoin(@ModelAttribute("userInfomation")@Valid UserDto userInfomation, BindingResult result) {
		
		/*System.out.println(req.getParameter("answer"));
		System.out.println((String)req.getSession().getAttribute("captcha"));*/
		userDtoValidator.validate(userInfomation, result);
		
		if(result.hasErrors()) {
			return "/join.do";
		}	
		userService.save(userInfomation);
		
		return "redirect:/login.do"; 
	
	}
	
	@RequestMapping(value="/captchaImg")
	public void captchaImg(HttpServletResponse res) throws Exception {
		new CaptchaUtil().getImgCaptCha(req, res);
	}
	
	
	@RequestMapping(value="/captchaAudio")
	public void captchaAudio(HttpServletResponse res) throws Exception {
		
		new CaptchaUtil().getAudioCaptCha(req, res);
	}
	
	/*@ResponseBody
	@RequestMapping(value="/captchaAudio", method=RequestMethod.POST)
	public void captchaAudio(@RequestBody Audio audio) throws Exception {
		
		//System.out.println("audio");
		System.out.println(audio);
		//System.out.println("audio");
//		new CaptchaUtil().getAudioCaptCha(req, res);
	}*/
	
	/*@ResponseBody
	@RequestMapping(value="/captchaAudio", method=RequestMethod.POST)
	public String captchaAudio(@RequestBody Answer answer) throws Exception {
		
		
		System.out.println(answer);
		System.out.println(req.getSession().getAttribute("captcha"));  
		
		
		return "ok";

	}*/
}
