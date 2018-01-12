package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import javafx.event.EventHandler;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;

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

    }

    @Override
    public void setupValueChangedListeners() {
//        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> model.setSelectedCarId(newValue));
//        model.selectedCarIdProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.longValue() == model.newestCarIdProperty().getValue()) {
//                listView.scrollTo(model.getCars(newValue.longValue()));
//                listView.getSelectionModel().select(model.getCars(newValue.longValue()));
//                listView.requestFocus();
//            }
//        });
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

    @Override
    public void addStylesheetFiles(String... stylesheetFile) {

    }
}
