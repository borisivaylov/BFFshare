package com.example.bffshare.core.mergeresponse.fulliteminfo;

import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoInput;
import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoOperation;
import com.example.bffshare.api.item.fulliteminfo.BFFItemInfoOutput;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.api.Item.getallitems.GetAllItemsResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class FullItemInfo implements BFFItemInfoOperation {

    private final ZooStoreRestExport zooStoreRestExport;
    private final ZooStorageRestExport zooStorageRestExport;

    @Override
    public List<BFFItemInfoOutput> process(BFFItemInfoInput bffItemInfoInput) throws Exception {

        List<GetAllItemsResponse> getAllItemsResponseList= zooStoreRestExport.getItemsByTag(bffItemInfoInput.getTagName());
        List<GetItemByTagResponse> getItemByTagResponseList = zooStorageRestExport.getStorageItemsByTag(bffItemInfoInput.getTagName());
        List<BFFItemInfoOutput> bffItemInfoOutputList = new ArrayList<BFFItemInfoOutput>();

        if (getAllItemsResponseList.isEmpty()){
            throw new Exception("No items found");
        }
        if (getItemByTagResponseList.isEmpty()){
            throw new Exception("No items found");
        }

        BFFItemInfoOutput bffItemInfoOutput= new BFFItemInfoOutput();

        for (GetAllItemsResponse getAllItemsResponse: getAllItemsResponseList) {

            bffItemInfoOutputList.add(BFFItemInfoOutput.builder()
                                                                .id(getAllItemsResponse.getId())
                                                                .title(getAllItemsResponse.getTitle())
                                                                .description(getAllItemsResponse.getDescription())
                                                                .tags(getAllItemsResponse.getTags())
                                                                .description(getAllItemsResponse.getDescription())
                                                                .vendorName(getAllItemsResponse.getVendor())
                                                                .media(getAllItemsResponse.getMedia())
                                                                .build());
        }

        for (int i = 0; i < getItemByTagResponseList.size(); i++) {

                bffItemInfoOutputList.get(i).setPrice(getItemByTagResponseList.get(i).getPrice());
                bffItemInfoOutputList.get(i).setQuantity(getItemByTagResponseList.get(i).getQuantity());
        }

        return bffItemInfoOutputList;
    }
}
