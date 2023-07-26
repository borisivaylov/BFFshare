package com.example.bffshare.api.item;


import com.example.bffshare.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BFFInput implements OperationInput {
    @JsonProperty("id")
    private UUID id;
}
