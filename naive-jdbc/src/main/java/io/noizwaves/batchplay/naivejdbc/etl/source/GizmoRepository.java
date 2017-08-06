package io.noizwaves.batchplay.naivejdbc.etl.source;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GizmoRepository {

    private final JdbcTemplate jdbcTemplate;

    public GizmoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GizmoRecord> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM source.gizmo",
                (rs, i) -> new GizmoRecord(
                        rs.getInt("id"),
                        rs.getString("creator_name"),
                        rs.getString("creator_type"),
                        rs.getInt("cost")
                )
        );
    }
}
