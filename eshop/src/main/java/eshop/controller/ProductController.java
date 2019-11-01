package eshop.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import eshop.domain.Product;
import eshop.service.ProductService;
import eshop.validator.UnitsInStockValidator;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private UnitsInStockValidator unitsInStockValidator;


	@RequestMapping("/products")
	public String productList(Model model) {

		model.addAttribute("produktai", productService.getAllProducts());

		/*
		 * // Kuriamas Produktas klasės objektas Product telefonas = new Product("P1234", "iPhone 6s", new BigDecimal(500));
		 * 
		 * telefonas.setDescription("Apple iPhone 6s išmanusis telefonas, su 4 colių raiškiu ekranu ir labai gera, 8 megapikselių kamera"); telefonas.setCategory("Telefonas");
		 * telefonas.setManufacturer("Apple"); telefonas.setUnitsInStock(1000);
		 * 
		 * // Objektas kaip parametras perduodamas į view model.addAttribute("produktas", telefonas);
		 */

		return "Products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		// Atliekamas nukreipimas į /products mappingą
		return "redirect:/market/products";
	}

	@RequestMapping("/all")
	public ModelAndView allProducts() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produktai", productService.getAllProducts());
		modelAndView.setViewName("Products");
		return modelAndView;
	}

	// Metodas surandą produktą pagal kategoriją kurią naudoja paieškai {category}
	@RequestMapping("/products/{category}")
	// @PathVariable nurodo kuris parametras yra kategorijos perduodamas
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		// SQL užklausa grąžiną sąrašą produktų pagal kategoriją
		model.addAttribute("produktai", productService.getProductsByCategory(productCategory));
		// Naudojamas tas pats kontroleris atvaizduoti sąrašą
		return "Products";
	}

	// Matrix kintamieji parametrai
	@RequestMapping("/products/filter/{params}")
	// http://localhost:8080/eshop/market/products/filter/params;brands=Google,Dell;categories=Tablet,Laptop
	// http://localhost:8080/eshop/market/products/filter/params;brands=Google;brands=Dell;categories=Tablet;categories=Laptop
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model) {
		model.addAttribute("produktai", productService.getProductsByFilter(filterParams));
		return "Products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	// GET rodomas tada kai kviečiame URL /products/add
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}

	// POST suveikia po mygtuko paspaudimo
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request) {

		if(result.hasErrors()) {
			return "addProduct";
		}
		
		// Atliekamas patikrinimas ar nebuvo bandymų subindinti laukus kurie draudžiami susieti
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		// Pasiimame nuotrauką iš modelio objekto
		MultipartFile productImage = newProduct.getProductImage();
		// Priskiriame šakninį servleto katalogą kaip kelią
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		// Jeigu nuotrauka buvo sėkmingai gauta iš modelio
		if (productImage != null && !productImage.isEmpty()) {
			try {
				// Bandome nuotrauką išsaugoti kaip failą į failinę sistemą formuojant pavadinimą pagal produkto ID
				productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}

	// Inicializuojama WebDataBinder objektas - bean'as
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		// Nurodome kuriuos objekto laukus šiame leisime susieti automatiškai
//		binder.setValidator(unitsInStockValidator);
		binder.setAllowedFields("productId", "name", "unitPrice", "description", "manufacturer", "category", "unitsInStock", "condition", "productImage", "language");
	}

}
