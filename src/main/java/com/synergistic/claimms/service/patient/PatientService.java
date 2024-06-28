package com.synergistic.claimms.service.patient;

import com.synergistic.claimms.domain.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient updatePatient(Patient patient);
    Patient getPatient(String patientId);
    List<Patient> getAllPatients();
    void deletePatient(String patientId);
    Patient getPatientByEmail(String email);
}
