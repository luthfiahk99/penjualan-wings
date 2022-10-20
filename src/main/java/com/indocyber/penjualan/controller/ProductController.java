package com.indocyber.penjualan.controller;

import com.indocyber.penjualan.dto.product.ProductGridDTO;
import com.indocyber.penjualan.dto.product.UpsertProductDTO;
import com.indocyber.penjualan.dto.transaction.UpsertTransactionDTO;
import com.indocyber.penjualan.dto.transactionDetail.UpsertTransactionDetailDTO;
import com.indocyber.penjualan.entity.Account;
import com.indocyber.penjualan.helper.Helper;
import com.indocyber.penjualan.service.AccountService;
import com.indocyber.penjualan.service.ProductService;
import com.indocyber.penjualan.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionDetailService transactionDetailService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String productCode,
                        @RequestParam(defaultValue = "") String productName,
                        Model model){

        List<ProductGridDTO> grid = productService.getProductGrid(page, productCode, productName);

        long totalPages = productService.getTotalPages(productCode, productName);

        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("productCode", productCode);
        model.addAttribute("productName", productName);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Room Index");

        return "product/product-index";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) String productCode,
                         Model model) {
        UpsertProductDTO dto = productService.getDetail(productCode);

        String breadCrumbs = String.format("Product Index / Detail of %s", dto.getProductName());
        model.addAttribute("product", dto);
        model.addAttribute("breadCrumbs", breadCrumbs);
        return "product/product-detail";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam(required = true) String productCode){
         accountService.insertProduct(productCode);

         return "redirect:/product/checkoutPage";
    }

    @GetMapping("/checkoutPage")
    public String checkoutPage(@RequestParam(defaultValue = "1")Integer page, Model model){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        Page<UpsertTransactionDetailDTO> grid = transactionDetailService.getTransactionGridByUser(username, page);

        BigDecimal total = new BigDecimal(0);
        for (UpsertTransactionDetailDTO value :  grid){
            total = total.add(value.getSubTotal());
        }
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", grid.getTotalPages());
        model.addAttribute("total", Helper.formatRupiah(total));

        return "product/checkout-page";
    }

    @GetMapping("/buy1")
    public String checkoutForm(@RequestParam String productCode,
                              Model model){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();

        UpsertTransactionDetailDTO dto = new UpsertTransactionDetailDTO();
        UpsertProductDTO productDTO = productService.getUpdateProduct(productCode);
        dto.setUsername(username);
        dto.setProductName(productDTO.getProductName());
        dto.setDiscountPrice(productDTO.getPrice().subtract(Helper.countDiscount(productDTO.getPrice(), productDTO.getDiscount() )));

        System.out.println("buy mapping : " + dto);
        model.addAttribute("detail", dto);
        model.addAttribute("type", "Buy");
        model.addAttribute("breadCrumbs", "Product List / Buy Product");

        return "product/buy-form";
    }

    @PostMapping("/addCart")
    public String addCart(@Valid @ModelAttribute("detail") UpsertTransactionDetailDTO dto,
                              BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("type", "Add to Check Out Page");
            model.addAttribute("breadCrumbs","Product List / Buy Product");
            return "product/buy-form";
        }

        transactionDetailService.saveDetail(dto);
        return "redirect:/checkoutPage";
    }
}
