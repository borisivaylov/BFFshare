package com.example.bffshare.api.item.mergeitemresponse;

import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BFFOutput implements OperationResult {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty("price")
    private double price;
}
