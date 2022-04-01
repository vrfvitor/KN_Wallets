package com.vrfvitor.knwallets.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

@Data
@Entity(name = "wallets")
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Long balanceCents = 0L;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "owner_first_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "owner_last_name")),
            @AttributeOverride(name = "email", column = @Column(name = "owner_email"))
    })
    private Person owner;

    public Wallet(Person owner) {
        this.owner = owner;
    }
}
