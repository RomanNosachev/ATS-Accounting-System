package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Abonent 
implements Table
{
    public IntegerProperty  ID_Abonent;
    public StringProperty   ATS;
    public StringProperty   FIO;
    public StringProperty   number;
    public StringProperty   address;
    public StringProperty   socialPosition;
    public StringProperty   exemption;
    
    public Abonent(int id, String ats, String fio, String number, String address, String socialPosition, String exemption)
    {
        ID_Abonent = new SimpleIntegerProperty(id);
        ATS = new SimpleStringProperty(ats);
        FIO = new SimpleStringProperty(fio);
        this.number = new SimpleStringProperty(number);
        this.address = new SimpleStringProperty(address);
        this.socialPosition = new SimpleStringProperty(socialPosition);
        this.exemption = new SimpleStringProperty(exemption);
    }
    
    public Abonent()
    {
        ID_Abonent = new SimpleIntegerProperty();
        ATS = new SimpleStringProperty();
        FIO = new SimpleStringProperty();
        number = new SimpleStringProperty();
        address = new SimpleStringProperty();
        socialPosition = new SimpleStringProperty();
        exemption = new SimpleStringProperty();
    }
}
