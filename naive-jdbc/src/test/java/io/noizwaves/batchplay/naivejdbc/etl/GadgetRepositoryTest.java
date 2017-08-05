package io.noizwaves.batchplay.naivejdbc.etl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static io.noizwaves.batchplay.naivejdbc.etl.testhelpers.RepositoryTestHelpers.testDataSource;
import static io.noizwaves.batchplay.naivejdbc.etl.testhelpers.RepositoryTestHelpers.truncateDB;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GadgetRepositoryTest {

    private JdbcTemplate jdbcTemplate;
    private GadgetRepository repository;

    @Before
    public void setUp() throws Exception {
        DataSource dataSource = testDataSource();
        truncateDB(dataSource);

        jdbcTemplate = new JdbcTemplate(dataSource);
        repository = new GadgetRepository(dataSource);
    }

    @Test
    public void testInsert() throws Exception {
        repository.insert(new GadgetRecord("Foo", "Bar"));


        Integer rowCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM gadget WHERE name = ? AND price = ?",
                Integer.class,
                "Foo",
                "Bar"
        );
        assertThat(rowCount, equalTo(1));
    }
}