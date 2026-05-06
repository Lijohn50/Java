package com.example.demo;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DressService implements DressInterface {

    @Override
    public void insert(Product product) {

        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO dressdata VALUES('" + product.getName() + "','" + product.getType() + "','" + product.getColor() + "'," + product.getPrice() + ",'" + product.getLastPurchase() + "'," + product.getQuantity() + ",'" + product.getBoosted() + "'," + product.getDiscount() + ",'" + product.getDetails() + "','" + product.getGender() + "','" + product.getSize() + "','" + product.getImage() + "');";
            statement.execute(query);
        }
        catch(
                SQLException ex)
        {
            System.out.println("failed to connect");
            ex.printStackTrace();
        }
    }
}
