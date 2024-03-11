package handlers;

import datastore.DataStore;
import dto.PatientDTO;
import io.javalin.http.Handler;

import java.util.List;
import java.util.stream.Collectors;



public class PatientHandler {
    public static Handler getAllPatients = ctx -> {
        List<PatientDTO> patientDTOs = DataStore.patients.stream()
                .map(patient -> new PatientDTO(patient.getId(), patient.getName(), patient.getSpecies()))
                .collect(Collectors.toList());
        ctx.json(patientDTOs);
    };
    public static Handler getPatientById = ctx -> {
        String id = ctx.pathParam("patientId");
        var patient = DataStore.patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(p -> new PatientDTO(p.getId(), p.getName(), p.getSpecies()))
                .orElse(null);

        if (patient != null) {
            ctx.json(patient);
        } else {
            ctx.status(404).result("Patient not found");
        }
    };
}