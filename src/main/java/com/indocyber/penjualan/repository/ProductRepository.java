package com.indocyber.penjualan.repository;

import com.indocyber.penjualan.dto.product.ProductGridDTO;
import com.indocyber.penjualan.dto.product.UpsertProductDTO;
import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import com.indocyber.penjualan.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("""
			SELECT new com.indocyber.penjualan.dto.product.ProductGridDTO(pro.productCode, pro.productName, pro.price, pro.discount)
			FROM Product AS pro
			WHERE pro.productCode LIKE %:productCode%
				AND pro.productName LIKE %:productName%
			""")
    List<ProductGridDTO> findAll(@Param("productCode") String productCode, @Param("productName") String productName, Pageable pagination);

	@Query("""
			SELECT COUNT(pro.price)
			FROM Product AS pro
			WHERE pro.productCode LIKE %:productCode%
				AND pro.productName LIKE %:productName%
			""")
	Long count(String productCode, String productName);

	@Query("""
			SELECT new com.indocyber.penjualan.dto.product.UpsertProductDTO(
				pro.productCode, pro.productName, pro.price, pro.currency, pro.discount, pro.dimension, pro.unit)
			FROM Product AS pro
			WHERE pro.productCode LIKE %:productCode%
			""")
    UpsertProductDTO getDetail(@Param("productCode") String productCode);

	@Query("""
			SELECT new com.indocyber.penjualan.dto.product.UpsertProductDTO(
				pro.productCode, pro.productName, pro.price, pro.currency, pro.discount, pro.dimension, pro.unit)
			FROM Product AS pro
			WHERE pro.productName = :productName
			""")
    UpsertProductDTO findByName(@Param("productName") String productName);

	@Query("""
			SELECT new com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO(
				pro.productCode, pro.productName, count(pro.productCode), pro.unit)
			FROM Product AS pro
				INNER JOIN pro.accountList AS ac
			WHERE ac.username = :username
			GROUP BY pro.productCode, ac.username, pro.productName, pro.unit
			""")
    Page<UpsertTransactionDetailDTO> findByUser(@Param("username") String username, Pageable pagination);
}

