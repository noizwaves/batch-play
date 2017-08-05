package io.noizwaves.batchplay.naivejdbc.etl;

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
