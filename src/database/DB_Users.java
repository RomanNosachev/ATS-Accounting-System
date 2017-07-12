package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Users 
extends AbstractDB
{
    private static Connection    connection;
    private static Statement     statement;
    private static ResultSet     resultSet;
    
    private static boolean     logged;
    
    public static void connect() throws ClassNotFoundException, SQLException
    {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DATABASE.s3db");        
    }
    
    public static void createDB() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        statement.execute(
                "CREATE TABLE if not exists 'Users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Login' text, 'Password' text);");        
    }
    
    public boolean isLogged()
    {
        return logged;
    }

    public static boolean login(String login, String pass) throws SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Users");
        
        while (resultSet.next())
        {                        
            if (login.equals(resultSet.getString("Login")) && pass.equals(resultSet.getString("Password")))
            {                
                logged = true;
                
                return true;
            }
        }
        
        return false;
    }
    
    public static void readDB() throws ClassNotFoundException, SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Users");
        
        while (resultSet.next())
        {
            int id = resultSet.getInt("id");
            
            String name = resultSet.getString("Login");
            String phone = resultSet.getString("Password");
            
            System.out.println("ID = " + id);
            System.out.println("Login = " + name);
            System.out.println("Password = " + phone);
            System.out.println();
        }        
    }
    
    public static void writeDB() throws SQLException
    {
           statement.execute("INSERT INTO 'Users' ('Login', 'Password') VALUES ('Petya', '125453'); ");
           statement.execute("INSERT INTO 'Users' ('Login', 'Password') VALUES ('Vasya', '321789'); ");
           statement.execute("INSERT INTO 'Users' ('Login', 'Password') VALUES ('Masha', '456123'); ");
           statement.execute("INSERT INTO 'Users' ('Login', 'Password') VALUES ('admin', '1111'); ");          
    }
    
    public static void closeDB() throws ClassNotFoundException, SQLException
    {
        connection.close();
        statement.close();
        resultSet.close();        
    }
}
