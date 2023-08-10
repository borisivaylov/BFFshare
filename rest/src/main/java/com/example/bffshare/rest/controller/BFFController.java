package com.example.bffshare.rest.controller;



import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoInput;
import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoOutput;
import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelInput;
import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelOperation;
import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelResult;
import com.example.bffshare.api.item.getitembytitleregex.GetItemByTitleInput;
import com.example.bffshare.api.item.getitembytitleregex.GetItemByTitleResult;
import com.example.bffshare.api.item.iteminfopage.ItemPageRequest;
import com.example.bffshare.api.item.iteminfopage.ItemPageResponse;
import com.example.bffshare.api.item.mergeitemresponse.BFFInput;
import com.example.bffshare.api.item.mergeitemresponse.BFFOutput;
import com.example.bffshare.core.mergeresponse.item.fulliteminfo.FullItemInfo;
import com.example.bffshare.core.mergeresponse.item.getItembytitleregex.GetItemByTitleOperationProcessor;
import com.example.bffshare.core.mergeresponse.item.getitemparallel.GetAllItemsParallelOperationProcessor;
import com.example.bffshare.core.mergeresponse.item.itemcustomInfo.MergeItemCustomInfoOperationProcessor;
import com.example.bffshare.core.mergeresponse.item.pageiteminfo.PageItemInfoOperationProcessor;
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
    private final GetAllItemsParallelOperation getAllItemsParallelOperationProcessor;
    private final GetItemByTitleOperationProcessor getItemByTitleOperationProcessor;

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

    @GetMapping("/parallel/{string}")
    List<GetAllItemsParallelResult> getAllItemsParallel(@PathVariable String string) throws Exception {
        return getAllItemsParallelOperationProcessor.process(GetAllItemsParallelInput.builder().test(string).build());
    }

    @GetMapping ("/itemTitle/{string}")
    Page<GetItemByTitleResult> itemsByTitle(@PathVariable String string,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "2") int size) throws Exception {
        Pageable pageable = PageRequest.of(page,1);
        return getItemByTitleOperationProcessor.process(GetItemByTitleInput.builder().title(string).build(),pageable);
    }


}
