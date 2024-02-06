package com.yvivas.springboot.api.springbootcrud.entities;

// import com.yvivas.springboot.api.springbootcrud.validation.IsExistsDb;
import com.yvivas.springboot.api.springbootcrud.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message="{NotBlank.product.name}")   //Validar que no puede ir vacía el String
    @IsRequired
    @Size(min = 3, max = 20)
    private String name;

    @Min(500)
    @NotNull(message="{NotNull.product.price}")    // Objeto = NotNull
    private Integer price;
    
    //Validar que no puede ir vacía el String
    // @NotBlank(message="{NotBlank.product.description}")  
    @IsRequired
    private String description;

    // @IsExistsDb
    @IsRequired
    private String sku;

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
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
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    
}
