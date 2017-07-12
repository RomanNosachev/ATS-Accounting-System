package database;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

public class DB_Country 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'Country' ('ID_Country' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text);");
    }

    public static void writeDB(Country currCountry) throws SQLException
    {
        statement.execute("INSERT INTO 'Country' ('Name') VALUES ('" + currCountry.name.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Country");
        
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

    public static ObservableList<Country> getData(String query) throws SQLException
    {
        query += " Country";
        
        ObservableList<Country> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new Country(resultSet.getInt("ID_Country"), 
                    resultSet.getString("Name")));
            
            maxID = resultSet.getInt("ID_Country");
        }

        return data;
    }
}
