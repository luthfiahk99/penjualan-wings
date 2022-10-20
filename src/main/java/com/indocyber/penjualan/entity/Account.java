package com.indocyber.penjualan.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;

    @ManyToMany
    @JoinTable(name = "Cart",
            joinColumns=@JoinColumn(name="Username"),
            inverseJoinColumns=@JoinColumn(name="ProductCode"))
    private List<Product> productList;

    public void addProduct(Product product){
        if(this.productList == null){
            this.productList = new LinkedList<>();
        }
        this.productList.add(product);
    }
}
