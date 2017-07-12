package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TariffType 
implements Table
{
    public IntegerProperty   ID_TariffType;
    public StringProperty    name;
    
    public TariffType(int iD_TariffType, String name)
    {
        super();
        ID_TariffType = new SimpleIntegerProperty(iD_TariffType);
        this.name = new SimpleStringProperty(name);
    }
    
    public TariffType()
    {
        super();
        ID_TariffType = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }
}
