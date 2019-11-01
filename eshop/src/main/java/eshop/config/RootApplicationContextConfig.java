package eshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * @author Sandman^KFMF Čia yra sudėti prisijungimai prie duomenų bazės
 */
@Configuration
@ComponentScan("eshop")
public class RootApplicationContextConfig {

	// Formuojama DB iš nurodytų failų
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = dbBuilder.setType(EmbeddedDatabaseType.H2).addScript("sql/create-table.sql").addScript("sql/insert-data.sql").build();
		return db;
	}

	// Grąžinamas objektas ryšiui su DB
	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

}
