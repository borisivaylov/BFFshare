package com.example.bffshare.api.catalog;

import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCatalogResult implements OperationResult {


    @JsonProperty
    private UUID catalogId;
    @JsonProperty
    private List<ViewCatalogItem> catalogItems = new ArrayList<>();
    @JsonProperty
    private Date dateOfCreation;

}
