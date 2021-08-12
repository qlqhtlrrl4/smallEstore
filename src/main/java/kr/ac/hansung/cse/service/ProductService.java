package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDao;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	public Product getProduct(int id) {
		
		return productDao.getProduct(id);
	}
	
	public List<Product> getAllProduct() {
		
		/*List<Product> products = productDao.getAllProduct();*/
		
		return productDao.getAllProduct();
	}


	public void addProduct(Product product) {
		
		productDao.addProduct(product);
	}


	public void deleteProduct(Product product) {
		
		productDao.deleteProduct(product);
		
	}


	public void updateProduct(Product product) {
		
		productDao.updateProduct(product);
	}

}
