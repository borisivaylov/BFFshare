package com.example.bffshare.core.mergeresponse.item.fulliteminfo;


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
import java.util.stream.IntStream;

 /* Fetches all available information about all items from
    Store and Storage services and displays it */

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


        getAllItemsResponseList.forEach( storeItem->{

                bffItemInfoOutputList.add(BFFItemInfoOutput.builder()
                                                .id(storeItem.getId())
                                                .title(storeItem.getTitle())
                                                .description(storeItem.getDescription())
                                                .tags(storeItem.getTags())
                                                .description(storeItem.getDescription())
                                                .vendorName(storeItem.getVendor())
                                                .media(storeItem.getMedia())
                                                .build());
        }) ;

        IntStream.range(0, Math.min(bffItemInfoOutputList.size(), getItemByTagResponseList.size()))
                .forEach(i -> {
                    bffItemInfoOutputList.get(i).setPrice(getItemByTagResponseList.get(i).getPrice());
                    bffItemInfoOutputList.get(i).setQuantity(getItemByTagResponseList.get(i).getQuantity());
                });

        return bffItemInfoOutputList;
    }
}
