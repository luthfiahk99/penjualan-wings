package com.indocyber.penjualan.dto.product;

import com.indocyber.penjualan.helper.Helper;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class UpsertProductDTO {

    @NotBlank(message = "This field is required")
    private String productCode;

    @NotBlank(message = "This field is required")
    private String productName;

    @NotBlank(message = "This field is required")
    private BigDecimal price;

    @NotBlank(message = "This field is required")
    private String currency;

    private Long discount;

    @NotBlank(message = "This field is required")
    private String dimension;

    @NotBlank(message = "This field is required")
    private String unit;

    public String getPriceRupiah(){return Helper.formatRupiah(this.price);}

    public String getDiscountPriceRupiah(){return Helper.formatRupiah(price.subtract(Helper.countDiscount(price, discount)));}
}
