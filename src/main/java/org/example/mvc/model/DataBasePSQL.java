package org.example.mvc.model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBasePSQL {

    private static volatile DataBasePSQL instance;
    private final HikariDataSource dataSource;

    private DataBasePSQL() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/mvc");
        config.setUsername("admin");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.dataSource = new HikariDataSource(config);
    }


    public static DataBasePSQL getInstance() {
        if (instance == null) {
            synchronized (DataBasePSQL.class) {
                if (instance == null) {
                    instance = new DataBasePSQL();
                }
            }
        }
        return instance;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

}
