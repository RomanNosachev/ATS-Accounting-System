import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Queries 
extends Application 
{
    @SuppressWarnings("rawtypes")
    private ObservableList<ObservableList>       data;
    
    private static Connection                    connection;
    private static Statement                     statement;
    private static ResultSet                     resultSet;
    
    @FXML
    @SuppressWarnings("rawtypes")
    private TableView                            tableView;
    
    @FXML
    private BorderPane                           rootLayout;
    @FXML
    private Stage                                primaryStage;

    private final static int cost = 50;      
    
    private static String   query;
    
    private final String    symmetricUnionWithoutCondition      = "SELECT * FROM City JOIN Country";
    private final String    symmetricUnionWithTextCondition     = "SELECT * FROM City JOIN Country ON City.Name = Country.Name";
    private final String    symmetricUnionWithDateCondition     = "SELECT * FROM Tariff JOIN TariffType ON Tariff.StartDate = Tariff.FinishDate";
    private final String    symmetricUnionWithBeetwen           = "SELECT * FROM Tariff JOIN TariffType ON Tariff.Cost BETWEEN 50.000 AND 100.000";
    private final String    symmetricUnionWithLike              = "SELECT * FROM City JOIN Country ON City.Name LIKE 'М%'";
    private final String    symmetricUnionWithIn                = "SELECT * FROM City JOIN Country ON City.Name IN ('Донецк', 'Макеевка')";
    private final String    leftUnion                           = "SELECT * FROM Country LEFT OUTER JOIN City";
    private final String    symmetricUnionWithDistinct          = "SELECT DISTINCT City.Name FROM City JOIN Country";
    private final String    requestWithAggregation              = "SELECT MAX(Cost) FROM Tariff";
    private final String    requestWithoutCondition             = "SELECT AVG(Cost) FROM Tariff";
    private final String    requestWithDataCondition            = "SELECT AVG(Cost) FROM Tariff WHERE Tariff.ID_TariffType = 3";
    private final String    requestWithGroupCondition           = "SELECT AVG(Cost) FROM Tariff GROUP BY Cost";
    private final String    requestWithDataAndGroupCondition    = "SELECT AVG(Cost) FROM Tariff WHERE Tariff.Cost > 50 GROUP BY Cost";
    private final String    subordinatedRequestWithComparison   = "SELECT * FROM Tariff WHERE Tariff.Cost > (SELECT ID_Tariff FROM Tariff)";
    private final String    subordinatedRequestWithExpression   = "SELECT * FROM Tariff WHERE Tariff.Cost IN (SELECT ID_Tariff FROM Tariff)";
    private final String    subordinatedRequestWithExistence    = "SELECT * FROM Tariff WHERE EXISTS (SELECT * FROM Tariff WHERE Tariff.Cost > 200)";
    private final String    parametricRequest                   = "SELECT * FROM Tariff WHERE Tariff.Cost = " + cost;
    private final String    symmetricUnion                      = "SELECT * FROM City JOIN Country";
    
    @Override
    public void start(Stage primaryStage) 
    {        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Запросы");
        this.primaryStage.setWidth(800);
        this.primaryStage.setHeight(600);
        
        initRootLayout();
        showForm();    
    }
    
    private void initRootLayout() 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);         
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showForm() 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("Queries.fxml"));
            AnchorPane view = (AnchorPane) loader.load();

            rootLayout.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public static void connect() throws ClassNotFoundException, SQLException
    {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DATABASE.s3db"); 
    }

    public static void createDB() throws SQLException
    {
        statement = connection.createStatement();
    }
    
    public static void closeDB() throws ClassNotFoundException, SQLException
    {
        if (connection != null)
            connection.close();
        
        if (statement != null)
            statement.close();
        
        if (resultSet != null)
            resultSet.close();
    }
    
    @FXML
    public void exit() throws ClassNotFoundException, SQLException
    {
        closeDB();
        
        System.exit(0);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void generateTable()
    {
        tableView.getColumns().clear();
        
        data = FXCollections.observableArrayList();
        
        try
        {
            for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++)
            {
                final int j = i;
                
                TableColumn col = new TableColumn(resultSet.getMetaData().getColumnName(i + 1));
                
                col.setCellValueFactory(
                        new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param)
                            {
                                return new SimpleStringProperty(param.getValue().get(j).toString());
                            }
                        });
                
                tableView.getColumns().addAll(col);
                
                System.out.println("Column [" + i + "] ");
            }
            
            while (resultSet.next())
            {
                ObservableList<String> row = FXCollections.observableArrayList();
                
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
                {
                    row.add(resultSet.getString(i));
                }
                
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            
            tableView.setItems(data);
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    
    public void request() throws ClassNotFoundException, SQLException
    {
        connect();
        createDB();
        
        resultSet = statement.executeQuery(query);
        generateTable();
        
        closeDB();
    }
    
    @FXML
    private void symmetricUnionWithoutCondition()
    {
        query = symmetricUnionWithoutCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void symmetricUnionWithTextCondition()
    {
        query = symmetricUnionWithTextCondition; 
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void symmetricUnionWithDateCondition()
    {
        query = symmetricUnionWithDateCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void symmetricUnionWithBeetwen()
    {
        query = symmetricUnionWithBeetwen;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void symmetricUnionWithLike()
    {
        query = symmetricUnionWithLike;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void symmetricUnionWithIn()
    {
        query = symmetricUnionWithIn;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void leftUnion()
    {
        query = leftUnion;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void symmetricUnionWithDistinct()
    {
        query = symmetricUnionWithDistinct;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void requestWithAggregation()
    {
        query = requestWithAggregation;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void requestWithoutCondition()
    {
        query = requestWithoutCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void requestWithDataCondition()
    {
        query = requestWithDataCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void requestWithGroupCondition()
    {
        query = requestWithGroupCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void requestWithDataAndGroupCondition()
    {
        query = requestWithDataAndGroupCondition;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void subordinatedRequestWithComparison()
    {
        query = subordinatedRequestWithComparison;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void subordinatedRequestWithExpression()
    {
        query = subordinatedRequestWithExpression;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void subordinatedRequestWithExistence()
    {
        query = subordinatedRequestWithExistence;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void parametricRequest()
    {
        query = parametricRequest;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void symmetricUnion()
    {
        query = symmetricUnion;
        
        try
        {
            request();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
	public static void main(String[] args) 
	{
		launch(args);
	}
}
