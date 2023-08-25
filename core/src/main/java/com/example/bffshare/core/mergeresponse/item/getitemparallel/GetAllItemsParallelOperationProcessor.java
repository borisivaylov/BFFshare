package com.example.bffshare.core.mergeresponse.item.getitemparallel;

import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelInput;
import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelOperation;
import com.example.bffshare.api.item.getallitemsparallel.GetAllItemsParallelResult;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.api.Item.getallitems.GetAllItemsRequest;
import com.example.storageservice.api.Item.getallitems.GetAllItemsResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*  Fetches all available information about all items from
    Store and Storage services and displays it using parallel stream */


@Service
@RequiredArgsConstructor
public class GetAllItemsParallelOperationProcessor implements GetAllItemsParallelOperation {

    private final ZooStorageRestExport zooStorageRestExport;

    @Override
    public List<GetAllItemsParallelResult> process(GetAllItemsParallelInput operationInput) throws Exception {

        GetAllItemsRequest getAllItemsRequest = new GetAllItemsRequest("test");
        List<GetAllItemsResponse> getAllItemsRequestList= zooStorageRestExport.getAllIds(getAllItemsRequest.getTesting1());

        List<UUID> uuids = new ArrayList<>();
        List<GetAllItemsParallelResult> itemsParallelResults = new ArrayList<>();

        getAllItemsRequestList.forEach( shipment -> { uuids.add(shipment.getItemId()); });

        uuids.parallelStream().forEach(
                uuid -> {
                    ItemResponse shipment = zooStorageRestExport.getStorageItemById(uuid.toString());
                            //.orElseThrow(() -> new NoSuchElementException("No such shipment"));
                    GetAllItemsParallelResult getAllItemsParallelResult= GetAllItemsParallelResult.builder()
                            .itemId(shipment.getId())
                            .price(shipment.getPrice())
                            .quantity(shipment.getQuantity())
                            .build();
                    itemsParallelResults.add(getAllItemsParallelResult);
                });

        return itemsParallelResults;
    }
}
