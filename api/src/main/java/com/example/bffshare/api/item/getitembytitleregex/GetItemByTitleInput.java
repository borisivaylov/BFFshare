package com.example.bffshare.api.item.getitembytitleregex;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetItemByTitleInput implements OperationInput {
    @JsonProperty
    private String title;
}
