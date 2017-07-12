package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.District;

public class DB_District 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'District' ('ID_District' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text);");
    }

    public static void writeDB(District currDistrict) throws SQLException
    {
        statement.execute("INSERT INTO 'District' ('Name') VALUES ('" + currDistrict.name.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM District");
        
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

    public static ResultSet get(String query) throws SQLException
    {
        query += " District";
        
        resultSet = statement.executeQuery(query); 
        
        return resultSet;
    }
    
    public static ObservableList<District> getData(String query) throws SQLException
    {
        query += " District";
        
        ObservableList<District> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new District(resultSet.getInt("ID_District"), 
                    resultSet.getString("Name")));
            
            maxID = resultSet.getInt("ID_District");
        }

        return data;
    }
}
