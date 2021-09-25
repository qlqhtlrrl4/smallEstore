package kr.ac.hansung.cse.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.ac.hansung.cse.domain.Product;

@Service
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Product product = (Product) target;
		
		ValidationUtils.rejectIfEmpty(errors, "name","product.name.required");
		ValidationUtils.rejectIfEmpty(errors, "category", "product.category.required");
		
		ValidationUtils.rejectIfEmpty(errors, "manufacture", "product.manufacture.required");
		
		ValidationUtils.rejectIfEmpty(errors, "description", "product.description.required");
		
		int price = product.getPrice();
		int unitInStock = product.getUnitInStock();
		
		if(price<=0 || price >=1000000) {
			errors.rejectValue("price", "product.price.range.invalid");
		}
		
		if(unitInStock<=0 || unitInStock >=1000) {
			errors.rejectValue("unitInStock", "product.unitInStock.range.invalid");
		}
	}

}
