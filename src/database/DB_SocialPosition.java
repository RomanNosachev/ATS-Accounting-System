package database;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.SocialPosition;

public class DB_SocialPosition 
extends AbstractDB
{
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        
        statement.execute(
                "CREATE TABLE if not exists 'SocialPosition' ('ID_SocialPosition' INTEGER PRIMARY KEY AUTOINCREMENT, 'Type' text);");
    }

    public static void writeDB(SocialPosition currSocialPosition) throws SQLException
    {
        statement.execute("INSERT INTO 'SocialPosition' ('Type') VALUES ('" + currSocialPosition.type.get() + "');");  
    }

    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM SocialPosition");
        
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

    public static ObservableList<SocialPosition> getData(String query) throws SQLException
    {
        query += " SocialPosition";
        
        ObservableList<SocialPosition> data = FXCollections.observableArrayList();

        resultSet = statement.executeQuery(query);
        
        while (resultSet.next())
        {             
            data.add(new SocialPosition(resultSet.getInt("ID_SocialPosition"), 
                    resultSet.getString("Type")));
            
            maxID = resultSet.getInt("ID_SocialPosition");
        }

        return data;
    }
}
