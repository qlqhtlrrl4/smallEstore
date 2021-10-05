package kr.ac.hansung.cse.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db/props/jdbc.properties")
public class FlywayConfig {

	/*	
	private static final String resourcePath = "classpath:db/migrate";
    private static final String dataSourceUrl = "jdbc:mysql://localhost:50001/estore?characterEncoding=UTF-8&rewriteBatchedStatements=true&serverTimezone=Asia/Seoul";
    private static final String username = "root";
    private static final String password = "1234";*/
	
	@Value("${flyway.resourcePath}")
	private String resourcePath;
	
	@Value("${jdbc.url}")
    private String dataSourceUrl;
	
	@Value("${jdbc.username}")
    private String username;
	
	@Value("${jdbc.password}")
    private String password;

    @Bean("flyway")
    public Flyway flyway() {

        FluentConfiguration fluentConfiguration = Flyway.configure().dataSource(dataSourceUrl, username, password);

        fluentConfiguration.locations(resourcePath);

        Flyway flyway = fluentConfiguration.load();

        //flyway.clean();
        flyway.migrate();

        return flyway;
    }
	
}
