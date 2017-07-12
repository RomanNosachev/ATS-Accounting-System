package database;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ATS;

public class DB_ATS
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'ATS' ('ID_ATS' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' text, 'ID_District' INTEGER, "
                + "'Address' text, 'Year' INTEGER, 'ID_ATS_Type' INTEGER, 'License' text, 'ID_Tariff' INTEGER);");        
    }
    
    public static void writeDB(ATS curr) throws SQLException
    { 
        statement.execute("INSERT INTO 'ATS' ('Name', 'ID_District', 'Address', 'Year', 'ID_ATS_Type', 'License', 'ID_Tariff') "
                + "VALUES ('" + curr.name.get() + "', '" + curr.ID_District.get() + "', '" + curr.address.get() 
                + "', '" + curr.year.get() + "', '" + curr.ID_ATS_Type.get() + "', '" + curr.license.get() 
                + "', '" + curr.ID_Tariff.get() + "'); ");  
    }
    
    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM ATS");
        
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
    
    public static ObservableList<ATS> getData(String query) throws SQLException
    {
        query += " ATS";
                
        ObservableList<ATS> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new ATS(resultSet.getInt("ID_ATS"), 
                    resultSet.getString("Name"), 
                    String.valueOf(resultSet.getInt("ID_District")),
                    resultSet.getString("Address"), 
                    String.valueOf(resultSet.getInt("Year")),
                    String.valueOf(resultSet.getInt("ID_ATS_Type")), 
                    resultSet.getString("License"), 
                    String.valueOf(resultSet.getInt("ID_Tariff"))));
            
            maxID = resultSet.getInt("ID_ATS");
        }

        return data;
    }
}
