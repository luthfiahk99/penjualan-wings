package com.indocyber.penjualan.service;

import com.indocyber.penjualan.dto.product.ProductGridDTO;
import com.indocyber.penjualan.dto.product.UpsertProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductGridDTO> getProductGrid(Integer page, String productCode, String productName);

    long getTotalPages(String productCode, String productName);

    void insertProduct(UpsertProductDTO dto);

    UpsertProductDTO getDetail(String productCode);

    UpsertProductDTO getUpdateProduct(String productCode);
}
