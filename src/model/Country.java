package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country 
implements Table
{
    public IntegerProperty  ID_Country;
    public StringProperty   name;
    
    public Country(int int1, String string)
    {
        ID_Country = new SimpleIntegerProperty(int1);
        name = new SimpleStringProperty(string);
    }

    public Country()
    {
        ID_Country = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }
}
