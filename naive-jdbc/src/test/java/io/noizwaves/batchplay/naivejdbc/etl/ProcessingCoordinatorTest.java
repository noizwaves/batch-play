package io.noizwaves.batchplay.naivejdbc.etl;

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
    private GadgetTransformer gadgetTransformer;

    @Mock
    private GadgetRepository gadgetRepository;

    @InjectMocks
    private ProcessingCoordinator coordinator;

    @Test
    public void testRunGadgetEtl() throws Exception {
        WidgetRecord widget1 = mock(WidgetRecord.class);
        WidgetRecord widget2 = mock(WidgetRecord.class);
        when(widgetRepository.findAll()).thenReturn(asList(widget1, widget2));

        GadgetRecord gadget1 = mock(GadgetRecord.class);
        when(gadgetTransformer.transform(widget1)).thenReturn(gadget1);
        GadgetRecord gadget2 = mock(GadgetRecord.class);
        when(gadgetTransformer.transform(widget2)).thenReturn(gadget2);


        int result = coordinator.runGadgetEtl();


        assertThat(result, equalTo(2));

        verify(gadgetRepository).insert(gadget1);
        verify(gadgetRepository).insert(gadget2);
    }
}