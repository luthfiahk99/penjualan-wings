package com.indocyber.penjualan.repository;

import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import com.indocyber.penjualan.entity.TransactionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {


//    @Query("""
//			SELECT new com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO
//			    (trd.id, pro.productName, trd.quantity, trd.unit, trd.subTotal)
//			FROM TransactionDetail AS trd
//			    JOIN trd.account AS ac
//			    JOIN trd.product AS pro
//			WHERE ac.username = :username
//			""")
//    Page<UpsertTransactionDetailDTO> findAll(@Param("username") String username, Pageable pagination);
}
