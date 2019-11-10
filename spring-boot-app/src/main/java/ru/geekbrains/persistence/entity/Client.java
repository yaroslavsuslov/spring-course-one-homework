package ru.geekbrains.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany()
    @JoinTable(name = "product_to_client", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    public Client() {
    }

    public void addProduct(Product product) {
        productList.add(product);
        product.getClientList().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstAndLastName) {
        this.name = firstAndLastName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
