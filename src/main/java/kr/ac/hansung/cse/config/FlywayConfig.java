package kr.ac.hansung.cse.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

	private static final String resourcePath = "classpath:db/migrate";

    private static final String dataSourceUrl = "jdbc:mysql://localhost:50001/estore?characterEncoding=UTF-8&rewriteBatchedStatements=true&serverTimezone=Asia/Seoul";
    private static final String username = "root";
    private static final String password = "1234";

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
