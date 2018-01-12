package ch.fhnw.oop2.electriccarsfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.PresentationModel;
import ch.fhnw.oop2.electriccarsfx.view.ApplicationUI;
import javafx.stage.WindowEvent;

public class ElectricCarsApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		PresentationModel pm = new PresentationModel();

		Parent rootPanel = new ApplicationUI(pm);
		Scene scene = new Scene(rootPanel);

		primaryStage.titleProperty().bind(pm.applicationTitleProperty());
		primaryStage.setScene(scene);
		primaryStage.setWidth(1300);
		primaryStage.setHeight(800);
        primaryStage.setOnCloseRequest(event -> Platform.exit());

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
