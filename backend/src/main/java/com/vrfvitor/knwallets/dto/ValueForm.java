package com.vrfvitor.knwallets.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValueForm {

    @Min(1)
    @NotNull
    private Long amountCents;

}
