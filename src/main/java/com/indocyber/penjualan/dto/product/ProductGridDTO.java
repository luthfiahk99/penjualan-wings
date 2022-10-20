package com.indocyber.penjualan.dto.product;

import com.indocyber.penjualan.helper.Helper;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class ProductGridDTO {

    private String productCode;

    private String productName;

    private BigDecimal price;

    private Long discount;

    private BigDecimal discountPrice;

    private String imagePath;

    public ProductGridDTO(String productCode, String productName, BigDecimal price, Long discount) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.discountPrice = price.subtract(Helper.countDiscount(price, discount));
    }

    public String getPriceRupiah(){return Helper.formatRupiah(this.price);}

    public String getDiscountPriceRupiah(){return Helper.formatRupiah(this.discountPrice);}
}
