package com.cts.training.mavenweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cts.training.mavenweb.dao.IProductDao;
import com.cts.training.mavenweb.entity.Product;

// @Component
@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	@Qualifier("productDaoHibernateImpl")
	// @Qualifier("studentDaoJdbcTemplateImpl")
	private IProductDao productDao;
	
	@Override
	public List<Product> findAllProducts() {
		// add additional logic
		return this.productDao.findAll();
	}

	@Override
	public Product findProductById(Integer id) {
		// TODO Auto-generated method stub
		return this.productDao.findById(id);
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return this.productDao.add(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return this.productDao.update(product);
	}

	@Override
	public boolean deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		return this.productDao.delete(id);
	}

}
