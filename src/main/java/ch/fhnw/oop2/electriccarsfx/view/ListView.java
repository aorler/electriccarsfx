package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListView extends TableView implements ViewMixin{
    private ObservableList<Car> items;

    public ListView(ObservableList<Car> items) {
        this.items = items;
        init();

        if(items.size() > 0) {
            //preselect first entry
            Platform.runLater(() -> {
                requestFocus();
                getSelectionModel().select(0);
                getFocusModel().focus(0);
            });
        }
    }

    @Override
    public void initializeSelf() {

    }

    @Override
    public void initializeControls() {

    }

    @Override
    public void layoutControls() {
        setItems(this.items);

        TableColumn<Car,Number> id = new TableColumn<>("ID");
        TableColumn<Car,String> manufacturer   = new TableColumn<>("Manufacturer");
        TableColumn<Car,String> model  = new TableColumn<>("Model");
        TableColumn<Car,Number> seats = new TableColumn<>("Sitzplätze");
        TableColumn<Car,String> vehicleCass = new TableColumn<>("Fahrzeugklasse");
        TableColumn<Car,Number> price = new TableColumn<>("Preis");
        TableColumn<Car,Number> wight = new TableColumn<>("Gewicht");
        TableColumn<Car,Number> kmRange = new TableColumn<>("Kilometer");
        TableColumn<Car,Number> topSpeed = new TableColumn<>("Höchstgeschwindigkeit");
        TableColumn<Car,Number> peakPowerKw = new TableColumn<>("Maximalkraft KW");
        TableColumn<Car,Number> peakPowerHp = new TableColumn<>("Maximalkraft HP");
        TableColumn<Car,Number> acceleration = new TableColumn<>("Beschleunigung");
        TableColumn<Car,Number> consuption = new TableColumn<>("Verbrauch");
        TableColumn<Car,String> standartChargingTime = new TableColumn<>("Ladezeit");
        TableColumn<Car,String> chargingTimeRotary = new TableColumn<>("LadezeitR");
        TableColumn<Car,Number> batteryCapacity = new TableColumn<>("Batteriekapazität");
        TableColumn<Car,Number> production = new TableColumn<>("Produktion");
        TableColumn<Car,Number> productionYear = new TableColumn<>("Produktionsjahr");
        TableColumn<Car,String> impURL = new TableColumn<>("Link");

        id.setCellValueFactory(cell -> cell.getValue().idProperty());
        manufacturer.setCellValueFactory(cell -> cell.getValue().manufacturerProperty());
        model.setCellValueFactory(cell -> cell.getValue().modelProperty());
        seats.setCellValueFactory(cell -> cell.getValue().seatsProperty());
        vehicleCass.setCellValueFactory(cell -> cell.getValue().vehicleClassProperty());
        price.setCellValueFactory(cell -> cell.getValue().priceProperty());
        wight.setCellValueFactory(cell -> cell.getValue().wightProperty());
        kmRange.setCellValueFactory(cell -> cell.getValue().kmRangeProperty());
        topSpeed.setCellValueFactory(cell -> cell.getValue().topSpeedProperty());
        peakPowerKw.setCellValueFactory(cell -> cell.getValue().peakPowerKwProperty());
        peakPowerHp.setCellValueFactory(cell -> cell.getValue().peakPowerHpProperty());
        acceleration.setCellValueFactory(cell -> cell.getValue().accelerationProperty());
        consuption.setCellValueFactory(cell -> cell.getValue().consumpitonProperty());
        standartChargingTime.setCellValueFactory(cell -> cell.getValue().standardChargingTimeProperty());
        chargingTimeRotary.setCellValueFactory(cell -> cell.getValue().chargingTimeRotaryProperty());
        batteryCapacity.setCellValueFactory(cell -> cell.getValue().battaryCapacityProperty());
        production.setCellValueFactory(cell -> cell.getValue().productionProperty());
        productionYear.setCellValueFactory(cell -> cell.getValue().productionYearProperty());
        impURL.setCellValueFactory(cell -> cell.getValue().imgUrlProperty());

        getColumns().addAll(id,manufacturer,model,seats,vehicleCass,price,wight,kmRange,topSpeed,peakPowerKw,
                peakPowerHp,acceleration,consuption,standartChargingTime,chargingTimeRotary,batteryCapacity,production,productionYear,impURL);
    }

    @Override
    public void setupEventHandlers() {

    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {

    }

    @Override
    public void addStylesheetFiles(String... stylesheetFile) {

    }


}
