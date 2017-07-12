package database;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Exemption;

public class DB_Exemption 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'Exemption' ('ID_Exemption' INTEGER PRIMARY KEY AUTOINCREMENT, 'Type' text,"
                + "'Terms' text, 'Tariff' text);");
    }

    public static void writeDB(Exemption currExemption) throws SQLException
    {
        statement.execute("INSERT INTO 'Exemption' ('Type', 'Terms', 'Tariff') VALUES ('" + currExemption.type.get() 
        + "', '" + currExemption.terms.get() + "', '" + currExemption.tariff.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Exemption");
        
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

    public static ObservableList<Exemption> getData(String query) throws SQLException
    {
        query += " Exemption";
        
        ObservableList<Exemption> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new Exemption(resultSet.getInt("ID_Exemption"), 
                    resultSet.getString("Type"),
                    resultSet.getString("Terms"),
                    resultSet.getString("Tariff")));
            
            maxID = resultSet.getInt("ID_Exemption");
        }

        return data;
    }
}
