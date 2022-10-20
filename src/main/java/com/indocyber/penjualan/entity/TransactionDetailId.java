package com.indocyber.penjualan.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Embeddable
public class TransactionDetailId implements Serializable {

    @Column(name = "ProductCode")
    private String productCode;

    @Column(name = "DocumentNumber")
    private Long documentNumber;
}
