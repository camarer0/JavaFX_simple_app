package kr.kr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, Integer> eventIDColumn;
    @FXML
    private TableColumn<Event, String> eventNameColumn;
    @FXML
    private TableColumn<Event, Integer> priceColumn;
    @FXML
    private TableColumn<Event, String> dateColumn;
    @FXML
    private TableColumn<Event, String> artistColumn;
    @FXML
    protected void showButtonClick(){pieChart.setVisible(true);
        pieChart.setData(getPieChart());}
    @FXML
    protected void hideButtonClick(){pieChart.setVisible(false);}
    @FXML
    private TextField inputEventID;
    @FXML
    private TextField inputEventName;
    @FXML
    private TextField inputPrice;
    @FXML
    private TextField inputDate;
    @FXML
    private TextField inputArtist;
    @FXML
    protected void addButton(){
        int eventID = Integer.parseInt(inputEventID.getText());
        String eventName = inputEventName.getText();
        int price = Integer.parseInt(inputPrice.getText());
        String date = inputDate.getText();
        String artist = inputArtist.getText();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", "database.db"));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            String query = String.format("INSERT INTO Event VALUES(%d,'%s',%d,'%s','%s')",eventID,eventName,
                    price,date,artist);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        tableView.setItems(getEvents());
        pieChart.setData(getPieChart());

    }
    @FXML
    protected void deleteButton(){
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        int eventID = tableView.getItems().get(selectedID).getEventID();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", "database.db"));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            String query = String.format("DELETE FROM Event WHERE eventID = %d",eventID);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        tableView.setItems(getEvents());
        pieChart.setData(getPieChart());

    }

    public ObservableList<Event> getEvents() {
        ObservableList<Event> events = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", "database.db"));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("select * from Event");
            while (rs.next()) {
                int eventID = rs.getInt("eventID");
                String eventName = rs.getString("eventName");
                int price = rs.getInt("price");
                String date = rs.getString("date");
                String artist = rs.getString("artist");
                events.add(new Event(eventID, eventName,price,date,artist));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return events;

    }
    public ObservableList<PieChart.Data> getPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", "database.db"));
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("Select artist, COUNT(artist) FROM Event GROUP BY(artist)");
            while (rs.next()) {
                String artist = rs.getString("artist");
                int count = rs.getInt("COUNT(artist)");
                pieChartData.add(new PieChart.Data(artist,count));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        return pieChartData;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        tableView.setItems(getEvents());
        pieChart.setData(getPieChart());

    }




}