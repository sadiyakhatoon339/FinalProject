package com.example.ECommerce.entities.product;

import com.example.ECommerce.models.product.ProductVariationModel;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;


@Entity
@TypeDef(name="json",typeClass = JsonStringType.class)
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;


    private Long quantityAvailable;

    private Long price;

    @Type(type ="json")
    @Column(columnDefinition = "json")
    private Map<String,String> metadata;

    public ProductVariation() {
    }

    public ProductVariation(ProductVariationModel productVariationModel) {
        this.price = productVariationModel.getPrice();
        this.quantityAvailable = productVariationModel.getQuantity();
        this.metadata = productVariationModel.getMetadata();
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String,String> metadata) {
        this.metadata = metadata;
    }

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}