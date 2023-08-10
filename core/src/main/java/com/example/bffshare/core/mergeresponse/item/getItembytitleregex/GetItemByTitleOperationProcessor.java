package com.example.bffshare.core.mergeresponse.item.getItembytitleregex;

import com.example.bffshare.api.item.getitembytitleregex.GetItemByTitleInput;
import com.example.bffshare.api.item.getitembytitleregex.GetItemByTitleOperation;
import com.example.bffshare.api.item.getitembytitleregex.GetItemByTitleResult;
import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.api.Item.bytitleregex.GetByTitleRegexResult;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetItemByTitleOperationProcessor implements GetItemByTitleOperation {

    private final ZooStoreRestExport zooStoreRestExport;
    private final ZooStorageRestExport zooStorageRestExport;
    @Override
    public Page<GetItemByTitleResult> process(GetItemByTitleInput operationInput, Pageable pageable) throws Exception {

        zooStoreRestExport.getItemPageByTitleRegex(operationInput.getTitle());

        List<GetByTitleRegexResult> getByTitleRegexResults =zooStoreRestExport.getItemPageByTitleRegex(operationInput.getTitle());
        List<GetItemByTitleResult> getItemByTitleResultList = new ArrayList<>();

        getByTitleRegexResults.forEach(getByTitleRegexResult -> {
            getItemByTitleResultList.add(GetItemByTitleResult.builder()
                            .id(getByTitleRegexResult.getId())
                            .title(getByTitleRegexResult.getTitle())
                            .description(getByTitleRegexResult.getDescription())
                            .vendor(getByTitleRegexResult.getVendor())
                            .price(zooStorageRestExport.getStorageItemById(getByTitleRegexResult.getId().toString()).getPrice())
                            .media(getByTitleRegexResult.getMedia())
                            .tags(getByTitleRegexResult.getTags())
                                            .build());

        });

        return new PageImpl<>(getItemByTitleResultList,pageable,1);
    }
}
