package com.example.bffshare.api.item.iteminfopage;

import com.example.bffshare.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPageRequest implements OperationInput {

    @JsonProperty("tagName")
    private String tagName;
    @JsonProperty("pageSize")
    private int size;
    @JsonProperty("page")
    private int page;
}
