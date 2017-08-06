package io.noizwaves.batchplay.naivejdbc.etl.transform;

import io.noizwaves.batchplay.naivejdbc.etl.destination.GadgetRecord;
import io.noizwaves.batchplay.naivejdbc.etl.source.GizmoRecord;
import org.springframework.stereotype.Service;

@Service
public class GizmoToGadgetTransformer {
    public GadgetRecord transform(GizmoRecord gizmo) {
        return new GadgetRecord(
                gizmo.getCreatorName() + " (" + gizmo.getCreatorType() + ")",
                "$" + gizmo.getCost()
        );
    }
}
