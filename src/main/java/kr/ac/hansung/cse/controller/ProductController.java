package kr.ac.hansung.cse.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.domain.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
public class ProductController { // controller-> service-> dao->db
	
	@Autowired
	private ProductService productService;
	
	
	
	@RequestMapping("/products")
	public String getAllProduct(Model model,Locale locale) {
		
		List<Product> products = productService.getAllProduct(locale);
		model.addAttribute("products",products);
		
		return "products"; // view's logical name
	}
}
