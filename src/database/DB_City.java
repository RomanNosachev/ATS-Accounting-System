package database;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.City;

public class DB_City 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'City' ('ID_City' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text);");
    }

    public static void writeDB(City currCity) throws SQLException
    {
        statement.execute("INSERT INTO 'City' ('Name') VALUES ('" + currCity.name.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM City");
        
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

    public static ObservableList<City> getData(String query) throws SQLException
    {
        query += " City";
        
        ObservableList<City> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new City(resultSet.getInt("ID_City"), 
                    resultSet.getString("Name")));
            
            maxID = resultSet.getInt("ID_City");
        }

        return data;
    }
}
