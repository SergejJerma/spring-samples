package eshop.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Atliekame servleto inicializaciją paveldint ...Initializer klasę
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// Nurodome programos duomenų inicializavimo klasę kaip parametrą
		return new Class[] { RootApplicationContextConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Nurodome mūsų implementuotą konfiguracinę klasę kaip parametrą
		return new Class[] { WebApplicationContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Nurodome pradžios užklausos susiejimą - request mapping'ą
		return new String[] { "/" }; // Galima pridėti /app/* - prefiksą
	}

	// Filtras naudojamas duomenų saugojimui su POST -> UTF8 formatu
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[] { characterEncodingFilter };
	}

}
