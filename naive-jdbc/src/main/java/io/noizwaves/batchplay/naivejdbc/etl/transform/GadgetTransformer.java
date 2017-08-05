package io.noizwaves.batchplay.naivejdbc.etl.transform;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRecord;
import io.noizwaves.batchplay.naivejdbc.etl.source.WidgetRecord;
import org.springframework.stereotype.Service;

@Service
public class GadgetTransformer {
    public GadgetRecord transform(WidgetRecord widget) {
        return new GadgetRecord(
                widget.getName(),
                "$" + widget.getUnitPrice().toString()
        );
    }
}
