package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.NumberStringConverter;

public class Editor extends GridPane implements ViewMixin {

    private final PresentationModel model;

    private Label manufacturer;
    private Label seats;
    private Label price;
    private Label kmRange;
    private Label kwPower;
    private Label acceleration;
    private Label chargingTime;
    private Label chargingRotary;
    private Label batteryCapacity;
    private Label production;
    private Label img;
    private Label carModel;
    private Label vehClass;
    private Label wight;
    private Label topSpeed;
    private Label horsePower;
    private Label consumption;
    private Label prodYear;

    private TextField manufacturerTxtField;
    private TextField seatsTxtField;
    private TextField priceTxtField;
    private TextField kmRangeTxtField;
    private TextField kwPowerTxtField;
    private TextField accelerationTxtField;
    private TextField chargingTimeTxtField;
    private TextField chargingRotaryTxtField;
    private TextField batteryCapacityTxtField;
    private TextField productionTxtField;
    private TextField imgTxtField;
    private TextField carModelTxtField;
    private TextField vehClassTxtField;
    private TextField wightTxtField;
    private TextField topSpeedTxtField;
    private TextField horsePowerTxtField;
    private TextField consumptionTxtField;
    private TextField prodYearTxtField;

    public Editor(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("editor");
    }

    @Override
    public void initializeControls() {

        //TODO: add missing labels
        manufacturer = new Label("Hersteller");
        seats = new Label("Sitze");
        price = new Label("Preis CHF");
        kmRange = new Label("Reichweite");
        kwPower = new Label("Spitzenleistung (KW)");
        acceleration = new Label("Beschleunigung auf 100");
        chargingTime = new Label("Ladezeit Standard (h)");
        chargingRotary = new Label("Ladezeit Drehstrom(h)");
        batteryCapacity = new Label("Kapazität Batterie(kwh)");
        production = new Label("jährliche Produktion");
        img = new Label("Bild");
        carModel = new Label("Modell");
        vehClass = new Label("Fahrzeugklasse");
        wight = new Label("Gewicht");
        topSpeed = new Label("Höchstegeschwindigkeit");
        horsePower = new Label("Spitzenleistung(PS)");
        consumption = new Label("Verbrauch");
        prodYear = new Label("Produktionsjahr");

        manufacturerTxtField = new TextField();
        seatsTxtField = new TextField();
        priceTxtField = new TextField();
        kmRangeTxtField = new TextField();
        kwPowerTxtField = new TextField();
        accelerationTxtField = new TextField();
        chargingTimeTxtField = new TextField();
        chargingRotaryTxtField = new TextField();
        batteryCapacityTxtField = new TextField();
        productionTxtField = new TextField();
        imgTxtField = new TextField();
        carModelTxtField = new TextField();
        vehClassTxtField = new TextField();
        wightTxtField = new TextField();
        topSpeedTxtField = new TextField();
        horsePowerTxtField = new TextField();
        consumptionTxtField = new TextField();
        prodYearTxtField = new TextField();

        //TODO: add custom css style
        //set styles9
        //manufacturer.getStyleClass().add("editor-label-small");
    }

    @Override
    public void layoutControls() {
        setGridLinesVisible(false);
        setPadding(new Insets(15, 5, 5, 5));
        setVgap(5);
        setHgap(5);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        getColumnConstraints().addAll(cc, cc, cc, cc);


        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        getRowConstraints().addAll(rc, rc, rc, rc, rc, rc, rc, rc, rc, rc);

        setColumnSpan(imgTxtField, 3);

        addRow(0, manufacturer, manufacturerTxtField, carModel, carModelTxtField);
        addRow(1, seats, seatsTxtField, vehClass, vehClassTxtField);
        addRow(2, price, priceTxtField, wight, wightTxtField);
        addRow(3, kmRange, kmRangeTxtField, topSpeed, topSpeedTxtField);
        addRow(4, kwPower, kwPowerTxtField, horsePower, horsePowerTxtField);
        addRow(5, acceleration, accelerationTxtField, consumption, consumptionTxtField);
        addRow(6, chargingTime, chargingTimeTxtField, chargingRotary, chargingRotaryTxtField);
        addRow(7, batteryCapacity, batteryCapacityTxtField);
        addRow(8, production, productionTxtField, prodYear, prodYearTxtField);
        addRow(9, img, imgTxtField);
    }

    @Override
    public void setupEventHandlers() {

    }

