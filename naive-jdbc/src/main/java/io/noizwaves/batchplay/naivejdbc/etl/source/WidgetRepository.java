package io.noizwaves.batchplay.naivejdbc.etl.source;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WidgetRepository {

    private final JdbcTemplate jdbcTemplate;

    public WidgetRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<WidgetRecord> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM source.widget",
                (rs, i) -> new WidgetRecord(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("unit_price")
                )
        );
    }
}
