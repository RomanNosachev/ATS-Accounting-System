package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SocialPosition
implements Table
{
    public IntegerProperty   ID_SocialPosition;
    public StringProperty    type;
    
    public SocialPosition(int int1, String string)
    {
        ID_SocialPosition = new SimpleIntegerProperty(int1);
        type = new SimpleStringProperty(string);
    }

    public SocialPosition()
    {
        ID_SocialPosition = new SimpleIntegerProperty();
        type = new SimpleStringProperty();
    }
}
