package ch.fhnw.oop2.electriccarsfx.presentationmodel;

import javafx.beans.property.*;

public class Car {

    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty manufacturer = new SimpleStringProperty(NO_VALUE_SET_STRING);
    private final StringProperty model = new SimpleStringProperty(NO_VALUE_SET_STRING);
    private final IntegerProperty seats = new SimpleIntegerProperty((int)NO_VALUE_SET_NUMERICAL);
    private final StringProperty vehicleClass = new SimpleStringProperty(NO_VALUE_SET_STRING);
    private final SimpleDoubleProperty price = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty wight = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty kmRange = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty topSpeed = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty peakPowerKw = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty peakPowerHp = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty acceleration = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleDoubleProperty consumption = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleStringProperty standardChargingTime = new SimpleStringProperty(NO_VALUE_SET_STRING);
    private final SimpleStringProperty chargingTimeRotary = new SimpleStringProperty(NO_VALUE_SET_STRING);
    private final SimpleDoubleProperty battaryCapacity = new SimpleDoubleProperty(NO_VALUE_SET_NUMERICAL);
    private final SimpleIntegerProperty production = new SimpleIntegerProperty((int)NO_VALUE_SET_NUMERICAL);
    private final SimpleIntegerProperty productionYear = new SimpleIntegerProperty((int)NO_VALUE_SET_NUMERICAL);
    private final SimpleStringProperty imgUrl = new SimpleStringProperty();

    private static final double KW_TO_HS = 1.35962;
    public static final double NO_VALUE_SET_NUMERICAL = -1.0;
    public static final String NO_VALUE_SET_STRING = "-1";
    public static final String NO_IMAGE_SET = "/icon/noimage.png";

    public Car(long id) {
        setId(id);

        setManufacturer("");
        setModel("");
        setSeats("");
        setVehicleClass("");
        setPrice("");
        setWight("");
        setKmRange("");
        setTopSpeed("");
        setPeakPowerKw("");
        setPeakPowerHp("");
        setAcceleration("");
        setConsumpiton("");
        setStandardChargingTime("");
        setChargingTimeRotary("");
        setBattaryCapacity("");
        setProduction("");
        setProductionYear("");
        setImgUrl("");

        addPowerConverterBindings();
    }

    public Car(String[] line){
        setId(line[0]);
        setManufacturer(line[1]);
        setModel(line[2]);
        setSeats(line[3]);
        setVehicleClass(line[4]);
        setPrice(line[5]);
        setWight(line[6]);
        setKmRange(line[7]);
        setTopSpeed(line[8]);
        setPeakPowerKw(line[9]);
        setPeakPowerHp(line[10]);
        setAcceleration(line[11]);
        setConsumpiton(line[12]);
        setStandardChargingTime(line[13]);
        setChargingTimeRotary(line[14]);
        setBattaryCapacity(line[15]);
        setProduction(line[16]);
        setProductionYear(line[17]);
        setImgUrl(line[18]);

        addPowerConverterBindings();
    }

    private void addPowerConverterBindings() {
        peakPowerHpProperty().addListener((obv, oldValue1, newHpValue) -> {

            peakPowerKwProperty().set((Double) newHpValue / KW_TO_HS);
        });

        peakPowerKwProperty().addListener((obv, oldValue1, newKwValue) -> {

            peakPowerHpProperty().set((Double) newKwValue * KW_TO_HS);
        });
    }

    public String infoAsLine(String delimiter){
        return String.join(delimiter,
                String.valueOf(getId()),
                getManufacturer(),
                getModel(),
                String.valueOf(getSeats()),
                getVehicleClass(),
                String.valueOf(getPrice()),
                String.valueOf(getWight()),
                String.valueOf(getKmRange()),
                String.valueOf(getTopSpeed()),
                String.valueOf(getPeakPowerKw()),
                String.valueOf(getPeakPowerHp()),
                String.valueOf(getAcceleration()),
                String.valueOf(getConsumpiton()),
                String.valueOf(getStandardChargingTime()),
                String.valueOf(getChargingTimeRotary()),
                String.valueOf(getBattaryCapacity()),
                String.valueOf(getProduction()),
                String.valueOf(getProductionYear()),
                getImgUrl());
    }



