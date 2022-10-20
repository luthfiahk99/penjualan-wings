package com.indocyber.penjualan.service;

import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import org.springframework.data.domain.Page;

public interface AccountService {

    void insertProduct(String productCode);
}
