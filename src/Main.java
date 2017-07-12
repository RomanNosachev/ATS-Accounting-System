import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import database.DB_ATS;
import database.DB_ATS_Type;
import database.DB_City;
import database.DB_Country;
import database.DB_District;
import database.DB_Exemption;
import database.DB_SocialPosition;
import database.DB_Tariff;
import database.DB_TariffType;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ATS;
import model.ATS_Type;
import model.City;
import model.Country;
import model.District;
import model.Exemption;
import model.SocialPosition;
import model.Tariff;
import model.TariffType;

public class Main
extends Application
implements Initializable
{
    @FXML
    private Stage                                primaryStage;
    @FXML
    private BorderPane                           rootLayout;
    @FXML
    private TabPane                              tabPane;
    
    @FXML
    private TextField                            idATS;
    @FXML
    private TextField                            name;
    @FXML
    private ComboBox<String>                     district;
    @FXML
    private TextField                            address;
    @FXML
    private TextField                            year;
    @FXML
    private ComboBox<String>                     type;
    @FXML
    private TextField                            license;
    @FXML
    private ComboBox<String>                     tariff;
    @FXML
    private Button                               add;
    
    @FXML
    private TableView<ATS>                       view;
    @FXML
    private TableColumn<ATS, Integer>            idATS_Col;
    @FXML
    private TableColumn<ATS, String>             name_Col;
    @FXML
    private TableColumn<ATS, String>             district_Col;
    @FXML
    private TableColumn<ATS, String>             address_Col;
    @FXML
    private TableColumn<ATS, String>             year_Col;
    @FXML
    private TableColumn<ATS, String>             ATS_Type_Col;
    @FXML
    private TableColumn<ATS, String>             license_Col;
    @FXML
    private TableColumn<ATS, String>             tariff_Col;
    
    @FXML
    private TableView<District>                  districtView;
    @FXML
    private TableColumn<District, Integer>       idDistrictCol;
    @FXML
    private TableColumn<District, String>        districtNameCol;
    @FXML
    private TextField                            idDistrictField;
    @FXML
    private TextField                            districtNameField;
    @FXML
    private Button                               addDistrictButton;
    
    @FXML
    private TableView<ATS_Type>                  ATS_TypeView;
    @FXML
    private TableColumn<ATS_Type, Integer>       idATS_TypeCol;
    @FXML
    private TableColumn<ATS_Type, String>        ATS_TypeNameCol;
    @FXML
    private TextField                            idATS_TypeField;
    @FXML
    private TextField                            ATS_TypeNameField;
    @FXML
    private Button                               addATS_TypeButton;
    
    @FXML
    private TableView<SocialPosition>            socialPositionView;
    @FXML
    private TableColumn<SocialPosition, Integer> idSocialPositionCol;
    @FXML
    private TableColumn<SocialPosition, String>  socialPositionNameCol;
    @FXML
    private TextField                            idSocialPositionField;
    @FXML
    private TextField                            socialPositionNameField;
    @FXML
    private Button                               addSocialPositionButton;
    
    @FXML
    private TableView<TariffType>                tariffTypeView;
    @FXML
    private TableColumn<TariffType, Integer>     idTariffTypeCol;
    @FXML
    private TableColumn<TariffType, String>      tariffTypeNameCol;
    @FXML
    private TextField                            idTariffTypeField;
    @FXML
    private TextField                            tariffTypeNameField;
    @FXML
    private Button                               addTariffTypeButton;
    
    @FXML
    private TableView<Country>                   countryView;
    @FXML
    private TableColumn<Country, Integer>        idCountryCol;
    @FXML
    private TableColumn<Country, String>         countryNameCol;
    @FXML
    private TextField                            idCountryField;
    @FXML
    private TextField                            countryNameField;
    @FXML
    private Button                               addCountryButton;
    
    @FXML
    private TableView<City>                      cityView;
    @FXML
    private TableColumn<City, Integer>           idCityCol;
    @FXML
    private TableColumn<City, String>            cityNameCol;
    @FXML
    private TextField                            idCityField;
    @FXML
    private TextField                            cityNameField;
    @FXML
    private Button                               addCityButton;
    
    @FXML
    private TableView<Exemption>                 exemptionView;
    @FXML
    private TableColumn<Exemption, Integer>      idExemptionCol;
    @FXML
    private TableColumn<Exemption, String>       exemptionNameCol;
    @FXML
    private TableColumn<Exemption, String>       exemptionTermsCol;
    @FXML
    private TableColumn<Exemption, String>       exemptionTariffCol;
    @FXML
    private TextField                            idExemptionField;
    @FXML
    private TextField                            exemptionNameField;
    @FXML
    private TextField                            exemptionTermsField;
    @FXML
    private TextField                            exemptionTariffField;
    @FXML
    private Button                               addExemptionButton;
    
    @FXML
    private TableView<Tariff>                    tariffView;
    @FXML
    private TableColumn<Tariff, Integer>         idTariffCol;
    @FXML
    private TableColumn<Tariff, String>          idTariffTariffTypeCol;
    @FXML
    private TableColumn<Tariff, String>          tariffStartDateCol;
    @FXML
    private TableColumn<Tariff, String>          tariffFinishDateCol;
    @FXML
    private TableColumn<Tariff, String>          tariffCostCol;
    @FXML
    private TextField                            idTariffField;
    @FXML
    private ComboBox<String>                     tariffTariffType;
    @FXML
    private DatePicker                           tariffStartDateField;
    @FXML
    private DatePicker                           tariffFinishDateField;
    @FXML
    private TextField                            tariffCostField;
    @FXML
    private Button                               addTariffButton;
    @FXML
    private TextField                            searchATSField;
    @FXML
    private TextField                            deleteATSField;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Автоматическая телефонная станция");
        this.primaryStage.setWidth(800);
        this.primaryStage.setHeight(600);
        
        initRootLayout();
        showForm();        
    }
    
    private void initRootLayout() 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);         
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showForm() 
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Login.class.getResource("Main.fxml"));
            AnchorPane view = (AnchorPane) loader.load();

            rootLayout.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        refreshAll();
        
        initTariffComboBox();
        initDistrictComboBox();
        initATS_TypeComboBox();
        initTariffTypeComboBox();
                
        idATS_Col.setCellValueFactory(cellData -> cellData.getValue().ID_ATS.asObject());
        name_Col.setCellValueFactory(cellData -> cellData.getValue().name);
        district_Col.setCellValueFactory(cellData -> cellData.getValue().district);
        address_Col.setCellValueFactory(cellData -> cellData.getValue().address);
        year_Col.setCellValueFactory(cellData -> cellData.getValue().year);
        ATS_Type_Col.setCellValueFactory(cellData -> cellData.getValue().ATS_Type);
        license_Col.setCellValueFactory(cellData -> cellData.getValue().license);
        tariff_Col.setCellValueFactory(cellData -> cellData.getValue().tariff);
        
        idATS_TypeCol.setCellValueFactory(cellData -> cellData.getValue().ID_ATS_Type.asObject());
        ATS_TypeNameCol.setCellValueFactory(cellData -> cellData.getValue().type);
        
        idCityCol.setCellValueFactory(cellData -> cellData.getValue().ID_City.asObject());
        cityNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
        
        idCountryCol.setCellValueFactory(cellData -> cellData.getValue().ID_Country.asObject());
        countryNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
        
        idDistrictCol.setCellValueFactory(cellData -> cellData.getValue().ID_District.asObject());
        districtNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
        
        idExemptionCol.setCellValueFactory(cellData -> cellData.getValue().ID_Exemption.asObject());
        exemptionNameCol.setCellValueFactory(cellData -> cellData.getValue().type);
        exemptionTermsCol.setCellValueFactory(cellData -> cellData.getValue().terms);
        exemptionTariffCol.setCellValueFactory(cellData -> cellData.getValue().tariff);

        idSocialPositionCol.setCellValueFactory(cellData -> cellData.getValue().ID_SocialPosition.asObject());
        socialPositionNameCol.setCellValueFactory(cellData -> cellData.getValue().type);
        
        idTariffTypeCol.setCellValueFactory(cellData -> cellData.getValue().ID_TariffType.asObject());
        tariffTypeNameCol.setCellValueFactory(cellData -> cellData.getValue().name);
        
        idTariffCol.setCellValueFactory(cellData -> cellData.getValue().ID_Tariff.asObject());
        idTariffTariffTypeCol.setCellValueFactory(cellData -> cellData.getValue().tariffType);
        tariffStartDateCol.setCellValueFactory(cellData -> cellData.getValue().startDate);
        tariffFinishDateCol.setCellValueFactory(cellData -> cellData.getValue().finishDate);
        tariffCostCol.setCellValueFactory(cellData -> cellData.getValue().cost);
    }
    
    @FXML
    private void saveState()
    {
        new State().start(new Stage());
    }
    
    @FXML
    private void selectATS()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(0);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectTariff()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(1);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectAbonent()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(2);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectCall()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(3);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectDistrict()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(4);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectATS_Type()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(5);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectSocialPosition()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(6);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectTariffType()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(7);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectContry()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(8);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectCity()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(9);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void selectExemption()
    {
        SelectionModel<Tab> tabSwitch = tabPane.getSelectionModel();
        tabSwitch.select(10);
        tabPane.setSelectionModel((SingleSelectionModel<Tab>) tabSwitch);
    }
    
    @FXML
    private void clearSearchATSFeild()
    {
        searchATSField.clear();
    }
    
    @FXML
    private void clearDeleteATSField()
    {
        deleteATSField.clear();
    }
    
    @FXML
    private void searchATS()
    {
        try
        {
            DB_ATS.connect();
            DB_ATS.createDB();
            
            view.setItems(DB_ATS.getData("SELECT * FROM ATS WHERE ATS.Name = '" + searchATSField.getText() + "';"));
            
            DB_ATS.closeDB();
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void deleteATS()
    {
        try
        {
            DB_ATS.connect();
            DB_ATS.createDB();
            
            DB_ATS.getData("DELETE FROM ATS WHERE ATS.ID_ATS = '" + deleteATSField.getText() + "';");
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        
        try
        {
            view.setItems(DB_ATS.getData("SELECT * FROM"));
            DB_ATS.closeDB();
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }         
    }
    
    @FXML
    private void loadQueriesForm()
    {
        new Queries().start(new Stage());
        
        primaryStage = (Stage) add.getScene().getWindow();
    }
    
    private void initATS_TypeComboBox()
    {
        ResultSet typesSet = null;

        try 
        {
            DB_ATS_Type.connect();
            DB_ATS_Type.createDB();
            
            typesSet = DB_ATS_Type.get("SELECT Type FROM");
            
            while (typesSet.next())
            {
                type.getItems().add(typesSet.getString("Type"));                
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try
            {
                DB_ATS_Type.closeDB();
            } catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void initTariffComboBox()
    {
        ResultSet tariffsSet = null;
        
        try 
        {
            DB_Tariff.connect();
            DB_Tariff.createDB();
            
            tariffsSet = DB_Tariff.get("SELECT ID_Tariff FROM");
            
            while (tariffsSet.next())
            {
                tariff.getItems().add(tariffsSet.getString("ID_Tariff"));                
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try
            {
                DB_TariffType.closeDB();
            } catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void initTariffTypeComboBox()
    {
        ResultSet tariffTypesSet = null;

        try 
        {
            DB_TariffType.connect();
            DB_TariffType.createDB();
            
            tariffTypesSet = DB_TariffType.get("SELECT Name FROM");
            
            while (tariffTypesSet.next())
            {
                tariffTariffType.getItems().add(tariffTypesSet.getString("Name"));                
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try
            {
                DB_TariffType.closeDB();
            } catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void initDistrictComboBox()
    {
        ResultSet districtsSet = null;

        try 
        {
            DB_District.connect();
            DB_District.createDB();
            
            districtsSet = DB_District.get("SELECT Name FROM");
            
            while (districtsSet.next())
            {
                district.getItems().add(districtsSet.getString("Name"));                
            }
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally 
        {
            try
            {
                DB_District.closeDB();
            } catch (ClassNotFoundException | SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @FXML
    private void addDistrict() throws ClassNotFoundException, SQLException
    {
        District curr = new District();
        curr.name.set(districtNameField.getText());
        
        districtNameField.setText("");
        
        DB_District.connect();
        DB_District.createDB();
        DB_District.writeDB(curr);
        
        idDistrictField.setText(String.valueOf(DB_District.maxID + 1));
        
        refreshDistrict();
        initDistrictComboBox();
        DB_District.closeDB();
    }
    
    @FXML
    private void addTypeATS() throws SQLException, ClassNotFoundException
    {
        ATS_Type curr = new ATS_Type();
        curr.type.set(ATS_TypeNameField.getText());
        
        ATS_TypeNameField.setText("");
        
        DB_ATS_Type.connect();
        DB_ATS_Type.createDB();
        DB_ATS_Type.writeDB(curr);
        
        idATS_TypeField.setText(String.valueOf(DB_ATS_Type.maxID + 1));
        
        refreshATS_Type();
        initATS_TypeComboBox();
        DB_ATS_Type.closeDB();
    }
    
    @FXML
    private void addSocialPosition() throws SQLException, ClassNotFoundException
    {
        SocialPosition curr = new SocialPosition();
        curr.type.set(socialPositionNameField.getText());
        
        socialPositionNameField.setText("");
        
        DB_SocialPosition.connect();
        DB_SocialPosition.createDB();
        DB_SocialPosition.writeDB(curr);
        
        idSocialPositionField.setText(String.valueOf(DB_SocialPosition.maxID + 1));
        
        refreshSocialPosition();
        DB_SocialPosition.closeDB();
    }
    
    @FXML
    private void addTariffType() throws ClassNotFoundException, SQLException
    {
        TariffType curr = new TariffType();
        curr.name.set(tariffTypeNameField.getText());
        
        tariffTypeNameField.setText("");
        
        DB_TariffType.connect();
        DB_TariffType.createDB();
        DB_TariffType.writeDB(curr);
        
        idTariffTypeField.setText(String.valueOf(DB_TariffType.maxID + 1));
        
        refreshTariffType();
        initTariffTypeComboBox();
        DB_TariffType.closeDB();
    }
    
    @FXML
    private void addCountry() throws SQLException, ClassNotFoundException
    {
        Country curr = new Country();
        curr.name.set(countryNameField.getText());
        
        countryNameField.setText("");
        
        DB_Country.connect();
        DB_Country.createDB();
        DB_Country.writeDB(curr);
        
        idCountryField.setText(String.valueOf(DB_Country.maxID + 1));
        
        refreshCountry();
        DB_Country.closeDB();
    }
    
    @FXML
    private void addCity() throws ClassNotFoundException, SQLException
    {
        City curr = new City();
        curr.name.set(cityNameField.getText());
        
        cityNameField.setText("");
        
        DB_City.connect();
        DB_City.createDB();
        DB_City.writeDB(curr);
        
        idCityField.setText(String.valueOf(DB_City.maxID + 1));
        
        refreshCity();
        DB_City.closeDB();
    }
    
    @FXML
    private void addExemption() throws ClassNotFoundException, SQLException
    {
        Exemption curr = new Exemption();
        curr.type.set(exemptionNameField.getText());
        curr.terms.set(exemptionTermsField.getText());
        curr.tariff.set(exemptionTariffField.getText());
                
        exemptionNameField.clear();
        exemptionTariffField.clear();
        exemptionTermsField.clear();
                
        DB_Exemption.connect();
        DB_Exemption.createDB();
        DB_Exemption.writeDB(curr);
        
        idExemptionField.setText(String.valueOf(DB_Exemption.maxID + 1));
        
        refreshExemption();
        DB_Exemption.closeDB();
    }
    
    @FXML
    private void addTariff() throws ClassNotFoundException, SQLException
    {
        Tariff curr = new Tariff();
        curr.tariffType.set(tariffTariffType.getValue());
                
        curr.startDate.set(tariffStartDateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        curr.finishDate.set(tariffFinishDateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
        curr.cost.set(tariffCostField.getText());
                
        DB_Tariff.connect();
        DB_Tariff.createDB();
        
        ResultSet id_TariffType = DB_TariffType.get("SELECT ID_TariffType FROM TariffType WHERE Name = '" + tariffTariffType.getValue() + "';");
                
        curr.setID(id_TariffType.getInt("ID_TariffType"));
        DB_Tariff.writeDB(curr);
        
        idTariffField.setText(String.valueOf(DB_Tariff.maxID));
        
        refreshTariff();
        initTariffComboBox();
        DB_Tariff.closeDB();
    }
    
    @FXML
    private void addATS() throws ClassNotFoundException, SQLException
    {
        ATS curr = new ATS();
        curr.name.set(name.getText());        
        curr.district.set(district.getValue());
        curr.address.set(address.getText());
        curr.year.set(year.getText());
        curr.ATS_Type.set(type.getValue());
        curr.license.set(license.getText());
        curr.tariff.set(tariff.getValue());
        
        DB_District.connect();
        DB_District.createDB();
        ResultSet idDistrict = DB_District.get("SELECT ID_District FROM District WHERE Name = '" + district.getValue() + "';");
        curr.setID_District(idDistrict.getInt("ID_District"));
        DB_District.closeDB();
        
        DB_ATS_Type.connect();
        DB_ATS_Type.createDB();
        ResultSet idType = DB_ATS_Type.get("SELECT ID_ATS_Type FROM ATS_Type WHERE Type = '" + type.getValue() + "';");
        curr.setID_ATS_Type(idType.getInt("ID_ATS_Type"));
        DB_ATS_Type.closeDB();
        
        DB_Tariff.connect();
        DB_Tariff.createDB();
        ResultSet idTariff = DB_Tariff.get("SELECT ID_Tariff FROM Tariff WHERE ID_Tariff = '" + tariff.getValue() + "';");
        curr.setID_Tariff(idTariff.getInt("ID_Tariff"));
        DB_Tariff.closeDB();
        
        DB_ATS.connect();
        DB_ATS.createDB();
        DB_ATS.writeDB(curr);
        
        idATS.setText(String.valueOf(DB_ATS.maxID));
        
        refreshATS();
        DB_ATS.closeDB();
    }
    
    public void refreshAll()
    {
        try
        {
            refreshATS();
            refreshATS_Type();
            refreshTariff();
            refreshCity();
            refreshCountry();
            refreshDistrict();
            refreshExemption();
            refreshSocialPosition();
            refreshTariffType();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public void refreshATS_Type() throws SQLException, ClassNotFoundException
    {
        DB_ATS_Type.connect();
        DB_ATS_Type.createDB();
                
        ATS_TypeView.setItems(DB_ATS_Type.getData("SELECT * FROM"));
        idATS_TypeField.setText(String.valueOf(DB_ATS_Type.maxID + 1));
        
        DB_ATS_Type.closeDB();
    }
    
    public void refreshATS() throws SQLException, ClassNotFoundException
    {
        DB_ATS.connect();
        DB_ATS.createDB();
                
        view.setItems(DB_ATS.getData("SELECT * FROM"));
        idATS.setText(String.valueOf(DB_ATS.maxID + 1));
        
        DB_ATS.closeDB();
    }
    
    public void refreshSocialPosition() throws SQLException, ClassNotFoundException
    {
        DB_SocialPosition.connect();
        DB_SocialPosition.createDB();
                
        socialPositionView.setItems(DB_SocialPosition.getData("SELECT * FROM"));
        idSocialPositionField.setText(String.valueOf(DB_SocialPosition.maxID + 1));
        
        DB_SocialPosition.closeDB();
    }
    
    public void refreshCity() throws SQLException, ClassNotFoundException
    {
        DB_City.connect();
        DB_City.createDB();
                
        cityView.setItems(DB_City.getData("SELECT * FROM"));
        idCityField.setText(String.valueOf(DB_City.maxID + 1));
        
        DB_City.closeDB();
    }

    public void refreshCountry() throws SQLException, ClassNotFoundException
    {
        DB_Country.connect();
        DB_Country.createDB();
                
        countryView.setItems(DB_Country.getData("SELECT * FROM"));
        idCountryField.setText(String.valueOf(DB_Country.maxID + 1));
        
        DB_Country.closeDB();
    }
    
    public void refreshDistrict() throws SQLException, ClassNotFoundException
    {
        DB_District.connect();
        DB_District.createDB();
                
        districtView.setItems(DB_District.getData("SELECT * FROM"));
        idDistrictField.setText(String.valueOf(DB_District.maxID + 1));
        
        DB_District.closeDB();
    }
    
    public void refreshExemption() throws SQLException, ClassNotFoundException
    {
        DB_Exemption.connect();
        DB_Exemption.createDB();
                
        exemptionView.setItems(DB_Exemption.getData("SELECT * FROM"));
        idExemptionField.setText(String.valueOf(DB_Exemption.maxID + 1));
        
        DB_Exemption.closeDB();
    }
    
    public void refreshTariff() throws SQLException, ClassNotFoundException
    {
        DB_Tariff.connect();
        DB_Tariff.createDB();
        
        tariffView.setItems(DB_Tariff.getData("SELECT * FROM"));
        idTariffField.setText(String.valueOf(DB_Tariff.maxID + 1));
        
        DB_Tariff.closeDB();
    }
    
    public void refreshTariffType() throws SQLException, ClassNotFoundException
    {
        DB_TariffType.connect();
        DB_TariffType.createDB();
                
        tariffTypeView.setItems(DB_TariffType.getData("SELECT * FROM"));
        idTariffTypeField.setText(String.valueOf(DB_TariffType.maxID + 1));
        
        DB_TariffType.closeDB();
    }
    
	@FXML
	public void exit()
	{
	    System.exit(0);
	}
}
