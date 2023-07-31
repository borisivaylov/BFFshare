package com.example.bffshare.api.item.fulliteminfo;

import com.example.bffshare.api.base.OperationResult;
import com.example.zoostoreproject.persistence.entity.Media;
import com.example.zoostoreproject.persistence.entity.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BFFItemInfoOutput implements OperationResult {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("quantity")
    private long quantity;
    @JsonProperty ("price")
    private double price;
    @JsonProperty("description")
    private String description;
    @JsonProperty("vendor")
    private String vendorName;
    @JsonProperty("media")
    private Set<Media> media;
    @JsonProperty("tags")
    private Set<Tag> tags;
    /*@JsonProperty("archived")
    private boolean archived;*/

}
