import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DB_Tariff;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class State 
extends Application 
{
    @FXML
    private Stage                           primaryStage;
    @FXML
    private BorderPane                      rootLayout;
    
    @FXML
    private BarChart<String, Number>        diagram;
    @FXML
    private CategoryAxis                    xAxis;
    @FXML
    private NumberAxis                      yAxis;
    
    @FXML
    private WebView                         webView;
    
    private ObservableList<String>          tariffID = FXCollections.observableArrayList();
    
    private XYChart.Series<String, Number>  series = new XYChart.Series<String, Number>();
    
    @Override
    public void start(final Stage stage) 
    {
        stage.setTitle("Статистика");
        stage.setWidth(500);
        stage.setHeight(500);
        Scene scene = new Scene(new Group());

        VBox root = new VBox(); 
        
        String html = 
                " <head>"
                + " <title> Статистика </title>"
                + "</head>"
                + "<body>"
                + " <table>"
                + "     <tr>"
                + "         <th> ID Тарифа </th>"
                + "         <th> Стоимость </th>"
                + "     </tr>";
        
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter("state.html");
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
        
        try
        {
            DB_Tariff.connect();
            DB_Tariff.createDB();
            
            ResultSet result = DB_Tariff.get("SELECT ID_Tariff FROM Tariff");
            
            while (result.next())
            {
                tariffID.add(String.valueOf(result.getInt("ID_Tariff")));
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        
        xAxis = new CategoryAxis();
        xAxis.setCategories(tariffID);
        
        yAxis = new NumberAxis();
        
        BarChart barChart = new BarChart(xAxis, yAxis);
        
        xAxis.setLabel("ID Тарифа");
        yAxis.setLabel("Стоимость");
        
        try
        {
            ResultSet result;
            
            result = DB_Tariff.get("SELECT Cost FROM Tariff");
            
            int i = 0;
            
            while (result.next())
            {
                series.getData().add(new XYChart.Data<String, Number>(tariffID.get(i), result.getInt("Cost")));
                html +=   "<tr>"
                        + " <td>" + tariffID.get(i) + "</td>"
                        + " <td>" + result.getInt("Cost") + "</td>"
                        + "</tr>";
                
                i++;
            }
            
            DB_Tariff.closeDB();

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
        barChart.getData().add(series);
        
        root.getChildren().addAll(barChart);
        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();
        
        html += "</table>"
                + "</body>";
        
        try
        {
            fileWriter.write(html);
            fileWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
	public static void main(String[] args) 
	{
		launch(args);
	}
}
