package io.noizwaves.batchplay.naivejdbc.etl.transform;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRecord;
import io.noizwaves.batchplay.naivejdbc.etl.source.GizmoRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GizmoToGadgetTransformerTest {
    @InjectMocks
    private GizmoToGadgetTransformer transformer;

    @Test
    public void testTransform() throws Exception {
        GadgetRecord result = transformer.transform(new GizmoRecord(1, "Foob", "AR", 22));


        assertThat(result, equalTo(new GadgetRecord("Foob (AR)", "$22")));
    }
}