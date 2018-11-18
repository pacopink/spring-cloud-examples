package com.neo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class DbConfig {

    @Bean(name="postgresDataSource")
    @ConfigurationProperties(prefix="postgres.datasource")
    public DataSource posgresDataSource() {
        DruidDataSource ds = new DruidDataSource();
        return ds;
    }

    @Bean(name="postgesJdbcTemplate")
    public NamedParameterJdbcTemplate postgresJdbcTemplate(@Qualifier("postgresDataSource")DataSource ds) {
        return new NamedParameterJdbcTemplate(ds);
    }


}
