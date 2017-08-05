package io.noizwaves.batchplay.naivejdbc.etl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GadgetRepository {

    private final JdbcTemplate jdbcTemplate;

    public GadgetRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(GadgetRecord gadget) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", gadget.getName());
        parameters.put("price", gadget.getPrice());

        new SimpleJdbcInsert(jdbcTemplate)
                .withSchemaName("destination")
                .withTableName("gadget")
                .usingGeneratedKeyColumns("id")
                .execute(parameters);
    }
}
