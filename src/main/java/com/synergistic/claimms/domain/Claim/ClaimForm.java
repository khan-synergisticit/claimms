package com.synergistic.claimms.domain.Claim;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergistic.claimms.domain.documents.ClaimDocument;
import com.synergistic.claimms.domain.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document("claimForm")
@JsonIgnoreProperties(value = { "target" })
public class ClaimForm {
    @Id
    private String claimId;
    private String policyId;
    private String memberId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate claimDate;
    @DocumentReference(lazy = true, collection = "claimCharge")
    private ClaimCharge claimCharge;
    @DocumentReference(lazy = true, collection = "patient")
    private Patient patient;
    private ClaimType claimType;
    private List<ClaimDocument> claimDocuments;
    private ClaimStatus claimStatus;

    public ClaimForm(String memberId) {
        this.memberId = memberId;
    }
}
