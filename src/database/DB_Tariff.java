package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Tariff;

public class DB_Tariff 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'Tariff' ('ID_Tariff' INTEGER PRIMARY KEY AUTOINCREMENT, 'ID_TariffType' INTEGER,"
                + "'StartDate' DATE, 'FinishDate' DATE, 'Cost' INTEGER)");      
    }
    
    public static void writeDB(Tariff curr) throws SQLException
    {        
        statement.execute("INSERT INTO 'Tariff' ('ID_TariffType', 'StartDate', 'FinishDate', 'Cost')"
                + "VALUES ('" + curr.getID_TariffType() + "','" + Date.valueOf(curr.startDate.get()) + "', '" 
                + Date.valueOf(curr.finishDate.get()) + "', '" + Integer.parseInt(curr.cost.get()) + "')");  
    }
    
    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Tariff");
        
        while (resultSet.next())
        {            
            String row = new String();
            
            for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++)
            {
                row += resultSet.getString(i) + '\n';
            }
            
            System.out.println(row);
            System.out.println();
        }        
    }
    
    public static ObservableList<Tariff> getData(String query) throws SQLException
    {
        query += " Tariff";
                
        ObservableList<Tariff> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
                        
        while (resultSet.next())
        {                                
            data.add(new Tariff(resultSet.getInt("ID_Tariff"), 
                    String.valueOf(resultSet.getInt("ID_TariffType")), 
                    resultSet.getString("StartDate"),
                    resultSet.getString("FinishDate"),
                    String.valueOf(resultSet.getInt("Cost"))));
            
            maxID = resultSet.getInt("ID_Tariff");
        }

        return data;
    }

    public static ResultSet get(String query) throws SQLException
    {
        query += " Tariff";
        
        resultSet = statement.executeQuery(query);
        
        return resultSet;
    }
}
