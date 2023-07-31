package com.example.bffshare.api.item.fulliteminfo;

import com.example.bffshare.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BFFItemInfoInput implements OperationInput {

    @JsonProperty("tagName")
    private String tagName;

}
