package handlers;

import datastore.DataStore;
import io.javalin.http.Handler;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;


public class PatientHandler {
    public static Handler getAllPatients = ctx -> {
        ctx.json(DataStore.patients);
    };

    public static Handler getPatientById = ctx -> {
        String id = ctx.pathParam("patientId");
        var patient = DataStore.patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (patient != null) {
            ctx.json(patient);
        } else {
            ctx.status(404).result("Patient not found");
        }
    };
}