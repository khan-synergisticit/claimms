package com.synergistic.claimms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document("provider")
public class Provider {
    @Id
    private String id;
    private String networkId;
    private String name;
    private String email;
    private String phone;
    private Address address;
}
