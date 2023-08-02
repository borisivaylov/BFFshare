package com.example.bffshare.rest.controller;


import com.example.bff.api.item.BFFInput;
import com.example.bff.api.item.BFFOutput;
import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoInput;
import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoOutput;
import com.example.bffshare.api.item.iteminfopage.ItemPageRequest;
import com.example.bffshare.api.item.iteminfopage.ItemPageResponse;
import com.example.bffshare.core.mergeresponse.fulliteminfo.FullItemInfo;
import com.example.bffshare.core.mergeresponse.itemcustomInfo.MergeItemCustomInfoOperationProcessor;
import com.example.bffshare.core.mergeresponse.pageiteminfo.PageItemInfoOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bff")
@RequiredArgsConstructor
public class BFFController {

    private final MergeItemCustomInfoOperationProcessor mergeItemResponse;
    private final FullItemInfo fullItemInfo;
    private final PageItemInfoOperationProcessor pageItemInfoOperationProcessor;

    @GetMapping("/{itemId}")
    BFFOutput getItemBFF(@PathVariable UUID itemId) throws Exception {
        return mergeItemResponse.process(BFFInput.builder().id(itemId).build());
    }
    @GetMapping("/itemInfo/{tagName}")
    List<BFFItemInfoOutput> getFullItemInfoList(@PathVariable String tagName) throws Exception {
        return fullItemInfo.process(BFFItemInfoInput.builder().tagName(tagName).build());
    }
    @GetMapping("/itemInfo/page/{tagName}")
    Page<ItemPageResponse>getFullItemInfoPage(@PathVariable String tagName, @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) throws Exception {
        Pageable pageable = PageRequest.of(size,2);

        return pageItemInfoOperationProcessor.process(ItemPageRequest.builder().tagName(tagName).build(),pageable);
    };
    @GetMapping("/random")
    public ResponseEntity rr(){
       return ResponseEntity.ok("aUTHENCATED");
    }


}
