package kr.ac.hansung.cse.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Product;

// jdbc -> spring jdbc -> hibernate

@Repository
@Transactional // method들이 transaction으로 수행된다-> begin transaction / commit --> aop의해서 자동적으로 
public class ProductDao {
	
	/*@Autowired*/
	private SessionFactory sessionFactory; //SessionFactory hibernate에 고유함

	public Product getProduct(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product)session.get(Product.class, id);
		
		return product;
			
	}

	public List<Product> getAllProduct() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Product";
		
		Query<Product> query = session.createQuery(hql,Product.class);
		List<Product>productList = query.getResultList();
		
		return productList;

	}

	public void addProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(product);
		session.flush(); // 꼭 flush 할 필요가없다.
	
	}

	public void deleteProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
	}
	
	public void updateProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		
	}
}
