package handlers;

import datastore.DataStore;
import dto.AppointmentDTO;
import io.javalin.http.Handler;

import java.util.stream.Collectors;




public class AppointmentHandler {

    public static Handler getAllAppointments = ctx -> {
        var appointmentDTOs = DataStore.appointments.stream()
                .map(appointment -> new AppointmentDTO(
                        appointment.getId(),
                        appointment.getDate(),
                        appointment.getPatientId(),
                        appointment.getReason()))
                .collect(Collectors.toList());
        ctx.json(appointmentDTOs);
    };
    public static Handler getAppointmentById = ctx -> {
        String id = ctx.pathParam("appointmentId");
        var appointment = DataStore.appointments.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .map(a -> new AppointmentDTO(a.getId(), a.getDate(), a.getPatientId(), a.getReason()))
                .orElse(null);

        if (appointment != null) {
            ctx.json(appointment);
        } else {
            ctx.status(404).result("Appointment not found");
        }
    };
}