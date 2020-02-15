package com.cts.training.mavenweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.mavenweb.entity.Product;
import com.cts.training.mavenweb.exception.StudentErrorResponse;
import com.cts.training.mavenweb.exception.StudentNotFoundException;
import com.cts.training.mavenweb.services.IProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductRestController {
	
	// dependency
	@Autowired
	private IProductService productService;
	
	// @RequestMapping(value =  "/students", method = {RequestMethod.GET, RequestMethod.PUT} )
	@GetMapping("/products") // GET HTTP VERB
	public ResponseEntity<List<Product>> exposeAll() {
		
		List<Product> products = this.productService.findAllProducts();
		// if(students.size() == 0)
		if(products == null)
			throw new StudentNotFoundException("Not able to fetch records!!!");
		ResponseEntity<List<Product>> response = 
								new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		
		
		return response;
	}
	
	// {<data variable>}
	@GetMapping("/products/{productId}") // GET HTTP VERB
	public ResponseEntity<Product> getById(@PathVariable Integer productId) {
		
		Product product = this.productService.findProductById(productId);
		if(product == null)
			throw new StudentNotFoundException("Student with id-" + productId + " not Found");
		
		ResponseEntity<Product> response = 
				new ResponseEntity<Product>(product, HttpStatus.OK);

		return response;
	}
	
	// @RequestMapping(value =  "/students", method = RequestMethod.POST)
	@PostMapping("/products") // POST HTTP VERB
	public ResponseEntity<Product> save(@RequestBody Product product) {
		if(!this.productService.addProduct(product))
			throw new RuntimeException("Could not add new record!!!");
		ResponseEntity<Product> response = 
				new ResponseEntity<Product>(product, HttpStatus.OK);

		return response;
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> saveUpdate(@PathVariable Integer productId,@RequestBody Product product) {
		Product p = new Product(productId,product.getName(),product.getCategory(),product.getCost());
		if(!this.productService.updateProduct(p))
			throw new RuntimeException("Could not update record!!!");
		ResponseEntity<Product> response = 
				new ResponseEntity<Product>(product, HttpStatus.OK);

		return response;
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> delete(@PathVariable Integer productId) {
		
		Product product = this.productService.findProductById(productId);
		if(product == null)
			throw new StudentNotFoundException("Student with id-" + productId + " not Found");
		
		// send studentId to DAO via SERVICE
		this.productService.deleteProduct(productId);
		
		ResponseEntity<Product> response = 
				new ResponseEntity<Product>(product, HttpStatus.OK);

		return response;
	}
	
	/*
	// for exception handling
	@ExceptionHandler  // ~catch
	public ResponseEntity<StudentErrorResponse> studentNotFoundHandler(StudentNotFoundException ex) {
		// create error object
		StudentErrorResponse error = new StudentErrorResponse(ex.getMessage(), 
															  HttpStatus.NOT_FOUND.value(), 
															  System.currentTimeMillis());
		ResponseEntity<StudentErrorResponse> response =
										new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<StudentErrorResponse> studentOperationErrorHAndler(Exception ex) {
		// create error object
		StudentErrorResponse error = new StudentErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<StudentErrorResponse> response =
										new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
		
		return response;
	}*/
	
	
	
	/************ REST endpoints ************/
	// /api/student [GET]
	// /api/student/id [GET]
	// /api/student [POST]
	// /api/student [PUT]
	// /api/student/id [DELETE]
	
	
	
	
}
