package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class City 
implements Table
{
    public IntegerProperty  ID_City;
    public StringProperty   name;
    
    public City(int int1, String string)
    {
        ID_City = new SimpleIntegerProperty(int1);
        name = new SimpleStringProperty(string);
    }

    public City()
    {
        ID_City = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }
}
