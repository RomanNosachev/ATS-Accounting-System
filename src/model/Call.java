package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Call 
implements Table
{
    public StringProperty   abonent;
    public StringProperty   country;
    public StringProperty   city;
    public StringProperty   number;
    public StringProperty   date;
    public StringProperty   duration;
    public StringProperty   time;
    public StringProperty   tariff;
    
    public Call(String abonent, String country, String city, String number,
            String date, String duration, String time, String tariff)
    {
        super();
        this.abonent = new SimpleStringProperty(abonent);
        this.country = new SimpleStringProperty(country);
        this.city = new SimpleStringProperty(city);
        this.number = new SimpleStringProperty(number);
        this.date = new SimpleStringProperty(date);
        this.duration = new SimpleStringProperty(duration);
        this.time = new SimpleStringProperty(time);
        this.tariff = new SimpleStringProperty(tariff);
    }

    public Call()
    {
        super();
        this.abonent = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.duration = new SimpleStringProperty();
        this.time = new SimpleStringProperty();
        this.tariff = new SimpleStringProperty();
    }
}
