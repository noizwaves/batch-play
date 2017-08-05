package io.noizwaves.batchplay.naivejdbc.etl;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.source.WidgetRepository;
import io.noizwaves.batchplay.naivejdbc.etl.transform.GadgetTransformer;
import org.springframework.stereotype.Service;

@Service
public class ProcessingCoordinator {

    private final WidgetRepository widgetRepository;
    private final GadgetTransformer gadgetTransformer;
    private final GadgetRepository gadgetRepository;

    public ProcessingCoordinator(
            WidgetRepository widgetRepository,
            GadgetTransformer gadgetTransformer,
            GadgetRepository gadgetRepository
    ) {
        this.widgetRepository = widgetRepository;
        this.gadgetTransformer = gadgetTransformer;
        this.gadgetRepository = gadgetRepository;
    }

    public int runGadgetEtl() {
        return widgetRepository.findAll().stream()
                .map(gadgetTransformer::transform)
                .mapToInt(g -> {
                    gadgetRepository.insert(g);
                    return 1;
                })
                .sum();
    }
}
