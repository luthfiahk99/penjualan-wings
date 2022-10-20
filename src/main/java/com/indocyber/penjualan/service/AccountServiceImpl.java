package com.indocyber.penjualan.service;

import com.indocyber.penjualan.ApplicationUserDetails;
import com.indocyber.penjualan.entity.Account;
import com.indocyber.penjualan.entity.Product;
import com.indocyber.penjualan.repository.AccountRepository;
import com.indocyber.penjualan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    protected ProductRepository productRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);

        Account account = null;
        if (nullableEntity.isPresent()){
            account = nullableEntity.get();
        }
        return new ApplicationUserDetails(account);
    }

    @Override
    public void insertProduct(String productCode) {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountRepository.getById(username);

        Product product = productRepository.getById(productCode);

        account.addProduct(product);
        accountRepository.save(account);
    }
}
