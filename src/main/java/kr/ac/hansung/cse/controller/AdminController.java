package kr.ac.hansung.cse.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.domain.Product;
import kr.ac.hansung.cse.domain.ProductDto;
import kr.ac.hansung.cse.service.ProductDtoValidator;
import kr.ac.hansung.cse.service.ProductService;
import kr.ac.hansung.cse.service.ProductValidator;


@Controller
@RequestMapping("/admin") // class level
public class AdminController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDtoValidator dtoValidator;
	
	@Autowired
	private ProductValidator  validator;
	

	
	@RequestMapping // method level
	public String adminPage() {

		return "/admin.do";
	}

	@RequestMapping("/productInventory") // class level에 admin이 있어서 /admin/productInventory 라고 안해도됌
	public String getProducts(Model model, Locale locale) { // controller -> model
		
		
		
		List<Product> products = productService.getAllProduct(locale);
		model.addAttribute("products", products);
		return "/productInventory.do";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProduct(Model model) {

		ProductDto product = new ProductDto();

		model.addAttribute("product", product);

		return "/addProduct.do";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	/*public String addProductPost(Locale locale, @Valid ProductDto product, BindingResult result) {
	 */
	public String addProductPost(Locale locale, @ModelAttribute("product")ProductDto productDto, BindingResult result) {

		dtoValidator.validate(productDto, result);

		if (result.hasErrors()) {
			System.out.println(result);
			
			
			return "/addProduct.do";
		}

		productService.addProduct(productDto, locale);

		// 재고현황을 다시 보기위해서 바로 productInventory 때리면 products 값이 안넘어가서 아무것도안뜸
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable int id) {

		Product product = productService.getProduct(id);

		productService.deleteProduct(product);

		return "redirect:/admin/productInventory.do";
	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateProduct(@PathVariable int id, Model model) {

		Product product = productService.getProduct(id);

		model.addAttribute("product", product);

		return "/updateProduct.do";
	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@PathVariable int id,Locale locale,Product product, BindingResult result) {
		
		validator.validate(product, result);
		
		if (result.hasErrors()) {
			
			return "/productInventory/updateProduct.do/"+id;
		}
		
		productService.updateProduct(product,locale);

		return "redirect:/admin/productInventory.do";
	}

}
