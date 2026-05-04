package com.example.demo.service;

import com.example.demo.adminModel.AddGame;
import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.adminModel.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GameService {

    public void addGame(AddGame addGame)
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "INSERT INTO gamelist VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, addGame.getTitle());
            preparedStatement.setString(2, addGame.getGenre());
            preparedStatement.setString(3, addGame.getType());
            preparedStatement.setString(4, addGame.getPlatform());
            preparedStatement.setString(5, addGame.getReleaseDate());
            preparedStatement.setDouble(6, addGame.getPrice());
            preparedStatement.setString(7, addGame.getDescription());
            preparedStatement.setString(8, addGame.getImage());
            preparedStatement.setString(9, addGame.getCpu());
            preparedStatement.setString(10, addGame.getWindows());
            preparedStatement.setString(11, addGame.getRam());
            preparedStatement.setString(12, addGame.getGpu());
            preparedStatement.setString(13, addGame.getStorage());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    public void removeGame(Product product)
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "DELETE FROM gamelist WHERE title = ? and genre = ? and  type = ? and platform = ? and release_date = ? and price = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getGenre());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setString(4, product.getPlatform());
            preparedStatement.setString(5, product.getReleaseDate());
            preparedStatement.setDouble(6, product.getPrice());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }
    }

    public void updateGame(Product product, AddGame addGame)
    {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "UPDATE gamelist SET title = ?, genre = ?, type = ?, platform = ?, release_date = ?, price = ?, image = ?, cpu = ?, windows = ?, ram = ?, gpu = ?, storage = ? WHERE title = ? and genre = ? and type = ? and platform = ? and release_date = ? and price = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, addGame.getTitle());
            preparedStatement.setString(2, addGame.getGenre());
            preparedStatement.setString(3, addGame.getType());
            preparedStatement.setString(4, addGame.getPlatform());
            preparedStatement.setString(5, addGame.getReleaseDate());
            preparedStatement.setDouble(6, addGame.getPrice());
            preparedStatement.setString(7, addGame.getImage());
            preparedStatement.setString(8, addGame.getCpu());
            preparedStatement.setString(9, addGame.getWindows());
            preparedStatement.setString(10, addGame.getRam());
            preparedStatement.setString(11, addGame.getGpu());
            preparedStatement.setString(12, addGame.getStorage());
            preparedStatement.setString(13, product.getTitle());
            preparedStatement.setString(14, product.getGenre());
            preparedStatement.setString(15, product.getType());
            preparedStatement.setString(16, product.getPlatform());
            preparedStatement.setString(17, product.getReleaseDate());
            preparedStatement.setString(18, String.valueOf(product.getPrice()));
            preparedStatement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("failed to connect to database");
            e.printStackTrace();
        }
    }
}
