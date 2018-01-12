package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class DetailView extends VBox implements ViewMixin {

    private final PresentationModel model;
    private Editor editor;
    private Header header;


    public DetailView(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        header = new Header(model);
        editor = new Editor(model);



    }


    @Override
    public void layoutControls() {
        getChildren().addAll(header,editor);


    }


}
