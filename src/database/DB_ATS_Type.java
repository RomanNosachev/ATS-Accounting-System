package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ATS_Type;

public class DB_ATS_Type 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'ATS_Type' ('ID_ATS_Type' INTEGER PRIMARY KEY AUTOINCREMENT, 'Type' text);");
    }

    public static void writeDB(ATS_Type currATS_Type) throws SQLException
    {
        statement.execute("INSERT INTO 'ATS_Type' ('Type') VALUES ('" + currATS_Type.type.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM ATS_Type");
        
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

    public static ObservableList<ATS_Type> getData(String query) throws SQLException
    {
        query += " ATS_Type";
        
        ObservableList<ATS_Type> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new ATS_Type(resultSet.getInt("ID_ATS_Type"), 
                    resultSet.getString("Type")));
            
            maxID = resultSet.getInt("ID_ATS_Type");
        }

        return data;
    }

    public static ResultSet get(String query) throws SQLException
    {
        query += " ATS_Type";
        
        resultSet = statement.executeQuery(query); 
        
        return resultSet;
    }
}
