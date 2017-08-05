package io.noizwaves.batchplay.naivejdbc.etl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GadgetTransformerTest {

    @InjectMocks
    private GadgetTransformer transformer;

    @Test
    public void testTransform() throws Exception {
        GadgetRecord result = transformer.transform(new WidgetRecord(8, "Foo", new BigDecimal("1.23")));

        assertThat(result, equalTo(new GadgetRecord("Foo", "$1.23")));
    }
}