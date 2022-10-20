package com.indocyber.penjualan.dto.cart;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class InsertCartDTO {

    private String productName;

    private String username;

    private Integer quantity;
}
