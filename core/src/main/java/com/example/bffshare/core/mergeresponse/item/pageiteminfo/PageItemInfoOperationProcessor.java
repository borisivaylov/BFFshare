package com.example.bffshare.core.mergeresponse.item.pageiteminfo;

import com.example.bffshare.api.item.iteminfopage.ItemPageOperation;
import com.example.bffshare.api.item.iteminfopage.ItemPageRequest;
import com.example.bffshare.api.item.iteminfopage.ItemPageResponse;
import com.example.storageservice.api.Item.getitembytag.GetItemByTagResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.api.Item.getallitems.GetAllItemsResponse;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Returns information about items found by tag, using pagination.

@Service
@RequiredArgsConstructor
public class PageItemInfoOperationProcessor implements ItemPageOperation {

    private final ZooStoreRestExport zooStoreRestExport;
    private final ZooStorageRestExport zooStorageRestExport;

    @Override
    public Page<ItemPageResponse> process(ItemPageRequest operationInput, org.springframework.data.domain.Pageable pageable) throws Exception {
        List<GetAllItemsResponse> getAllItemsResponseList= zooStoreRestExport.getItemsByTag(operationInput.getTagName());
        List<GetItemByTagResponse> getItemByTagResponseList = zooStorageRestExport.getStorageItemsByTag(operationInput.getTagName());
        List<ItemPageResponse> itemPageResponseList = new ArrayList<ItemPageResponse>();

        if (getAllItemsResponseList.isEmpty()){
            throw new Exception("No items found");
        }
        if (getItemByTagResponseList.isEmpty()){
            throw new Exception("No items found");
        }

        getAllItemsResponseList.forEach(getAllItemsResponse -> {
            itemPageResponseList.add(ItemPageResponse.builder()
                    .id(getAllItemsResponse.getId())
                    .title(getAllItemsResponse.getTitle())
                    .description(getAllItemsResponse.getDescription())
                    .tags(getAllItemsResponse.getTags())
                    .description(getAllItemsResponse.getDescription())
                    .vendorName(getAllItemsResponse.getVendor())
                    .media(getAllItemsResponse.getMedia())
                    .build());

        });

        for (int i = 0; i < getItemByTagResponseList.size(); i++) {

            itemPageResponseList.get(i).setPrice(getItemByTagResponseList.get(i).getPrice());
            itemPageResponseList.get(i).setQuantity(getItemByTagResponseList.get(i).getQuantity());
        }

        return new PageImpl<>( itemPageResponseList,pageable, 2 );//itemPageResponseList.size());
    }
}
