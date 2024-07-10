package ru.clevertec.check.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Position {

    private Product product;
    private int quantity;
    private double totalPricePosition;
    private double discountPosition;
//
//    public Position() {
//    }
//
//    public Position(Product product, int quantity, double totalPricePosition, double discountPosition) {
//        this.product = product;
//        this.quantity = quantity;
//        this.totalPricePosition = totalPricePosition;
//        this.discountPosition = discountPosition;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getTotalPricePosition() {
//        return totalPricePosition;
//    }
//
//    public void setTotalPricePosition(double totalPricePosition) {
//        this.totalPricePosition = totalPricePosition;
//    }
//
//    public double getDiscountPosition() {
//        return discountPosition;
//    }
//
//    public void setDiscountPosition(double discountPosition) {
//        this.discountPosition = discountPosition;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Position position = (Position) o;
//        return getQuantity() == position.getQuantity() &&
//                Double.compare(position.getTotalPricePosition(), getTotalPricePosition()) == 0 &&
//                Double.compare(position.getDiscountPosition(), getDiscountPosition()) == 0 &&
//                Objects.equals(getProduct(), position.getProduct());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getProduct(), getQuantity(), getTotalPricePosition(), getDiscountPosition());
//    }
//
//    @Override
//    public String toString() {
//        return "Position{" +
//                "product=" + product +
//                ", quantity=" + quantity +
//                ", totalPricePosition=" + totalPricePosition +
//                ", discountPosition=" + discountPosition +
//                '}';
//    }
}
