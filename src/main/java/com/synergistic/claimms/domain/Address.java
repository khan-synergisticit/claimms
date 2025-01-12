package com.synergistic.claimms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;

}
