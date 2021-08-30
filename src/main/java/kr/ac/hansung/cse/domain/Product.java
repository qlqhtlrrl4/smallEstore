package kr.ac.hansung.cse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //ai(auto increment)
	@Column(name="product_id", nullable=false , updatable=false)
	private int id;
	
	//@NotEmpty(message="상품이름은 빈칸이면 안됩니다")
	private String name;
	
	private String en_name;
	
	private String category;
	
	private String en_category;
	
	//@Min(value=0, message="The product price must not be less than zero")
	private int price;
	
	//@NotEmpty(message="The product name must not be null")
	private String manufacture;
	
	private String en_manufacture;
	
	//@Min(value=0, message="The product price must not be less than zero")
	private int unitInStock;
	
	private String description;
	
	private String en_description;
	
	//hibernate = jpa+ native (entity는 javax.persistence에 있는)
	
}
