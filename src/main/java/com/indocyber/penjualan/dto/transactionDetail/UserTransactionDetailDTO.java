package com.indocyber.penjualan.dto.transactionDetail;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UserTransactionDetailDTO {

    private Long id;

    private String productName;

    private Integer quantity;

    private String unit;

    private BigDecimal subTotal;
}
