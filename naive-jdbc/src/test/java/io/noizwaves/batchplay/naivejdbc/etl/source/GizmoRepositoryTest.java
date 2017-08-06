package io.noizwaves.batchplay.naivejdbc.etl.source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.List;

import static io.noizwaves.batchplay.naivejdbc.etl.testhelpers.RepositoryTestHelpers.testDataSource;
import static io.noizwaves.batchplay.naivejdbc.etl.testhelpers.RepositoryTestHelpers.truncateDB;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GizmoRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private GizmoRepository repository;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource = testDataSource();
        truncateDB(dataSource);

        jdbcTemplate = new JdbcTemplate(dataSource);
        repository = new GizmoRepository(dataSource);
    }

    @Test
    public void testFindAll() throws Exception {
        jdbcTemplate.update("INSERT INTO source.gizmo (id, creator_name, creator_type, cost) VALUES (2, 'Foo', 'GG', 11)");
        jdbcTemplate.update("INSERT INTO source.gizmo (id, creator_name, creator_type, cost) VALUES (999, 'Bar', 'H', 4444)");


        List<GizmoRecord> records = repository.findAll();


        assertThat(records, containsInAnyOrder(
                new GizmoRecord(2, "Foo", "GG", 11),
                new GizmoRecord(999, "Bar", "H", 4444)
        ));
    }
}