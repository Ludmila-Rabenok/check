package ru.clevertec.check.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Product {

    private int id;
    private String description;
    private double price;
    private int quantityInStock;
    private boolean wholesaleProduct;
//
//    public Product() {
//    }
//
//    public Product(int id, String description, double price, int quantityInStock, boolean wholesaleProduct) {
//        this.id = id;
//        this.description = description;
//        this.price = price;
//        this.quantityInStock = quantityInStock;
//        this.wholesaleProduct = wholesaleProduct;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getQuantityInStock() {
//        return quantityInStock;
//    }
//
//    public void setQuantityInStock(int quantityInStock) {
//        this.quantityInStock = quantityInStock;
//    }
//
//    public boolean isWholesaleProduct() {
//        return wholesaleProduct;
//    }
//
//    public void setWholesaleProduct(boolean wholesaleProduct) {
//        this.wholesaleProduct = wholesaleProduct;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Product product = (Product) o;
//        return isWholesaleProduct() == product.isWholesaleProduct() &&
//                Objects.equals(getId(), product.getId()) &&
//                Objects.equals(getDescription(), product.getDescription()) &&
//                Objects.equals(getPrice(), product.getPrice()) &&
//                Objects.equals(getQuantityInStock(), product.getQuantityInStock());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getDescription(), getPrice(), getQuantityInStock(), isWholesaleProduct());
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", quantityInStock=" + quantityInStock +
//                ", wholesaleProduct=" + wholesaleProduct +
//                '}';
//    }
}
