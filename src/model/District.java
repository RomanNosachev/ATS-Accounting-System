package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class District 
implements Table
{
    public IntegerProperty   ID_District;
    public StringProperty    name;
    
    public District(int int1, String string)
    {
        ID_District = new SimpleIntegerProperty(int1);
        name = new SimpleStringProperty(string);
    }

    public District()
    {
        ID_District = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
    }
}
