package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TariffType;

public class DB_TariffType 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'TariffType' ('ID_TariffType' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text);");
    }

    public static void writeDB(TariffType currTariffType) throws SQLException
    {
        statement.execute("INSERT INTO 'TariffType' ('Name') VALUES ('" + currTariffType.name.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM TariffType");
        
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

    public static ObservableList<TariffType> getData(String query) throws SQLException
    {
        query += " TariffType";
        
        ObservableList<TariffType> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new TariffType(resultSet.getInt("ID_TariffType"), 
                    resultSet.getString("Name")));
            
            maxID = resultSet.getInt("ID_TariffType");
        }

        return data;
    }

    public static ResultSet get(String query) throws SQLException
    {
        query += " TariffType";
        
        resultSet = statement.executeQuery(query); 
        
        return resultSet;
    }
}
