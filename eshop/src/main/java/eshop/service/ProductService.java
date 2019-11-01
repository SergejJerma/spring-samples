package eshop.service;

import java.util.List;
import java.util.Map;

import eshop.domain.Product;

public interface ProductService {
	List<Product> getAllProducts();

	void updateAllStock();

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductById(String productID);

	void addProduct(Product product);

}