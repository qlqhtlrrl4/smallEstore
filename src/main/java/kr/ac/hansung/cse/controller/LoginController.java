package kr.ac.hansung.cse.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PropertySource("classpath:db/props/recaptcha.properties")
public class LoginController {
	
	/*@Autowired
	private Environment env;*/

	@RequestMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		if (error != null) {
			model.addAttribute("errorMsg", "Invalid username and password");

		}

		if (logout != null) {
			model.addAttribute("logoutMsg", "You have been logged out successfully");
		}

		return "/login.do";
	}

}
