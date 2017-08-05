package io.noizwaves.batchplay.naivejdbc.etl.testhelpers;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RepositoryTestHelpers {
    public static DataSource testDataSource() {
        return new DataSourceBuilder(null)
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost/naivejdbc-test")
                .username("adam")
                .password("")
                .build();
    }

    public static void truncateDB(DataSource dataSource) {
        final String[] tablesToTruncate = new String[] {"source.widget", "destination.gadget"};
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        for (String tableName : tablesToTruncate) {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
        }
    }
}
