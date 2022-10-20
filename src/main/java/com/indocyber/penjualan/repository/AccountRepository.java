package com.indocyber.penjualan.repository;

import com.indocyber.penjualan.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
