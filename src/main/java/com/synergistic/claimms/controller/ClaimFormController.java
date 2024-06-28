package com.synergistic.claimms.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.synergistic.claimms.domain.Claim.ClaimCharge;
import com.synergistic.claimms.domain.Claim.ClaimForm;
import com.synergistic.claimms.domain.Claim.ClaimStatus;
import com.synergistic.claimms.domain.PageInfo;
import com.synergistic.claimms.domain.Patient;
import com.synergistic.claimms.domain.Provider;
import com.synergistic.claimms.domain.documents.ClaimDocument;
import com.synergistic.claimms.domain.documents.DocumentType;
import com.synergistic.claimms.service.claimcharge.ClaimChargeService;
import com.synergistic.claimms.service.claimform.ClaimFormService;
import com.synergistic.claimms.service.patient.PatientService;
import com.synergistic.claimms.service.provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/claimForm/")
public class ClaimFormController {
    @Autowired
    private ClaimFormService claimFormService;
    @Autowired private PatientService patientService;
    @Autowired private ProviderService providerService;
    @Autowired private ClaimChargeService claimChargeService;
    private PageInfo pageInfo = new PageInfo(0, 10, "claimDate", "desc"); //PageInfo(int pageNo, int pageSize, String sortBy, String sortOrder)
    @RequestMapping("/save")
    public ResponseEntity<?> save(@RequestBody ClaimForm claimForm) {
        Patient patient;
        if(claimForm.getPatient().getId() != null ){
            patient = patientService.updatePatient(claimForm.getPatient());
            claimForm.setPatient(patient);
        } else {
            if(claimForm.getPatient().getEmail() != null ){
                patient = patientService.getPatientByEmail(claimForm.getPatient().getEmail());
                if(patient != null){
                    claimForm.setPatient(patient);
                }else{
                    patient = patientService.createPatient(claimForm.getPatient());
                    claimForm.setPatient(patient);
                }
            }else{
                if(claimForm.getMemberId() != null){
                    claimForm.getPatient().setId(claimForm.getMemberId());
                    patient = patientService.createPatient(claimForm.getPatient());
                    claimForm.setPatient(patient);
                }else {
                    patient = patientService.createPatient(claimForm.getPatient());
                    claimForm.setPatient(patient);
                }
            }

        }
        if(claimForm.getClaimCharge().getProvider().getId() == null || claimForm.getClaimCharge().getProvider().getId().isEmpty()){
            Provider provider;
            if(claimForm.getClaimCharge().getProvider().getEmail() != null){
                provider = providerService.findByEmail(claimForm.getClaimCharge().getProvider().getEmail());
            }else{
                if(claimForm.getClaimCharge().getProvider().getNetworkId()!=null){
                    claimForm.getClaimCharge().getProvider().setId(claimForm.getClaimCharge().getProvider().getNetworkId());
                     provider = providerService.saveProvider(claimForm.getClaimCharge().getProvider());
                    claimForm.getClaimCharge().setProvider(provider);
                }else {
                     provider = providerService.saveProvider(claimForm.getClaimCharge().getProvider());
                    claimForm.getClaimCharge().setProvider(provider);
                }
            }
            claimForm.getClaimCharge().setProvider(provider);


        }else {
            Provider provider;
            if(claimForm.getClaimCharge().getProvider().getEmail() != null){
                provider = providerService.findByEmail(claimForm.getClaimCharge().getProvider().getEmail());
            }else{
                provider = providerService.updateProvider(claimForm.getClaimCharge().getProvider());
            }
            claimForm.getClaimCharge().setProvider(provider);

        }

        ClaimCharge claimCharge = claimChargeService.createClaimCharge(claimForm.getClaimCharge());
        claimForm.setClaimCharge(claimCharge);
        claimForm.setClaimDocuments(createClaimDocuments());


        return new ResponseEntity<>(claimFormService.createClaimForm(claimForm), HttpStatus.OK);
    }

    @RequestMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClaimForm claimForm) {
        return new ResponseEntity<>(claimFormService.updateClaimForm(claimForm), HttpStatus.OK);
    }

    @RequestMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestParam String status, @RequestParam String claimId) {
        ClaimForm claimForm = claimFormService.getClaimFormById(claimId);
        for(ClaimStatus stats : ClaimStatus.values()){
            if(stats.name().equals(status)){
                claimForm.setClaimStatus(stats);
            }
        }
        return new ResponseEntity<>(claimFormService.updateClaimForm(claimForm), HttpStatus.OK);
    }

    @RequestMapping("/find")
    public ResponseEntity<?> findByPolicyId(@RequestParam String claimFormId) {
        return new ResponseEntity<>(claimFormService.getClaimFormByPolicy(claimFormId), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByStatus", method = RequestMethod.GET)
    private ResponseEntity<?> fetchByStatus(@RequestParam String status) throws IOException {
        System.out.println("Status: " + status);
        ClaimStatus claimStatus = null;
        for(ClaimStatus c : ClaimStatus.values()){
            if(status.equalsIgnoreCase(c.toString())){
                claimStatus = c;
            }
        }
        Page<ClaimForm> claimForms = claimFormService.findByStatus(claimStatus, pageInfo);
        return new ResponseEntity<>(claimForms.getContent(), HttpStatus.OK);
    }

    @RequestMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String claimFormId) {
        claimFormService.deleteClaimForm(claimFormId);
        return new ResponseEntity<>("Claim form deleted.", HttpStatus.OK);
    }
    @RequestMapping("/update/docs")
    public ResponseEntity<?> updateClaimDocuments(@RequestBody JsonNode docs) throws IOException {
        System.out.println("docs: " + docs.toString());
        String claimId = docs.get("claimId").asText();
        ClaimForm claimForm = claimFormService.getClaimFormById(claimId);
        JsonNode claimDocuments = docs.get("claimDocuments");
        List<ClaimDocument> documents = new ArrayList<>();
        if(claimDocuments.isArray()){
            for(JsonNode claimDoc : claimDocuments){
                ObjectMapper mapper = new ObjectMapper();
                ClaimDocument doc = mapper.readValue(claimDoc.toString(), ClaimDocument.class);
                documents.add(doc);
            }
        }
        List<ClaimDocument> retrievedDocs = claimForm.getClaimDocuments();
        for(ClaimDocument doc : documents){
            for(ClaimDocument retrievedDoc : retrievedDocs){
                if(Objects.equals(doc.getType(), retrievedDoc.getType())&&doc.getUrl()!=null){
                    retrievedDoc.setUrl(doc.getUrl());
                    if(doc.getAccepted() != null){
                        retrievedDoc.setAccepted(doc.getAccepted());
                    }

                }
            }
        }

        claimForm.setClaimDocuments(retrievedDocs);
        claimFormService.updateClaimForm(claimForm);
        return ResponseEntity.ok().build();
    }
    private List<ClaimDocument> createClaimDocuments() {
        List<ClaimDocument> documents = new ArrayList<>();
        for(DocumentType s1: DocumentType.values()){
            ClaimDocument d = new ClaimDocument(s1);
            documents.add(d);
        }
        return documents;
    }
}
