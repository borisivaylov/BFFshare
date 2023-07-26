package com.example.bffshare.rest.controller;


import com.example.bff.api.item.BFFInput;
import com.example.bff.api.item.BFFOutput;
import com.example.bffshare.core.mergeresponse.MergeItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/bff")
@RequiredArgsConstructor
public class BFFController {

    private final MergeItemResponse mergeItemResponse;

    @GetMapping("/{itemId}")
    BFFOutput getItemBFF(@PathVariable UUID itemId) throws Exception {
        return mergeItemResponse.process(BFFInput.builder().id(itemId).build());
    }
}
