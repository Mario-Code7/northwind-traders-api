package com.pluralsight.NorthwindTradersAPI.data;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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

    public Product insert(Product product) {
        String query =  """
            INSERT INTO products (ProductName, CategoryID, UnitPrice)
            VALUES (?, ?, ?)
            """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getCategoryId());
            statement.setDouble(3, product.getUnitPrice());

            statement.executeUpdate();

//            try (ResultSet keys = statement.getGeneratedKeys()) {
//                if (keys.next()) {
//                    product.setProductId(keys.getInt(1));
//                }
//            }

        } catch (SQLException e) {
            System.out.println("There was an error inserting data. Please try again.");
            e.printStackTrace();
        }
        return product;
    }

    public void update(int productId, Product product) {
        String query =  """
            UPDATE products
            SET ProductName = ?, CategoryID = ?, UnitPrice = ?
            WHERE ProductID = ?
            """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setDouble(3, product.getUnitPrice());
            preparedStatement.setInt(4, product.getProductId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating product!", e);
        }
    }

    public void delete(int id) {
        String query =  """
            DELETE FROM products
            WHERE ProductID = ?;
            """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating product!", e);
        }
    }
}
