package kr.ac.hansung.cse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //ai(auto increment)
	@Column(name="product_id", nullable=false , updatable=false)
	private int id;
	
	@NotEmpty(message="The product name must not be null")
	private String name;
	
	
	private String category;
	
	@Min(value=0, message="The product price must not be less than zero")
	private int price;
	
	@NotEmpty(message="The product name must not be null")
	private String manufacture;
	
	@Min(value=0, message="The product price must not be less than zero")
	private int unitInStock;
	
	private String description;
	
	//hibernate = jpa+ native (entity는 javax.persistence에 있는)

}