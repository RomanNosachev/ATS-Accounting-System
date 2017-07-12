package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ATS 
implements Table
{
    public IntegerProperty     ID_ATS;
    public StringProperty      name;
    public StringProperty      district;
    public StringProperty      address;
    public StringProperty      year;
    public StringProperty      ATS_Type;
    public StringProperty      license;
    public StringProperty      tariff;
    
    public IntegerProperty     ID_District;
    public IntegerProperty     ID_ATS_Type;
    public IntegerProperty     ID_Tariff;
    
    public ATS(int ID_ATS, String name, String ID_District, String address, String year, String ID_ATS_Type, String license, String ID_Tariff)
    {
        this.ID_ATS = new SimpleIntegerProperty(ID_ATS);
        this.name = new SimpleStringProperty(name);
        this.district = new SimpleStringProperty(ID_District);
        this.address = new SimpleStringProperty(address);
        this.year = new SimpleStringProperty(year);
        this.ATS_Type = new SimpleStringProperty(ID_ATS_Type);
        this.license = new SimpleStringProperty(license);
        this.tariff = new SimpleStringProperty(ID_Tariff);
    }

    public void setID(int id_District, int id_ATS_Type, int id_Tariff)
    {
        ID_District = new SimpleIntegerProperty(id_District);
        ID_ATS_Type = new SimpleIntegerProperty(id_ATS_Type);
        ID_Tariff = new SimpleIntegerProperty(id_Tariff);
    }

    public void setID_District(int iD_District)
    {
        ID_District = new SimpleIntegerProperty(iD_District);
    }

    public void setID_ATS_Type(int iD_ATS_Type)
    {
        ID_ATS_Type = new SimpleIntegerProperty(iD_ATS_Type);
    }
    
    public void setID_Tariff(int id_Tariff)
    {
        ID_Tariff = new SimpleIntegerProperty(id_Tariff);
    }
    
    public ATS()
    {
        ID_ATS = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        district = new SimpleStringProperty();
        address = new SimpleStringProperty();
        year = new SimpleStringProperty();
        ATS_Type = new SimpleStringProperty();
        license = new SimpleStringProperty();
        tariff = new SimpleStringProperty();
    }
}
