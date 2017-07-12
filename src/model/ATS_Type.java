package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ATS_Type 
implements Table
{
    public IntegerProperty     ID_ATS_Type;
    public StringProperty      type;
    
    public ATS_Type(int int1, String string)
    {
        ID_ATS_Type = new SimpleIntegerProperty(int1);
        type = new SimpleStringProperty(string);
    }

    public ATS_Type()
    {
        ID_ATS_Type = new SimpleIntegerProperty();
        type = new SimpleStringProperty();
    }
}
