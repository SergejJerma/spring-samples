package eshop.domain.repository;

import java.util.List;
import java.util.Map;

import eshop.domain.Product;

public interface ProductRepository {
	List<Product> getAllProducts();

	void updateStock(String productId, long noOfUnits);

	List<Product> getProductsByCategory(String category);

	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	Product getProductById(String productID);

	void addProduct(Product product);

}
