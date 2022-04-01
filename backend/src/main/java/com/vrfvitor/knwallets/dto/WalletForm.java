package com.vrfvitor.knwallets.dto;

import com.vrfvitor.knwallets.model.*;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletForm {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String email;

    public Wallet converter() {
        return new Wallet(new Person(this.firstName, this.lastName, this.email));
    }
}
