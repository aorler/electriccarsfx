package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ButtonBar extends HBox implements ViewMixin {

    private final PresentationModel model;
    private Button saveButton;
    private Button newButton;
    private Button deleteButton;

    public ButtonBar(PresentationModel mode1) {
        this.model = mode1;
        init();
    }


    @Override
    public void initializeControls() {
        saveButton = new Button("Save");
        newButton = new Button("New");
        deleteButton = new Button("Delete");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(saveButton, newButton, deleteButton);

    }

    @Override
    public void setupEventHandlers() {

    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getNewButton() {
        return newButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
