package handlers;

import datastore.DataStore;
import io.javalin.http.Handler;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;


public class AppointmentHandler {

    public static Handler getAllAppointments = ctx -> {
        ctx.json(DataStore.appointments);
    };

    public static Handler getAppointmentById = ctx -> {
        // This logic needs to be inside the handler for getting an appointment by ID
        String id = ctx.pathParam("appointmentId");
        var appointment = DataStore.appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (appointment != null) {
            ctx.json(appointment);
        } else {
            ctx.status(404).result("Appointment not found");
        }
    };
}