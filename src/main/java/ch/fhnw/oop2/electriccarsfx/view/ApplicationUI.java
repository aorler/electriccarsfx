package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.application.Platform;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class ApplicationUI extends BorderPane implements ViewMixin {
    private final PresentationModel model;

    private ButtonBar buttonBar;

    private ListView listView;
    private DetailView detailView;
    private SplitPane mainSplit;

    public ApplicationUI(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("style.css");
    }

    @Override
    public void initializeControls() {
        buttonBar = new ButtonBar(model);
        listView = new ListView(model.getAllCars());
        detailView = new DetailView(model);
        mainSplit = new SplitPane();
    }

    @Override
    public void layoutControls() {
        setTop(buttonBar);
        setCenter(mainSplit);
        mainSplit.getItems().add(listView);
        mainSplit.getItems().add(detailView);
    }

    @Override
    public void setupEventHandlers() {

        buttonBar.getDeleteButton().setOnMouseClicked(event -> {
            Car selectedItem = (Car)listView.getSelectionModel().getSelectedItem();

            if(selectedItem != null) {
                model.removeCar(selectedItem);
            }

        });

        buttonBar.getSaveButton().setOnMouseClicked(event -> {
            model.save();
        });

        buttonBar.getNewButton().setOnMouseClicked(event -> {
            //create new id
            long biggestId = -1;
            for (Car car : model.getAllCars()) {
                if (car.getId() > biggestId)
                    biggestId = car.getId();
            }

            model.addCar(new Car(biggestId + 1));

            Platform.runLater(() -> {
                listView.scrollTo(model.getLastCar());
                listView.getSelectionModel().select(model.getLastCar());
                listView.requestFocus();
            });

        });
    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    Car selectedCar = (Car) newValue;

                    model.setSelectedCarId(selectedCar.getId());
                });
    }

}
