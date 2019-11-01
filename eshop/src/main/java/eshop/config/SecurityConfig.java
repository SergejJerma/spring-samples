package eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// Nustatomas login puslapis, vardo ir pass kintamieji
		httpSecurity.formLogin().loginPage("/login").usernameParameter("userId").passwordParameter("password");

		// Jei sėkmingai /nesėkmingai prisijungiama viskas siunčiama į login.jsp failą ir ten panaudojama IF'e
		httpSecurity.formLogin().defaultSuccessUrl("/market/products/add").failureUrl("/login?error");

		// Logout veiksmas
		httpSecurity.logout().logoutSuccessUrl("/login?logout");

		// Konfiguruojame URL nukreipimą jei prisijungti nepavyksta
		httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");

		// Koks vartotojas kokius puslapius pasiekia
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers("/**/add").access("hasRole('ADMIN')").antMatchers("/**/market/**").access("hasRole('USER')");

		httpSecurity.csrf().disable();
	}
}
