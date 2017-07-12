package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exemption 
implements Table
{
    public IntegerProperty   ID_Exemption;
    public StringProperty    type;
    public StringProperty    terms;
    public StringProperty    tariff;
    
    public Exemption(int id, String type, String terms, String tariff)
    {
        ID_Exemption = new SimpleIntegerProperty(id);
        this.type = new SimpleStringProperty(type);
        this.terms = new SimpleStringProperty(terms);
        this.tariff = new SimpleStringProperty(tariff);
    }

    public Exemption()
    {
        ID_Exemption = new SimpleIntegerProperty();
        type = new SimpleStringProperty();
        terms = new SimpleStringProperty();
        tariff = new SimpleStringProperty();
    }
}
