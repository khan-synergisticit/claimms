package com.synergistic.claimms.service.patient;

import com.synergistic.claimms.domain.Patient;
import com.synergistic.claimms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired private PatientRepository patientRepository;
    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(String patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    @Override
    public void deletePatient(String patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
