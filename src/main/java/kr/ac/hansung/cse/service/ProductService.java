package kr.ac.hansung.cse.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.domain.Product;
import kr.ac.hansung.cse.domain.ProductDto;
import kr.ac.hansung.cse.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TextTrans textTrans;
	
	@Transactional("jpatransactionManager")
	public List<Product> getAllProduct(Locale locale) {

		if (String.valueOf(locale).equals("ko")) {
			return productRepository.findAll();
		} else {
			return productRepository.findAllEn();
		}
		
		
	}
	@Transactional("jpatransactionManager")
	public void addProduct(ProductDto productDto, Locale locale) {
		
		Product product = new Product();
		
		if (String.valueOf(locale).equals("ko")) {

			String en_name = textTrans.translateEn(productDto.getName());
			String en_category = textTrans.translateEn(productDto.getCategory());
			String en_manufacture = textTrans.translateEn(productDto.getManufacture());
			String en_des = textTrans.translateEn(productDto.getDescription());

			product.setName(productDto.getName());
			product.setCategory(productDto.getCategory());
			product.setDescription(productDto.getDescription());
			product.setManufacture(productDto.getManufacture());
			product.setEn_name(en_name);
			product.setEn_category(en_category);
			product.setEn_description(en_des);
			product.setEn_manufacture(en_manufacture);
			product.setPrice(productDto.getPrice());
			product.setUnitInStock(productDto.getUnitInStock());

		} else {
			String ko_name = textTrans.translateKo(productDto.getName());
			String ko_category = textTrans.translateKo(productDto.getCategory());
			String ko_manufacture = textTrans.translateKo(productDto.getManufacture());
			String ko_des = textTrans.translateKo(productDto.getDescription());

			product.setEn_name(productDto.getName());
			product.setEn_category(productDto.getCategory());
			product.setEn_description(productDto.getDescription());
			product.setEn_manufacture(productDto.getManufacture());
			product.setName(ko_name);
			product.setCategory(ko_category);
			product.setDescription(ko_des);
			product.setManufacture(ko_manufacture);
			product.setPrice(productDto.getPrice());
			product.setUnitInStock(productDto.getUnitInStock());
		}
		
		productRepository.save(product);
	}
	@Transactional("jpatransactionManager")
	public Product getProduct(int id) {

		return productRepository.findById(id);

	}
	@Transactional("jpatransactionManager")
	public void deleteProduct(Product product) {

		productRepository.delete(product);

	}
	@Transactional("jpatransactionManager")
	public void updateProduct(Product product,Locale locale) {
		
		if (String.valueOf(locale).equals("ko")) {
			
			String en_name = textTrans.translateEn(product.getName());
			String en_category = textTrans.translateEn(product.getCategory());
			String en_manufacture = textTrans.translateEn(product.getManufacture());
			String en_des = textTrans.translateEn(product.getDescription());
			
			product.setEn_name(en_name);
			product.setEn_category(en_category);
			product.setEn_description(en_des);
			product.setEn_manufacture(en_manufacture);
	
		} else {
			
			String ko_name = textTrans.translateKo(product.getName());
			String ko_category = textTrans.translateKo(product.getCategory());
			String ko_manufacture = textTrans.translateKo(product.getManufacture());
			String ko_des = textTrans.translateKo(product.getDescription());
			
			product.setName(ko_name);
			product.setCategory(ko_category);
			product.setDescription(ko_des);
			product.setManufacture(ko_manufacture);
		}
		
		productRepository.save(product);

	}

}
