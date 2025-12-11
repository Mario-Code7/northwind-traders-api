package com.pluralsight.NorthwindTradersAPI.data;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcProductDao implements ProductDao {

    private final DataSource dataSource;


    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = """
                select ProductID, ProductName, CategoryID, UnitPrice
                from products
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product(
                            resultSet.getInt("ProductID"),
                            resultSet.getString("ProductName"),
                            resultSet.getInt("CategoryID"),
                            resultSet.getDouble("UnitPrice")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getById(int productId) {
        Product product = null;
        String query = """
            select ProductID, ProductName, CategoryID, UnitPrice
            from products
            WHERE productId = ?
            """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, productId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product (
                            resultSet.getInt("ProductID"),
                            resultSet.getString("ProductName"),
                            resultSet.getInt("CategoryID"),
                            resultSet.getDouble("UnitPrice"))
                            ;
                }
            }
        } catch(SQLException e) {
            System.out.println("There is an error retrieving the data. Please try again!");
            e.printStackTrace();
        }
        return product;
    }
}
