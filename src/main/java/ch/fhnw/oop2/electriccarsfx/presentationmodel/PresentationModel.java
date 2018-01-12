package ch.fhnw.oop2.electriccarsfx.presentationmodel;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PresentationModel {

    private final StringProperty applicationTitle = new SimpleStringProperty("Electric Car");
    private final LongProperty selectedCarId = new SimpleLongProperty(-1);

    private static final String FILE_NAME = "/data/electriccars.csv";
    private static final String SEP = ";";
    private ObservableList<Car> allCars = FXCollections.observableArrayList();

    public PresentationModel(){
        allCars.addAll(readFromFile());
    }

    public Car getCar(long id) {
        return allCars.stream()
                .filter(car -> car.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(getPath(FILE_NAME))) {
            writer.write("ID;MANUFACTURER;MODEL;SEATS;VEHICLE_CLASS;PRICE;WEIGHT;NOMINAL_RANGE_KM;TOP_SPEED_KM_H;PEAK_POWER_KW;PEAK_POWER_HP;ACCELERATION_S;CONSUMPTION_KWH;CHARGING_TIME_STANDARD_H;CHARGING_TIME_ROTARY_CURRENT_H;BATTERY_CAPACITY_KWH;PRODUCTION;PRODUCTION_YEAR;IMAGE_URL");
            writer.newLine();
            allCars.stream()
                    .map(car -> car.infoAsLine(SEP))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new IllegalStateException(e);
                        }
                    });
        } catch (IOException e) {
            throw new IllegalStateException("save failed");
        }
    }

    private List<Car> readFromFile(){
        Stream<String> stream = getStreamOfLines(FILE_NAME);
        return stream.skip(1)
                .map(line -> new Car(line.split(SEP, 30)))
                .collect(Collectors.toList());
    }

    private Stream<String> getStreamOfLines(String fileName){
        try {
            return Files.lines(getPath(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    private Path getPath(String fileName)  {
        try {
            return Paths.get(getClass().getResource(fileName).toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public ObservableList<Car> getAllCars(){return allCars;}

    public StringProperty applicationTitleProperty() {
        return applicationTitle;
    }

    public LongProperty getSelectedCarIdProperty() {return selectedCarId; }

    public void setSelectedCarId(long selectedCarID) {this.selectedCarId.set(selectedCarID); }

    public void addCar(Car car) {
        allCars.add(car);

        setSelectedCarId(car.getId());
    }

    public void removeCar(Car selectedItem) {
        allCars.remove(selectedItem);
    }

    public Car getLastCar() {
        return getAllCars().get(getAllCars().size()-1);
    }
}
