package com.indocyber.penjualan.dto.transactionDetail;

import com.indocyber.penjualan.helper.Helper;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UpsertTransactionDetailDTO {

    private String productName;

    private String username;

    private Long quantity;

    private String unit;

    private BigDecimal discountPrice;

    private BigDecimal subTotal;

    public UpsertTransactionDetailDTO(String productName, String username, Long quantity, String unit) {
        this.productName = productName;
        this.username = username;
        this.quantity = quantity;
        this.unit = unit;
    }

    public UpsertTransactionDetailDTO(String productName, Long quantity, BigDecimal price, Long discount) {
        this.productName = productName;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public UpsertTransactionDetailDTO(String productName, Long quantity, String unit, BigDecimal subTotal) {
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
        this.subTotal = subTotal;
    }


}
