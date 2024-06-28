package com.synergistic.claimms.domain.Claim;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.synergistic.claimms.domain.Provider;
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
@Document("claimCharge")
@JsonIgnoreProperties(value = { "target" })
public class ClaimCharge {
    @Id
    private String id;
    @DocumentReference(collection = "provider")
    private Provider provider;
    private Double totalCost;
    private List<Double> costs;
    private List<String> items;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private List<LocalDate[]> dates;
    private Boolean accepted = false;
}
