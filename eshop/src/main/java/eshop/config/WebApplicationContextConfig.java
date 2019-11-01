package eshop.config;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.UrlPathHelper;

import eshop.interceptor.ProcessingTimeLogInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("eshop")
public class WebApplicationContextConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// Reikia įjungti jei naudojame nestandartinius @RequestMapping'us
		configurer.enable();
	}

	// Pakeičiame nustatymus, kad iš URL nebūtu naikinamas kabliataškis
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	// Nustatomas resursų katalogo shortcut'as
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// ** reiškia, kad resursai ieškomi rekursiškai
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		// Konfiguruojame JSP failų paėmimo vietą ir galūnę
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class); // Nurodoma view klasė - JSP tipas
		resolver.setPrefix("/WEB-INF/views/"); // Dėl saugumo rekomenduojama view failus laikyti WEB-INF kataloge
		resolver.setSuffix(".jsp"); // Nurodoma view failų galūnė
		return resolver;
	}

	// Beanas registruojantis išorinio teksto failą
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages"); // Properties failo galūnė nėra reikalinga
		return resource;
	}

	// Beanas reikalingas failams įkelti į serverį - atskiria ar HTTP request'as yra multypart ar ne
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(10240000);
		return resolver;
	}

	// Žemiau eina du Beansai reikalingi JSON formatavimui
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		jsonView.setPrettyPrint(true);
		return jsonView;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		ArrayList<View> views = new ArrayList<>();
		views.add(jsonView());
		resolver.setDefaultViews(views);
		return resolver;
	}

	// Aktyvinamas loginimo interceptorius
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ProcessingTimeLogInterceptor());

		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);
	}

	// Beanas skirtas kalbos keitimui
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		return resolver;
	}
	
	// Kuriamas validatorius
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	// Validatorius aktyvinamas
	@Override
	public Validator getValidator(){
		return validator();
	}

}
