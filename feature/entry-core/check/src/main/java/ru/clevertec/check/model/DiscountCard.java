package main.java.ru.clevertec.check.model;

import java.util.Objects;

public class DiscountCard {

    private int id;
    private int number;
    private int discountAmount;

    public DiscountCard() {
    }

    public DiscountCard(int id, int number, int discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return getId() == that.getId() &&
                getNumber() == that.getNumber() &&
                getDiscountAmount() == that.getDiscountAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getDiscountAmount());
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number=" + number +
                ", discountAmount=" + discountAmount +
                '}';
    }
}
