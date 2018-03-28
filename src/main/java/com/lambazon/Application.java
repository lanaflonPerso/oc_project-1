package com.lambazon;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.lambazon.controller.CustomerController;
import com.lambazon.domain.Customer;
import com.lambazon.repository.CustomerRepository;
import com.lambazon.repository.ProductRepository;

/**
 * 
 * @author stanlick
 *
 */

@SpringBootApplication
public class Application implements  WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Internationalization (i18n)

			@Bean
			public LocaleResolver localeResolver() {
				SessionLocaleResolver localeResolver = new SessionLocaleResolver();
				localeResolver.setDefaultLocale(Locale.US);
				return localeResolver;
			}
		
			/**
			 * As all of our requests in the project are stateless, we must know, with each
			 * incoming request that what locale should be served for this request. To
			 * accomplish this, we need to define a LocaleChangeInterceptor bean
			 * 
			 * Apart from adding this bean, we also need to tell Spring to add this
			 * interceptor at request level for all incoming requests.
			 */
			
			   @Bean
			   public LocaleChangeInterceptor localeChangeInterceptor() {
			       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
			       lci.setParamName("lang");
			       return lci;
			   }
			 
			   @Override
			   public void addInterceptors(InterceptorRegistry registry) {
			       registry.addInterceptor(localeChangeInterceptor());
			   }
	   
	// Initialize database
			   
			   @Inject
			   API api;
			   
				@PostConstruct
				public void init() {
					api.deleteAll();
					
					// Customers
					Customer customer = api.createCustomer("Donald Trump");
					//customer.setAddress(...);
					//customer.setPhoneNumber(...);
					api.createCustomer("Scott Stanlick");
					
					
					//Products
					api.createProduct(10, 92.50, "Echo Dot", "(2nd Generation) - Black");
					api.createProduct(20, 9.99, "Anker 3ft / 0.9m Nylon Braided", "Tangle-Free Micro USB Cable");
					api.createProduct(30, 69.99, "JVC HAFX8R Headphone", "Riptidz, In-Ear");
					api.createProduct(40, 32.50, "VTech CS6114 DECT 6.0", "Cordless Phone");
					api.createProduct(50, 895.00, "NOKIA OEM BL-5J", "Cell Phone");
					
					
				}	   
	
}
