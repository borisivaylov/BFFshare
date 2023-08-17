package com.example.bffshare.rest.controller;

import com.example.bffshare.api.catalog.ViewCatalogInput;
import com.example.bffshare.api.catalog.ViewCatalogResult;
import com.example.bffshare.core.mergeresponse.catalog.ViewCatalogOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final ViewCatalogOperationProcessor viewCatalogOperationProcessor;


    @GetMapping("/{catalogId}")
    ViewCatalogResult viewCatalog(@PathVariable UUID catalogId) throws Exception {
        return viewCatalogOperationProcessor.process(ViewCatalogInput.builder().catalogId(catalogId).build());
    }
}
