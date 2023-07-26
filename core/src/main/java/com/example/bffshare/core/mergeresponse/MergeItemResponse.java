package com.example.bffshare.core.mergeresponse;

import com.example.bff.api.item.BFFInput;
import com.example.bff.api.item.BFFOutput;
import com.example.bff.api.item.MergeResponses;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.api.Item.getItem.GetItemResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MergeItemResponse implements MergeResponses {

    public final ZooStoreRestExport zooStoreRestExport;
    public final ZooStorageRestExport zooStorageRestExport;

    @Override
    public BFFOutput process(BFFInput bffInput) throws Exception {

        GetItemResponse getItemResponseStore;
        ItemResponse itemResponseStorage;
        try {
            getItemResponseStore = zooStoreRestExport.getItemById(bffInput.getId().toString());
            itemResponseStorage = zooStorageRestExport.getStorageItemById(bffInput.getId().toString());
           // getItemResponseStore = zooStoreRestExport.getItemById(bffInput.getId().toString());
            System.out.println(itemResponseStorage.getId());
            System.out.println(getItemResponseStore.getId());

        } catch (Exception e) {
            throw new Exception("item not found");
        }

        System.out.println(itemResponseStorage.getId());
        System.out.println(getItemResponseStore.getId());

        return BFFOutput.builder()
                .id(itemResponseStorage.getId())
                .title(getItemResponseStore.getTitle())
                .description(getItemResponseStore.getDescription())
                .price(itemResponseStorage.getPrice())
                .quantity(itemResponseStorage.getQuantity())
                .build();
    }
}
