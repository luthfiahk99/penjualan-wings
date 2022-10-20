package com.indocyber.penjualan.service;

import com.indocyber.penjualan.dto.product.ProductGridDTO;
import com.indocyber.penjualan.dto.product.UpsertProductDTO;
import com.indocyber.penjualan.entity.Product;
import com.indocyber.penjualan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    private int rowsInPage = 10;

    @Override
    public List<ProductGridDTO> getProductGrid(Integer page, String productCode, String productName) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<ProductGridDTO> grid = productRepository.findAll(productCode, productName, pagination);
        return grid;
    }

    @Override
    public long getTotalPages(String productCode, String productName) {
        double totalData = (double)(productRepository.count(productCode, productName));

        long totalPage = (long) (Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public void insertProduct(UpsertProductDTO dto) {
        Product entity = new Product(
                dto.getProductCode(),
                dto.getProductName(),
                dto.getPrice(),
                dto.getCurrency(),
                dto.getDiscount(),
                dto.getDimension(),
                dto.getUnit()
        );
        productRepository.save(entity);
    }

    @Override
    public UpsertProductDTO getDetail(String productCode) {
        return productRepository.getDetail(productCode);
    }

    @Override
    public UpsertProductDTO getUpdateProduct(String productCode) {
        Optional<Product> nullableEntity = productRepository.findById(productCode);

        Product entity = nullableEntity.get();

        UpsertProductDTO productDTO = new UpsertProductDTO(
                entity.getProductCode(),
                entity.getProductName(),
                entity.getPrice(),
                entity.getCurrency(),
                entity.getDiscount(),
                entity.getDimension(),
                entity.getUnit());

        return productDTO;
    }

}
