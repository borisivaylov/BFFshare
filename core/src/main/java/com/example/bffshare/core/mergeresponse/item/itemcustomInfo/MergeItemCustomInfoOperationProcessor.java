package com.example.bffshare.core.mergeresponse.item.itemcustomInfo;

import com.example.bffshare.api.item.mergeitemresponse.BFFInput;
import com.example.bffshare.api.item.mergeitemresponse.BFFOutput;
import com.example.bffshare.api.item.mergeitemresponse.MergeItemCustomInfoOperation;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.api.Item.getItem.GetItemResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MergeItemCustomInfoOperationProcessor implements MergeItemCustomInfoOperation {

    public final ZooStoreRestExport zooStoreRestExport;
    public final ZooStorageRestExport zooStorageRestExport;

    @Override
    public BFFOutput process(BFFInput bffInput) throws Exception {

        GetItemResponse getItemResponseStore = zooStoreRestExport.getItemById(bffInput.getId().toString());
        ItemResponse itemResponseStorage = zooStorageRestExport.getStorageItemById(bffInput.getId().toString());

        return BFFOutput.builder()
                .id(itemResponseStorage.getId())
                .title(getItemResponseStore.getTitle())
                .description(getItemResponseStore.getDescription())
                .price(itemResponseStorage.getPrice())
                .quantity(itemResponseStorage.getQuantity())
                .build();
    }
}
