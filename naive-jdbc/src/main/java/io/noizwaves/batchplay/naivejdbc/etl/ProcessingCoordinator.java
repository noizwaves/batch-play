package io.noizwaves.batchplay.naivejdbc.etl;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.source.GizmoRepository;
import io.noizwaves.batchplay.naivejdbc.etl.source.WidgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.transform.GizmoToGadgetTransformer;
import io.noizwaves.batchplay.naivejdbc.etl.transform.WidgetToGadgetTransformer;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProcessingCoordinator {

    private final WidgetRepository widgetRepository;
    private final GizmoRepository gizmoRepository;
    private final WidgetToGadgetTransformer widgetToGadgetTransformer;
    private final GizmoToGadgetTransformer gizmoToGadgetTransformer;
    private final GadgetRepository gadgetRepository;

    public ProcessingCoordinator(
            WidgetRepository widgetRepository,
            GizmoRepository gizmoRepository,
            WidgetToGadgetTransformer widgetToGadgetTransformer,
            GizmoToGadgetTransformer gizmoToGadgetTransformer,
            GadgetRepository gadgetRepository
    ) {
        this.widgetRepository = widgetRepository;
        this.gizmoRepository = gizmoRepository;
        this.widgetToGadgetTransformer = widgetToGadgetTransformer;
        this.gizmoToGadgetTransformer = gizmoToGadgetTransformer;
        this.gadgetRepository = gadgetRepository;
    }

    public int runGadgetEtl() {
        return Stream.concat(
                widgetRepository.findAll().stream()
                        .map(widgetToGadgetTransformer::transform),
                gizmoRepository.findAll().stream()
                        .map(gizmoToGadgetTransformer::transform)
        ).mapToInt(g -> {
            gadgetRepository.insert(g);
            return 1;
        }).sum();
    }
}
