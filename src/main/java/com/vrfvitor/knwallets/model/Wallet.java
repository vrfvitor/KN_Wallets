package com.vrfvitor.knwallets.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    private UUID id;

    private Long balanceCents;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "owner_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "owner_last_name")),
            @AttributeOverride(name = "email", column = @Column(name = "owner_email"))
    })
    private Person owner;

}
