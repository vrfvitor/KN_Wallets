package com.vrfvitor.knwallets.model;

import lombok.*;

import javax.persistence.*;

@Data
@Embeddable
public class Person {

    private String firstName;
    private String lastName;
    private String email;

}
