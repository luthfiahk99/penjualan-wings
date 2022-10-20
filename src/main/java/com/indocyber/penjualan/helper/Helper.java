package com.indocyber.penjualan.helper;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Helper {

    public static BigDecimal countDiscount(BigDecimal price, Long discount){
        BigDecimal disc = new BigDecimal(discount);
        BigDecimal totalDisc = price.multiply(disc.divide(BigDecimal.valueOf(100)));
        return totalDisc;
    }

    public static String formatRupiah(BigDecimal price){
        Locale indo = new Locale("id", "ID");
        String format = NumberFormat.getCurrencyInstance(indo).format(price);
        return format;
    }

//    public static String countSubTotal(Integer quantity, BigDecimal price, Long discount){
//
//    }
}
