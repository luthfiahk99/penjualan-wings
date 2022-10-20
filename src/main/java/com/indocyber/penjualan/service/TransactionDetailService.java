package com.indocyber.penjualan.service;

import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import org.springframework.data.domain.Page;

public interface TransactionDetailService {
    void saveDetail(UpsertTransactionDetailDTO dto);

    Page<UpsertTransactionDetailDTO> getTransactionGridByUser(String username, Integer page);
}
