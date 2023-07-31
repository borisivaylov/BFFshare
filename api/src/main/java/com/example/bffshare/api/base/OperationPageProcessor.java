package com.example.bffshare.api.base;

import com.example.bffshare.api.item.iteminfopage.ItemPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationPageProcessor <I extends OperationInput, T extends OperationResult>{
    Page<T> process(I operationInput, Pageable pageable) throws Exception;

}
