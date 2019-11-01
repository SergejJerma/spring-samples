package eshop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.domain.Product;
import eshop.domain.repository.ProductRepository;
import eshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock() {
		// Iš DB paimami visi produktai
		List<Product> allProducts = productRepository.getAllProducts();

		for (Product product : allProducts) {
			// Jeigu produkto kieks sandėlyje < 500
			if (product.getUnitsInStock() < 500) {
				// Keičiame produkto kiekį kviesdami produktų repozitorijos metodą
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
			}
		}

	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	@Override
	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
