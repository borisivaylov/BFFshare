package com.example.bffshare.api.item.getitembytitleregex;

import com.example.bffshare.api.base.OperationInput;
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
public class GetItemByTitleResult implements OperationResult {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("vendor")
    private UUID vendor;
    @JsonProperty("price")
    private double price;
    @JsonProperty("media")
    private Set<Media> media;
    @JsonProperty("tags")
    private Set<Tag> tags;
}
