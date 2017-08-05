package io.noizwaves.batchplay.naivejdbc.etl;

import io.noizwaves.batchplay.naivejdbc.etl.testhelpers.RepositoryTestHelpers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class WidgetRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    private WidgetRepository repository;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource = RepositoryTestHelpers.testDataSource();
        RepositoryTestHelpers.truncateDB(dataSource);

        jdbcTemplate = new JdbcTemplate(dataSource);
        repository = new WidgetRepository(dataSource);
    }

    @Test
    public void testFindAll() throws Exception {
        jdbcTemplate.update("INSERT INTO widget (id, name, unit_price) VALUES (1, 'Foo', 12.34)");
        jdbcTemplate.update("INSERT INTO widget (id, name, unit_price) VALUES (77, 'Bar', 777.70)");


        List<WidgetRecord> records = repository.findAll();


        assertThat(records, containsInAnyOrder(
                new WidgetRecord(1, "Foo", new BigDecimal("12.34")),
                new WidgetRecord(77, "Bar", new BigDecimal("777.70"))
        ));
    }
}