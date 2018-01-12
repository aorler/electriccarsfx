package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class Header extends VBox implements ViewMixin {

    private final PresentationModel model;
    private Label brand;
    private Label type;
    private Label maxDistance;
    private Label maxSpeed;
    private Label price;

    public Header(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("header");
    }

    @Override
    public void initializeControls() {
        brand = new Label();
        type = new Label();
        maxDistance = new Label();
        maxSpeed = new Label();
        price = new Label();

        //set styles
        brand.getStyleClass().add("header-label-big");
        type.getStyleClass().add("header-label-medium");
        maxDistance.getStyleClass().add("header-label-small");
        maxSpeed.getStyleClass().add("header-label-small");
        price.getStyleClass().add("header-label-small");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(brand,type,maxDistance,maxSpeed,price);
        setMinHeight(150);
    }

    @Override
    public void setupEventHandlers() {

    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {
        model.getSelectedCarIdProperty().addListener((observable, oldValue, newValue) -> {
            Car oldSelection = model.getCar(oldValue.longValue());
            Car newSelection = model.getCar(newValue.longValue());

            if (oldSelection != null) {
                brand.textProperty().unbind();
                type.textProperty().unbind();
                maxDistance.textProperty().unbind();
                maxSpeed.textProperty().unbind();
                price.textProperty().unbind();
            }

            if (newSelection != null) {
                brand.textProperty().bind(newSelection.manufacturerProperty());
                type.textProperty().bind(newSelection.modelProperty());
                maxDistance.textProperty().bind(newSelection.kmRangeProperty().asString("%.2f km"));
                maxSpeed.textProperty().bind(newSelection.topSpeedProperty().asString("%.2f km/h"));
                price.textProperty().bind(newSelection.priceProperty().asString("%.2f CHF"));
            }
        });

    }

    @Override
    public void addStylesheetFiles(String... stylesheetFile) {

    }
}
