package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tariff 
implements Table
{
    public IntegerProperty   ID_Tariff;
    public StringProperty    tariffType;
    public StringProperty    startDate;
    public StringProperty    finishDate;
    public StringProperty    cost;
    
    private IntegerProperty   ID_TariffType;
    
    public Tariff(int iD_Tariff, String tariffType, String startDate,
            String finishDate, String cost)
    {
        super();
        ID_Tariff = new SimpleIntegerProperty(iD_Tariff);
        this.tariffType = new SimpleStringProperty(tariffType);
        this.startDate = new SimpleStringProperty(startDate);
        this.finishDate = new SimpleStringProperty(finishDate);
        this.cost = new SimpleStringProperty(cost);
    }

    public void setID(int id_TariffType)
    {
        ID_TariffType = new SimpleIntegerProperty(id_TariffType);
    }
    
    public Tariff()
    {
        super();
        ID_Tariff = new SimpleIntegerProperty();
        this.tariffType = new SimpleStringProperty();
        this.startDate = new SimpleStringProperty();
        this.finishDate = new SimpleStringProperty();
        this.cost = new SimpleStringProperty();
    }

    public int getID_TariffType()
    {
        return ID_TariffType.get();
    }
}
