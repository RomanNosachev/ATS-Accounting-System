
import java.io.IOException;
import java.sql.SQLException;

import database.DB_Users;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login 
extends Application 
{        
    private Stage           primaryStage;
    private BorderPane      rootLayout;
    
    @FXML
    private TextField        loginField;
    
    @FXML
    private PasswordField    passField;
    
    @FXML
    private Button           loginButton;
    
    public Login()
    {
        loginField = new TextField();
        passField = new PasswordField();
        loginButton = new Button(); 
    }
    
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ATS");
		this.primaryStage.setWidth(250);
		this.primaryStage.setHeight(300);
		
		initRootLayout();
		showForm();
	}

	public void initRootLayout() 
	{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@FXML
	public void login() throws ClassNotFoundException, SQLException
	{
	    DB_Users.connect();
	    DB_Users.createDB();
	    
	    if (DB_Users.login(loginField.getText(), passField.getText()))
	    {
	        try
            {
                loadMainForm();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
	    }
	    else
	    {
	        
	    }
	    
	    DB_Users.closeDB();
	}

	private void loadMainForm() throws Exception
	{
	    new Main().start(new Stage());
	    
	    primaryStage = (Stage) loginButton.getScene().getWindow();
	    primaryStage.close();
	}
	
	public void showForm() 
	{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("Login.fxml"));
            AnchorPane view = (AnchorPane) loader.load();

            rootLayout.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