    @Override
    public void setupValueChangedListeners() {

        model.getSelectedCarIdProperty().addListener(((observable, oldValue, newValue) -> {
            Car oldSelection = model.getCar(oldValue.longValue());
            Car newSelection = model.getCar(newValue.longValue());

            //unbind old selection
            if (oldSelection != null) {
                manufacturerTxtField.textProperty().unbindBidirectional(oldSelection.manufacturerProperty());
                seatsTxtField.textProperty().unbindBidirectional(oldSelection.seatsProperty());
                priceTxtField.textProperty().unbindBidirectional(oldSelection.priceProperty());
                kmRangeTxtField.textProperty().unbindBidirectional(oldSelection.kmRangeProperty());
                kwPowerTxtField.textProperty().unbindBidirectional(oldSelection.peakPowerKwProperty());
                accelerationTxtField.textProperty().unbindBidirectional(oldSelection.accelerationProperty());
                chargingTimeTxtField.textProperty().unbindBidirectional(oldSelection.standardChargingTimeProperty());
                chargingRotaryTxtField.textProperty().unbindBidirectional(oldSelection.chargingTimeRotaryProperty());
                batteryCapacityTxtField.textProperty().unbindBidirectional(oldSelection.battaryCapacityProperty());
                productionTxtField.textProperty().unbindBidirectional(oldSelection.productionProperty());
                imgTxtField.textProperty().unbindBidirectional(oldSelection.imgUrlProperty());
                carModelTxtField.textProperty().unbindBidirectional(oldSelection.modelProperty());
                vehClassTxtField.textProperty().unbindBidirectional(oldSelection.vehicleClassProperty());
                wightTxtField.textProperty().unbindBidirectional(oldSelection.wightProperty());
                topSpeedTxtField.textProperty().unbindBidirectional(oldSelection.topSpeedProperty());
                horsePowerTxtField.textProperty().unbindBidirectional(oldSelection.peakPowerHpProperty());
                consumptionTxtField.textProperty().unbindBidirectional(oldSelection.consumpitonProperty());
                prodYearTxtField.textProperty().unbindBidirectional(oldSelection.productionYearProperty());
            }

            //bind new selection
            if (newSelection != null) {
                manufacturerTxtField.textProperty().bindBidirectional(newSelection.manufacturerProperty());
                seatsTxtField.textProperty().bindBidirectional(newSelection.seatsProperty(), new NumberStringConverter());
                priceTxtField.textProperty().bindBidirectional(newSelection.priceProperty(), new NumberStringConverter());
                kmRangeTxtField.textProperty().bindBidirectional(newSelection.kmRangeProperty(), new NumberStringConverter());
                kwPowerTxtField.textProperty().bindBidirectional(newSelection.peakPowerKwProperty(), new NumberStringConverter());
                accelerationTxtField.textProperty().bindBidirectional(newSelection.accelerationProperty(), new NumberStringConverter());
                chargingTimeTxtField.textProperty().bindBidirectional(newSelection.standardChargingTimeProperty());
                chargingRotaryTxtField.textProperty().bindBidirectional(newSelection.chargingTimeRotaryProperty());
                batteryCapacityTxtField.textProperty().bindBidirectional(newSelection.battaryCapacityProperty(), new NumberStringConverter());
                productionTxtField.textProperty().bindBidirectional(newSelection.productionProperty(), new NumberStringConverter());
                imgTxtField.textProperty().bindBidirectional(newSelection.imgUrlProperty());
                carModelTxtField.textProperty().bindBidirectional(newSelection.modelProperty());
                vehClassTxtField.textProperty().bindBidirectional(newSelection.vehicleClassProperty());
                wightTxtField.textProperty().bindBidirectional(newSelection.wightProperty(), new NumberStringConverter());
                topSpeedTxtField.textProperty().bindBidirectional(newSelection.topSpeedProperty(), new NumberStringConverter());
                horsePowerTxtField.textProperty().bindBidirectional(newSelection.peakPowerHpProperty(), new NumberStringConverter());
                consumptionTxtField.textProperty().bindBidirectional(newSelection.consumpitonProperty(), new NumberStringConverter());
                prodYearTxtField.textProperty().bindBidirectional(newSelection.productionYearProperty(), new NumberStringConverter());
            }

        }));

    }

    @Override
    public void setupBindings() {

    }

    @Override
    public void addStylesheetFiles(String... stylesheetFile) {

    }
}
