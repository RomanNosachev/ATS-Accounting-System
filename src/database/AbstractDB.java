package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDB 
{
    protected static Connection    connection;
    protected static Statement     statement;
    protected static ResultSet     resultSet;
    
    public static int      maxID;
    
    public static void connect() throws ClassNotFoundException, SQLException
    {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DATABASE.s3db"); 
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
}
