package ru.clevertec.check.repository.DB;

import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.util.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public final Product create(Product product) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection
                     .prepareStatement(getSaveQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareSaveQuery(pst, product);
            final int count = pst.executeUpdate();
            if (count == 1) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                } else {
                    throw new InternalServerException();
                }
            }
            return product;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public final Product readById(int id) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection
                     .prepareStatement(getSelectByIdQuery())) {
            pst.setInt(1, id);
            final ResultSet resultSet = pst.executeQuery();
            final List<Product> resultSetList = parseResultSet(resultSet);
            if (resultSetList.isEmpty()) {
                return null;
            }
            return resultSetList.iterator().next();
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    @Override
    public final boolean update(Product product) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(getUpdateQuery())) {
            prepareUpdateQuery(pst, product);
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

    @Override
    public final boolean changeQuantityInStock(int id, int newQuantityInStock) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(getUpdateQuantityInStockQuery())) {
            pst.setInt(1, newQuantityInStock);
            pst.setInt(2, id);
            return pst.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private String getSaveQuery() {
        return "INSERT INTO product(description, price, quantity_in_stock, wholesale_product) " +
                "VALUES (?, ?, ?, ?)";
    }

    private String getSelectByIdQuery() {
        return "SELECT * FROM product WHERE id = ?";
    }


    private String getUpdateQuery() {
        return "UPDATE product SET description = ?, price = ?, quantity_in_stock = ?, wholesale_product = ?"
                + "WHERE id = ?";
    }

    private String getRemoveQuery() {
        return "DELETE FROM product WHERE id = ?";
    }

    private String getUpdateQuantityInStockQuery() {
        return "UPDATE product SET quantity_in_stock = ? WHERE id = ?";
    }

    protected void prepareSaveQuery(PreparedStatement pst, Product product) {
        try {
            pst.setString(1, product.getDescription());
            pst.setDouble(2, product.getPrice());
            pst.setInt(3, product.getQuantityInStock());
            pst.setBoolean(4, product.isWholesaleProduct());
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    protected List<Product> parseResultSet(ResultSet resultSet) {
        try {
            final List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                final int id = resultSet.getInt(1);
                final String description = resultSet.getString(2);
                final double price = resultSet.getDouble(3);
                final int quantityInStock = resultSet.getInt(4);
                final boolean wholesaleProduct = resultSet.getBoolean(5);
                final Product product = new Product(id, description, price, quantityInStock, wholesaleProduct);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }

    private void prepareUpdateQuery(PreparedStatement pst, Product product) {
        try {
            pst.setString(1, product.getDescription());
            pst.setDouble(2, product.getPrice());
            pst.setInt(3, product.getQuantityInStock());
            pst.setBoolean(4, product.isWholesaleProduct());
            pst.setInt(5, product.getId());
        } catch (SQLException e) {
            throw new InternalServerException();
        }
    }
}
