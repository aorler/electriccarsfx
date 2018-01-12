package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class Header extends GridPane implements ViewMixin {

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
    public void initializeSelf() {getStyleClass().add("header");}

    @Override
    public void initializeControls() {
        brand = new Label();
        type = new Label();
        maxDistance = new Label();
        maxSpeed = new Label();
        price = new Label();

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
        model.selectedCarIdProperty().addListener((observable, oldValue, newValue) -> {
            Car oldSelection = model.getCars(oldValue.longValue());
            Car newSelection = model.getCars(newValue.longValue());

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
                maxDistance.textProperty().bind(newSelection.kmRangeProperty().asString());
                maxSpeed.textProperty().bind(newSelection.topSpeedProperty().asString());
                price.textProperty().bind(newSelection.priceProperty().asString());

            }
        });

    }

    @Override
    public void addStylesheetFiles(String... stylesheetFile) {

    }
}
