package io.noizwaves.batchplay.naivejdbc.etl;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRecord;
import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.source.GizmoRecord;
import io.noizwaves.batchplay.naivejdbc.etl.source.GizmoRepository;
import io.noizwaves.batchplay.naivejdbc.etl.source.WidgetRecord;
import io.noizwaves.batchplay.naivejdbc.etl.source.WidgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.transform.GizmoToGadgetTransformer;
import io.noizwaves.batchplay.naivejdbc.etl.transform.WidgetToGadgetTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProcessingCoordinatorTest {
    @Mock
    private WidgetRepository widgetRepository;

    @Mock
    private GizmoRepository gizmoRepository;

    @Mock
    private WidgetToGadgetTransformer widgetToGadgetTransformer;

    @Mock
    private GizmoToGadgetTransformer gizmoToGadgetTransformer;

    @Mock
    private GadgetRepository gadgetRepository;

    @InjectMocks
    private ProcessingCoordinator coordinator;

    @Test
    public void testRunGadgetEtl() throws Exception {
        WidgetRecord widget1 = mock(WidgetRecord.class);
        WidgetRecord widget2 = mock(WidgetRecord.class);
        when(widgetRepository.findAll()).thenReturn(asList(widget1, widget2));

        GizmoRecord gizmo1 = mock(GizmoRecord.class);
        GizmoRecord gizmo2 = mock(GizmoRecord.class);
        when(gizmoRepository.findAll()).thenReturn(asList(gizmo1, gizmo2));

        GadgetRecord gadget1 = mock(GadgetRecord.class);
        when(widgetToGadgetTransformer.transform(widget1)).thenReturn(gadget1);
        GadgetRecord gadget2 = mock(GadgetRecord.class);
        when(widgetToGadgetTransformer.transform(widget2)).thenReturn(gadget2);

        GadgetRecord gadget3 = mock(GadgetRecord.class);
        when(gizmoToGadgetTransformer.transform(gizmo1)).thenReturn(gadget3);
        GadgetRecord gadget4 = mock(GadgetRecord.class);
        when(gizmoToGadgetTransformer.transform(gizmo2)).thenReturn(gadget4);


        int result = coordinator.runGadgetEtl();


        assertThat(result, equalTo(4));

        verify(gadgetRepository).insert(gadget1);
        verify(gadgetRepository).insert(gadget2);
        verify(gadgetRepository).insert(gadget3);
        verify(gadgetRepository).insert(gadget4);
    }
}