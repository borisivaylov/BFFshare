package com.example.bffshare.restexport;

import com.example.bffshare.api.cart.emptycart.EmptyCartContentsResponse;
import com.example.bffshare.api.cart.viewallitems.ViewCartResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.UUID;

@Headers({"Content-Type: application/json"})
public interface BffShareRestExport {

    @RequestLine("GET /cart/{uuid}")
    ViewCartResponse getStorageItemById(@Param UUID uuid);
    @RequestLine( "POST /cart//emptybff")
    EmptyCartContentsResponse emptyCartBff(@Param UUID uuid);

}
