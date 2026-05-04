package com.example.demo.admincontroller;

import com.example.demo.utility.ConnectionSingleton;
import com.example.demo.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminStats implements Initializable {

    @FXML
    private Label gameCountLabel;

    @FXML
    private Label totalSellLabel;

    @FXML
    private Label gameNameLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label gameCopyLabel;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private BarChart<String, Integer> genreChart;

    @FXML
    private Label gamesBoughtLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label purchaseDateLabel;

    @FXML
    private Label totalSpentLabel;

    @FXML
    void addGameEvent(ActionEvent event) {
        HelloApplication.changeScene("adminaddgames");
    }

    @FXML
    void customerInfoEvent(ActionEvent event) {
        HelloApplication.changeScene("customerinfo");
    }

    @FXML
    void dashboardEvent(ActionEvent event) {
        HelloApplication.changeScene("admindashboard");
    }

    @FXML
    void logoutEvent(ActionEvent event) {
        HelloApplication.changeScene("adminlogin");
    }

    @FXML
    void removeGameEvent(ActionEvent event) {
        HelloApplication.changeScene("adminremovegames");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try
        {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "SELECT * FROM selectedgames";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int totalGames = 0;
            double totalSells = 0;
            String mostSoldGame = "";
            String genre = "";
            int gameCount = 0;

            XYChart.Series<String, Integer> seriesNow = new XYChart.Series<>();
            seriesNow.setName("Game Count");

            XYChart.Data<String, Integer> dataNow = new XYChart.Data<>();
            //"Action", "Adventure", "RPG", "Shooter", "Strategy", "Simulation", "Sports","Open World"

            int action = 1;
            int adventure = 1;
            int shooter = 1;
            int sports = 1;
            int openWorld = 1;
            int strategy = 1;
            int simulation = 1;
            int rpg = 1;

            while(resultSet.next())
            {
                totalGames++;
                totalSells += resultSet.getDouble("price");

                if (resultSet.getString("genre").equals("Action"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Action", action++));
                }
                else if (resultSet.getString("genre").equals("Adventure"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Adventure", adventure++));
                }
                else if (resultSet.getString("genre").equals("RPG"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("RPG", rpg++));
                }
                else if (resultSet.getString("genre").equals("Shooter"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Shooter", shooter++));
                }
                else if (resultSet.getString("genre").equals("Strategy"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Strategy", strategy++));
                }
                else if (resultSet.getString("genre").equals("Simulation"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Simulation", simulation++));
                }
                else if (resultSet.getString("genre").equals("Sports"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Sports", sports++));
                }
                else if (resultSet.getString("genre").equals("Open World"))
                {
                    seriesNow.getData().add(new XYChart.Data<>("Open World", openWorld++));
                }

                int cnt = 0;
                String query2 = "SELECT * FROM selectedgames";
                ResultSet resultSet2 = connection.createStatement().executeQuery(query2);
                while(resultSet2.next())
                {
                    if(resultSet2.getString("title").equals(resultSet.getString("title")))
                    {
                        cnt++;
                        if(cnt >= gameCount)
                        {
                            gameCount = cnt;
                            mostSoldGame = resultSet2.getString("title");
                            genre = resultSet2.getString("genre");
                        }
                    }
                }
            }

            genreChart.getData().add(seriesNow);
            gameCopyLabel.setText(gameCount + " Copies");
            gameNameLabel.setText(mostSoldGame);
            genreLabel.setText(genre);
            gameCountLabel.setText(totalGames + " Copies");
            totalSellLabel.setText(String.format("%.2f", totalSells) + " BDT");

            String username = "";
            int games = 0;
            double totalSpent = 0;
            String lastPurchase = "";

            String date = "";//determines purchase date
            String query3 = "SELECT * FROM selectedgames";
            ResultSet resultSet3 = connection.createStatement().executeQuery(query3);
            while(resultSet3.next())
            {
                int cnt = 0;// determining top buyer
                double cnt2 = 0;// determines total spent
                String query4 = "SELECT * FROM selectedgames WHERE customer_username = '" +  resultSet3.getString("customer_username") + "'";
                ResultSet resultSet4 = connection.createStatement().executeQuery(query4);
                while(resultSet4.next())
                {
                    cnt++;
                    cnt2 +=  resultSet4.getDouble("price");
                    date = resultSet4.getString("purchase_date");
                }

                if(cnt > games)
                {
                    games = cnt;
                    username =  resultSet3.getString("customer_username");
                    totalSpent = cnt2;
                    lastPurchase = date;
                }
            }

            nameLabel.setText(username);
            gamesBoughtLabel.setText(Integer.toString(games));
            totalSpentLabel.setText(totalSpent + "");
            purchaseDateLabel.setText(lastPurchase);
        }
        catch(SQLException e)
        {
            System.out.println("failed to connect to database");
            e.printStackTrace();
        }
    }
}