    //Getters and Setters
    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }
    public void setId(String id) {
        if(id.isEmpty() || id.equals(String.valueOf(NO_VALUE_SET_STRING)) || id.equals("null")) {
            this.id.setValue(System.currentTimeMillis());
            return;
        }
        this.id.set(Long.parseLong(id));
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public StringProperty manufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if(manufacturer.isEmpty() || manufacturer.equals("null")) {
            this.manufacturer.setValue(NO_VALUE_SET_STRING);
            return;
        }
        this.manufacturer.set(manufacturer);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        if(model.isEmpty() || model.equals("null")) {
            this.model.setValue(NO_VALUE_SET_STRING);
            return;
        }
        this.model.set(model);
    }

    public int getSeats() {
        return seats.get();
    }

    public IntegerProperty seatsProperty() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats.set(seats);
    }
    private void setSeats(String seats) {
        if(seats.isEmpty() || seats.equals("null")) {
            this.seats.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.seats.setValue(Integer.parseInt(seats));
    }

    public String getVehicleClass() {
        return vehicleClass.get();
    }

    public StringProperty vehicleClassProperty() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        if(vehicleClass.isEmpty() || vehicleClass.equals("null")) {
            this.vehicleClass.setValue(NO_VALUE_SET_STRING);
            return;
        }
        this.vehicleClass.set(vehicleClass);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
    public void setPrice(String price) {
        if(price.isEmpty() || price.equals("null")) {
            this.price.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.price.set(Double.parseDouble(price));
    }

    public Double getWight() {
        return wight.get();
    }

    public SimpleDoubleProperty wightProperty() {
        return wight;
    }

    public void setWight(double wight) {
        this.wight.set(wight);
    }
    public void setWight(String weight) {
        if(weight.isEmpty() || weight.equals("null")) {
            this.wight.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.wight.set(Double.parseDouble(weight));
    }

    public double getKmRange() {
        return kmRange.get();
    }
    public SimpleDoubleProperty kmRangeProperty() {
        return kmRange;
    }

    public void setKmRange(float kmRange) {
        this.kmRange.set(kmRange);
    }
    public void setKmRange(String nominalRangeKM) {
        if(nominalRangeKM.isEmpty() || nominalRangeKM.equals("null")) {
            this.kmRange.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.kmRange.set(Double.parseDouble(nominalRangeKM));
    }

    public Double getTopSpeed() {
        return topSpeed.get();
    }

    public SimpleDoubleProperty topSpeedProperty() {
        return topSpeed;
    }

    public void setTopSpeed(double topSpeed) {
        this.topSpeed.set(topSpeed);
    }
    public void setTopSpeed(String topSpeedKM) {
        if(topSpeedKM.isEmpty() || topSpeedKM.equals("null")) {
            this.topSpeed.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.topSpeed.set(Double.parseDouble(topSpeedKM));
    }

    public double getPeakPowerKw() {
        return peakPowerKw.get();
    }

    public SimpleDoubleProperty peakPowerKwProperty() {
        return peakPowerKw;
    }

    public void setPeakPowerKw(double peakPowerKw) {
        this.peakPowerKw.set(peakPowerKw);
    }
    public void setPeakPowerKw(String peakPowerKW) {
        if(peakPowerKW.isEmpty() || peakPowerKW.equals("null")) {
            this.peakPowerKw.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.peakPowerKw.set(Double.parseDouble(peakPowerKW));
    }

    public double getPeakPowerHp() {
        return peakPowerHp.get();
    }

    public SimpleDoubleProperty peakPowerHpProperty() {
        return peakPowerHp;
    }
    public void setPeakPowerHp(String peakPowerHP) {
        if(peakPowerHP.isEmpty() || peakPowerHP.equals("null")) {
            this.peakPowerHp.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.peakPowerHp.set(Double.parseDouble(peakPowerHP));
    }

    public void setPeakPowerHp(double peakPowerHp) {
        this.peakPowerHp.set(peakPowerHp);
    }

    public double getAcceleration() {
        return acceleration.get();
    }

    public SimpleDoubleProperty accelerationProperty() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration.set(acceleration);
    }
    public void setAcceleration(String accelerationS) {
        if(accelerationS.isEmpty() || accelerationS.equals("null")) {
            this.acceleration.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.acceleration.set(Double.parseDouble(accelerationS));
    }

    public double getConsumpiton() {
        return consumption.get();
    }

    public SimpleDoubleProperty consumpitonProperty() {
        return consumption;
    }

    public void setConsumpiton(double consumpiton) {
        this.consumption.set(consumpiton);
    }

    public void setConsumpiton(String consumption) {
        if(consumption.isEmpty() || consumption.equals("null")) {
            this.consumption.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.consumption.set(Double.parseDouble(consumption));
    }

    public String getStandardChargingTime() {
        return standardChargingTime.get();
    }

    public SimpleStringProperty standardChargingTimeProperty() {
        return standardChargingTime;
    }

    public void setStandardChargingTime(String standardChargingTime) {
        if(standardChargingTime.isEmpty() || standardChargingTime.equals("null")) {
            this.standardChargingTime.setValue(NO_VALUE_SET_STRING);
            return;
        }
        this.standardChargingTime.set(standardChargingTime);
    }

    public String getChargingTimeRotary() {
        return chargingTimeRotary.get();
    }

    public SimpleStringProperty chargingTimeRotaryProperty() {
        return chargingTimeRotary;
    }

    public void setChargingTimeRotary(String chargingTimeRotary) {
        if(chargingTimeRotary.isEmpty() || chargingTimeRotary.equals("null")) {
            this.chargingTimeRotary.setValue(NO_VALUE_SET_STRING);
            return;
        }
        this.chargingTimeRotary.set(chargingTimeRotary);
    }


    public double getBattaryCapacity() {
        return battaryCapacity.get();
    }

    public SimpleDoubleProperty battaryCapacityProperty() {
        return battaryCapacity;
    }

    public void setBattaryCapacity(double battaryCapacity) {
        this.battaryCapacity.set(battaryCapacity);
    }

    public void setBattaryCapacity(String batteryCapacity) {
        if(batteryCapacity.isEmpty() || batteryCapacity.equals("null")) {
            this.battaryCapacity.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.battaryCapacity.set(Double.parseDouble(batteryCapacity));
    }

    public int getProduction() {
        return production.get();
    }

    public SimpleIntegerProperty productionProperty() {
        return production;
    }

    public void setProduction(int production) {
        this.production.set(production);
    }
    public void setProduction(String production) {
        if(production.isEmpty() || production.equals("null")) {
            this.production.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.production.set(Integer.parseInt(production));
    }

    public int getProductionYear() {
        return productionYear.get();
    }

    public SimpleIntegerProperty productionYearProperty() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear.set(productionYear);
    }
    public void setProductionYear(String productionYear) {
        if(productionYear.isEmpty() || productionYear.equals("null")) {
            this.productionYear.setValue(NO_VALUE_SET_NUMERICAL);
            return;
        }
        this.productionYear.set(Integer.parseInt(productionYear));
    }

    public String getImgUrl() {
        if(imgUrl.getValue().equals(NO_VALUE_SET_STRING)){
            return NO_IMAGE_SET;
        }
        return imgUrl.get();
    }

    public SimpleStringProperty imgUrlProperty() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)   {
        if(imgUrl.isEmpty() || imgUrl.equals("null")) {
        this.imgUrl.setValue(NO_VALUE_SET_STRING);
        return;
    }
        this.imgUrl.set(imgUrl);
}
    }









