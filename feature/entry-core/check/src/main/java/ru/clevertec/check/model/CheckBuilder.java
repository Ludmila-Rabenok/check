package main.java.ru.clevertec.check.model;

import java.util.List;

public class CheckBuilder {

    Order order;
    List<Position> positions;
    double totalPrice;
    double totalDiscount;
    double totalWithDiscount;

    public CheckBuilder() {
    }

    public CheckBuilder setOrder(Order order) {
        this.order = order;
        return this;
    }

    public CheckBuilder setPositions(List<Position> positions) {
        this.positions = positions;
        return this;
    }

    public CheckBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public CheckBuilder setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }

    public CheckBuilder setTotalWithDiscount(double totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
        return this;
    }

    public Check build() {
        return new Check(this);
    }
}
