package com.example.bffshare.core.mergeresponse.catalog;

import com.example.bffshare.api.catalog.ViewCatalogInput;
import com.example.bffshare.api.catalog.ViewCatalogItem;
import com.example.bffshare.api.catalog.ViewCatalogOperation;
import com.example.bffshare.api.catalog.ViewCatalogResult;
import com.example.storageservice.api.catalog.view.GetCatalogResult;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewCatalogOperationProcessor implements ViewCatalogOperation {

    private final ZooStorageRestExport zooStorageRestExport;
    @Override
    public ViewCatalogResult process(ViewCatalogInput operationInput) throws Exception {

        GetCatalogResult currentCatalog = zooStorageRestExport.viewCatalog(operationInput.getCatalogId());
        List<ViewCatalogItem> viewCatalogItemList = new ArrayList<>();

        currentCatalog.getCatalogItems().forEach(item ->{
            viewCatalogItemList.add(ViewCatalogItem.builder()
                            .itemId(item.getItemId())
                            .title(item.getTitle())
                            .description(item.getDescription())
                            .actualPrice(item.getActualPrice())
                            .discount(item.getDiscount())
                            .onSalePrice(item.getOnSalePrice())
                    .build());
        });

        return ViewCatalogResult.builder()
                .catalogId(currentCatalog.getCatalogId())
                .catalogItems(viewCatalogItemList)
                .dateOfCreation(currentCatalog.getDateOfCreation())
                .build();
    }
}
