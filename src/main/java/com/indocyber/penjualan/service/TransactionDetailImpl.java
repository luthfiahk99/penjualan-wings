package com.indocyber.penjualan.service;

import com.indocyber.penjualan.dto.product.ProductGridDTO;
import com.indocyber.penjualan.dto.product.UpsertProductDTO;
import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import com.indocyber.penjualan.entity.Account;
import com.indocyber.penjualan.entity.Product;
import com.indocyber.penjualan.entity.TransactionDetail;
import com.indocyber.penjualan.repository.AccountRepository;
import com.indocyber.penjualan.repository.ProductRepository;
import com.indocyber.penjualan.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionDetailImpl implements TransactionDetailService{

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    private int rowsInPage = 5;

    @Override
    public void saveDetail(UpsertTransactionDetailDTO dto) {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        UpsertProductDTO productDTO = productRepository.findByName(dto.getProductName());
        Optional<Product> tempProduct = productRepository.findById(productDTO.getProductCode());
        Product product = null;
        if(tempProduct.isPresent()){
            product = tempProduct.get();
        }

        Optional<Account> tempAccount = accountRepository.findById(username);
        Account account = tempAccount.get();

        TransactionDetail transactionDetail = new TransactionDetail();
//                dto.getId(),
//                account,
//                product,
//                null,
//                dto.getDiscountPrice(),
//                dto.getQuantity(),
//                product.getUnit(),
//                product.getCurrency(),
//                dto.getDiscountPrice().subtract(new BigDecimal(dto.getQuantity()))

        transactionDetailRepository.save(transactionDetail);
    }

    @Override
    public Page<UpsertTransactionDetailDTO> getTransactionGridByUser(String username, Integer page) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        Page<UpsertTransactionDetailDTO> grid = productRepository.findByUser(username, pagination);

        for (UpsertTransactionDetailDTO value : grid){
            BigDecimal price = value.getDiscountPrice();

            value.setSubTotal(price.multiply(new BigDecimal(value.getQuantity())));
        }
        return grid;
    }
}
