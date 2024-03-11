package datastore;

import org.example.Appointment;
import org.example.Patient;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static List<Appointment> appointments = new ArrayList<>();
    public static List<Patient> patients = new ArrayList<>();

    static {
        // Initialize with some mock data
        patients.add(new Patient("101", "Fluffy", "Cat"));
        appointments.add(new Appointment("1", "101", "2024-03-15", "Annual check-up"));
    }
}