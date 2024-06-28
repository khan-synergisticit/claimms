package com.synergistic.claimms.domain.documents;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClaimDocument {
    private DocumentType type;
    private String fileName;
    private String url;
    private Boolean accepted;

    public ClaimDocument(DocumentType type) {
        this.type = type;
        this.fileName = "";
        this.url = "";
        this.accepted = false;
    }
}
