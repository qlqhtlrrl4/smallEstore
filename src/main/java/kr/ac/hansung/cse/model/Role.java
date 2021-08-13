package kr.ac.hansung.cse.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {


	
	private Long id;
	
	@Id
	private String role;
	

}
