package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class Shirt implements Serializable {
    @Id
    @Column(name = "tshirt_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shirtId;
    private String size;

    private String color;

    private Integer quantity;

    private String description;

    private Double price;


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getShirtId() {
        return shirtId;
    }

    public void setShirtId(Integer shirtId) {
        this.shirtId = shirtId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shirt)) return false;
        Shirt shirt = (Shirt) o;
        return Objects.equals(shirtId, shirt.shirtId) && Objects.equals(size, shirt.size) && Objects.equals(color, shirt.color) && Objects.equals(quantity, shirt.quantity) && Objects.equals(description, shirt.description) && Objects.equals(price, shirt.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shirtId, size, color, quantity, description, price);
    }
}
