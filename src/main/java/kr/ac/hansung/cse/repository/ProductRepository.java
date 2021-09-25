package kr.ac.hansung.cse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.ac.hansung.cse.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.id = (:productId)")
	Product findById(@Param("productId") int id);

	
	@Query(value = "SELECT product_id, en_name as name, null as en_name,"
			+ "en_category as category,null as en_category,  price, en_manufacture as manufacture, null as en_manufacture,"
			+ "unitInStock, en_description as description,null as en_description"
			+ " FROM product", nativeQuery = true)
	List<Product> findAllEn(); 
	
	
		
}
