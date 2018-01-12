package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
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
    private BarGraph graph;

    public DetailView(PresentationModel model) {
        this.model = model;
        init();
    }

    @Override
    public void initializeControls() {
        header = new Header(model);
        editor = new Editor(model);
        graph = new BarGraph(model.getAllCars());
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(header, editor, graph);

    }

    @Override
    public void setupBindings() {

        model.getSelectedCarIdProperty().addListener((observable, oldValue, newValue) -> {
            Car oldSelection = model.getCar(oldValue.longValue());
            Car newSelection = model.getCar(newValue.longValue());

            if (oldSelection != null) {
                graph.priceProperty().unbind();
                graph.kmLadungProperty().unbind();
                graph.kwProperty().unbind();
                graph.kmhProperty().unbind();
                graph.kgProperty().unbind();
            }

            if (newSelection != null) {
                graph.priceProperty().bindBidirectional(newSelection.priceProperty());
                graph.kmLadungProperty().bindBidirectional(newSelection.kmRangeProperty());
                graph.kwProperty().bindBidirectional(newSelection.peakPowerKwProperty());
                graph.kmhProperty().bindBidirectional(newSelection.topSpeedProperty());
                graph.kgProperty().bindBidirectional(newSelection.wightProperty());
            }
        });

    }
}
