package ru.clevertec.check.repository.DB;

import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {

    @Override
    public final DiscountCard create(DiscountCard discountCard) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection
                     .prepareStatement(getSaveQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareSaveQuery(pst, discountCard);
            final int count = pst.executeUpdate();
            if (count == 1) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    discountCard.setId(rs.getInt(1));
                } else {
                    throw new InternalServerException();
                }
            }
            return discountCard;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public final DiscountCard readByNumber(int number) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection
                     .prepareStatement(getSelectByNumberQuery())) {
            pst.setInt(1, number);
            final ResultSet resultSet = pst.executeQuery();
            final List<DiscountCard> resultSetList = parseResultSet(resultSet);
            if (resultSetList.isEmpty()) {
                return null;
            }
            return resultSetList.iterator().next();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public final boolean update(DiscountCard discountCard) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(getUpdateQuery())) {
            prepareUpdateQuery(pst, discountCard);
            return pst.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public final boolean remove(int id) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(getRemoveQuery())) {
            pst.setInt(1, id);
            return pst.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private String getSaveQuery() {
        return "INSERT INTO discount_card(number, discount_amount) " +
                "VALUES (?, ?)";
    }

    protected void prepareSaveQuery(PreparedStatement pst, DiscountCard discountCard) {
        try {
            pst.setInt(1, discountCard.getNumber());
            pst.setInt(2, discountCard.getDiscountAmount());
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    protected List<DiscountCard> parseResultSet(ResultSet resultSet) {
        try {
            final List<DiscountCard> cards = new ArrayList<>();
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final int number = resultSet.getInt(2);
                final int discount_amount = resultSet.getInt(3);
                final DiscountCard card = new DiscountCard(id, number, discount_amount);
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private String getSelectByNumberQuery() {
        return "SELECT * FROM discount_card WHERE number = ?";
    }


    private String getUpdateQuery() {
        return "UPDATE discount_card SET number = ?, discount_amount = ? WHERE id = ?";
    }

    private void prepareUpdateQuery(PreparedStatement pst, DiscountCard discountCard) {
        try {
            pst.setInt(1, discountCard.getNumber());
            pst.setInt(2, discountCard.getDiscountAmount());
            pst.setInt(3, discountCard.getId());
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private String getRemoveQuery() {
        return "DELETE FROM discount_card WHERE id = ?";
    }
}
