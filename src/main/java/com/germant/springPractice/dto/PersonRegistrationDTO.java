package com.germant.springPractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PersonRegistrationDTO {
    private String name;

    private String password;
}
