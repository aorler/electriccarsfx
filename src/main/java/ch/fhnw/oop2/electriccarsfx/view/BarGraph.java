package ch.fhnw.oop2.electriccarsfx.view;

import ch.fhnw.oop2.electriccarsfx.presentationmodel.Car;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleablePropertyFactory;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.util.List;

// BarGraph from other group

public class BarGraph extends Region {
    // needed for StyleableProperties
    private static final StyleablePropertyFactory<BarGraph> FACTORY = new StyleablePropertyFactory<>(Region.getClassCssMetaData());

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return FACTORY.getCssMetaData();
    }

    /****************     static values   **************************************/
    private static final double ARTBOARD_WIDTH  = 200;
    private static final double ARTBOARD_HEIGHT = 120;

    private static final double ASPECT_RATIO = ARTBOARD_WIDTH / ARTBOARD_HEIGHT;

    private static final double MINIMUM_WIDTH  = 320;
    private static final double MINIMUM_HEIGHT = MINIMUM_WIDTH / ASPECT_RATIO;

    private static final double MAXIMUM_WIDTH = 320;

    private static final double CIRCLE_RADIUS = 10;
    private static final double CIRCLE_YPOS = 92.5;
    private static final double RECT_DISTANCE = 33.33;

    private static final double BAR_WIDTH = 20;

    /****************     BarGraph parts    **************************************/
    //text in circle
    private Label titleChf;
    private Label titleKmLadung;
    private Label titleKw;
    private Label titleKmh;
    private Label titleKg;
    private Label[] labArray;

    //label for the legend
    private Label valueLegend;
    private Label averageLegend;

    //circle for title
    private Circle circleChf;
    private Circle circleKmLadung;
    private Circle circleKw;
    private Circle circleKmh;
    private Circle circleKg;
    private Circle[] circArray;

    //bar for average values
    private Rectangle rectAverageChf;
    private Rectangle rectAverageKmLadung;
    private Rectangle rectAverageKw;
    private Rectangle rectAverageKmh;
    private Rectangle rectAverageKg;
    private Rectangle[] rectAverageArray;

    //bar for selected car values
    private Rectangle rectActualChf;
    private Rectangle rectActualKmLadung;
    private Rectangle rectActualKw;
    private Rectangle rectActualKmh;
    private Rectangle rectActualKg;
    private Rectangle[] rectActualArray;

    //label for max
    private Label titleMax;

    /****************     BarGraph properties    **************************************/
    //average values
    private DoubleProperty averagePrice = new SimpleDoubleProperty();
    private DoubleProperty averageKmLadung = new SimpleDoubleProperty();
    private DoubleProperty averageKw = new SimpleDoubleProperty();
    private DoubleProperty averageKmh = new SimpleDoubleProperty();
    private DoubleProperty averageKg = new SimpleDoubleProperty();

    //max values
    private DoubleProperty maxPrice = new SimpleDoubleProperty();
    private DoubleProperty maxKmLadung = new SimpleDoubleProperty();
    private DoubleProperty maxKw = new SimpleDoubleProperty();
    private DoubleProperty maxKmh = new SimpleDoubleProperty();
    private DoubleProperty maxKg = new SimpleDoubleProperty();

    //selected car values
    private DoubleProperty price = new SimpleDoubleProperty();
    private DoubleProperty kmLadung = new SimpleDoubleProperty();
    private DoubleProperty kw = new SimpleDoubleProperty();
    private DoubleProperty kmh = new SimpleDoubleProperty();
    private DoubleProperty kg = new SimpleDoubleProperty();

    private ObservableList<Car> list;

    // needed for resizing
    private Pane drawingPane;

    public BarGraph(ObservableList<Car> list) {
        this. list = list;
        initializeSelf();
        calculateAveragesAndMax();
        initializeParts();
        layoutParts();
        setupEventHandlers();
        setupBinding();
        setupValueChangeListenersAndAnimations();

    }

    private void initializeSelf() {
        // load stylesheets
        String fonts = getClass().getResource("bar-fonts.css").toExternalForm();
        getStylesheets().add(fonts);

        String stylesheet = getClass().getResource("bar-style.css").toExternalForm();
        getStylesheets().add(stylesheet);

        getStyleClass().add("barGraph");
    }

    private void calculateAveragesAndMax(){
        //this method is used to calculate the maximum values in the specific categories aswell as the average values

        //price
        double sum = 0;
        double max = 0;
        for(int i = 0; i < list.size(); ++i){
            sum = sum + list.get(i).getPrice();
            if(list.get(i).getPrice() > max){
                maxPrice.setValue(list.get(i).getPrice());
                max = list.get(i).getPrice();
            }
        }
        averagePrice.setValue(sum / list.size());


        //km/ladung
        double sum2 = 0;
        double max2 = 0;
        for(int i = 0; i < list.size(); ++i){
            sum2 = sum2 + list.get(i).getKmRange();
            if(list.get(i).getKmRange() > max2){
                maxKmLadung.setValue(list.get(i).getKmRange());
                max2 = list.get(i).getKmRange();
            }
        }
        averageKmLadung.setValue(sum2 / list.size());

        //kw
        double sum3 = 0;
        double max3 = 0;
        for(int i = 0; i < list.size(); ++i){
            sum3 = sum3 + list.get(i).getPeakPowerKw();
            if(list.get(i).getPeakPowerKw() > max3){
                maxKw.setValue(list.get(i).getPeakPowerKw());
                max3 = list.get(i).getPeakPowerKw();
            }
        }
        averageKw.setValue(sum3 / list.size());

        //kmH
        double sum4 = 0;
        double max4 = 0;
        for(int i = 0; i < list.size(); ++i){
            sum4 = sum4 + list.get(i).getTopSpeed();
            if(list.get(i).getTopSpeed() > max4){
                maxKmh.setValue(list.get(i).getTopSpeed());
                max4 = list.get(i).getTopSpeed();
            }
        }
        averageKmh.setValue(sum4 / list.size());

        //kg
        double sum5 = 0;
        double max5 = 0;
        for(int i = 0; i < list.size(); ++i){
            sum5 = sum5 + list.get(i).getWight();
            if(list.get(i).getWight() > max5){
                maxKg.setValue(list.get(i).getWight());
                max5 = list.get(i).getWight();
            }
        }
        averageKg.setValue(sum5 / list.size());

    }


    private void initializeParts() {
        //initialize parts
        double center = ARTBOARD_WIDTH * 0.5;

        titleChf =         new Label("CHF");
        titleKmLadung =    new Label("km/Lad.");
        titleKw =          new Label("kW");
        titleKmh =         new Label("km/h");
        titleKg =          new Label("kg");
        labArray =         new Label[]{titleChf, titleKmLadung, titleKw, titleKmh, titleKg};

        valueLegend =      new Label("Aktueller Wert");
        averageLegend =    new Label("Durch. Wert");

        circleChf =        new Circle(CIRCLE_RADIUS);
        circleKmLadung =   new Circle(CIRCLE_RADIUS);
        circleKw =         new Circle(CIRCLE_RADIUS);
        circleKmh =        new Circle(CIRCLE_RADIUS);
        circleKg =         new Circle(CIRCLE_RADIUS);
        circArray =        new Circle[]{circleChf, circleKmLadung, circleKw, circleKmh, circleKg};

        rectAverageChf =      new Rectangle(BAR_WIDTH, 0);
        rectAverageKmLadung = new Rectangle(BAR_WIDTH, 0);
        rectAverageKw =       new Rectangle(BAR_WIDTH, 0);
        rectAverageKmh =      new Rectangle(BAR_WIDTH, 0);
        rectAverageKg =       new Rectangle(BAR_WIDTH, 0);
        rectAverageArray =    new Rectangle[]{rectAverageChf, rectAverageKmLadung, rectAverageKw,
                rectAverageKmh, rectAverageKg};

        rectActualChf =       new Rectangle(BAR_WIDTH, 0);
        rectActualKmLadung =  new Rectangle(BAR_WIDTH, 0);
        rectActualKw =        new Rectangle(BAR_WIDTH, 0);
        rectActualKmh =       new Rectangle(BAR_WIDTH, 0);
        rectActualKg =        new Rectangle(BAR_WIDTH, 0);
        rectActualArray =     new Rectangle[]{rectActualChf, rectActualKmLadung, rectActualKw,
                rectActualKmh, rectActualKg};

        titleMax =         new Label("MAX");

        // always needed
        drawingPane = new Pane();
        drawingPane.getStyleClass().add("drawingPane");
        drawingPane.setMaxSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setMinSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
        drawingPane.setPrefSize(ARTBOARD_WIDTH, ARTBOARD_HEIGHT);
    }

    private void layoutParts() {
        setPadding(new Insets(10, 10, 10, 10));

        // layout all parts of bar graph
        StackPane[] barPanes = new StackPane[5];
        StackPane[] circlePanes = new StackPane[5];
        for(int i = 0; i < barPanes.length; ++i){
            //create pane for circle with text
            circlePanes[i] = new StackPane(circArray[i], labArray[i]);
            circArray[i].getStyleClass().add("circle");
            labArray[i].getStyleClass().add("label");
            StackPane.setAlignment(circArray[i], Pos.TOP_CENTER);
            StackPane.setAlignment(labArray[i], Pos.TOP_CENTER);

            //create pane for the bar's and insert circle panes too
            barPanes[i] = new StackPane(rectAverageArray[i], rectActualArray[i], circlePanes[i]);
            rectAverageArray[i].getStyleClass().add("averageBar");
            rectActualArray[i].getStyleClass().add("actualBar");
            rectActualArray[i].setTranslateX(-2);
            StackPane.setAlignment(rectAverageArray[i], Pos.TOP_CENTER);
            StackPane.setAlignment(rectActualArray[i], Pos.TOP_CENTER);

            //adding rotation to bar's to display them in the right direction
            rectAverageArray[i].getTransforms().add(new Rotate(180,
                    rectAverageArray[i].getX() + (BAR_WIDTH / 2),
                    rectAverageArray[i].getY() + 5));

            rectActualArray[i].getTransforms().add(new Rotate(180,
                    rectActualArray[i].getX() + (BAR_WIDTH / 2),
                    rectActualArray[i].getY() + 5 ));

            //setting position of panes
            barPanes[i].setLayoutX(RECT_DISTANCE * (i + 1));
            barPanes[i].setLayoutY(CIRCLE_YPOS);

            drawingPane.getChildren().addAll(barPanes[i]);
        }

        //layout max label
        titleMax.setLayoutX(10);
        titleMax.setLayoutY(-7);
        titleMax.getStyleClass().add("minmaxLabel");
        titleMax.setAlignment(Pos.TOP_LEFT);

        //for the max bar
        Separator sep = new Separator();
        sep.setOrientation(Orientation.HORIZONTAL);
        sep.setLayoutX(30);
        sep.setLayoutY(1);
        sep.setMinWidth(155);
        sep.getStyleClass().add("seperator");

        //layout the legend
        valueLegend.getStyleClass().add("wertLabel");
        averageLegend.getStyleClass().add("durchLabel");

        valueLegend.setLayoutY(50);
        averageLegend.setLayoutY(70);

        valueLegend.setShape(new Circle(10));
        averageLegend.setShape(new Circle(10));

        drawingPane.getChildren().addAll(titleMax, sep, valueLegend, averageLegend);
        getChildren().add(drawingPane);
    }

    private void setupValueChangeListenersAndAnimations() {
        priceProperty().addListener(observable -> {
            //neccessary, to later calculate the height of the bar's
            calculateAveragesAndMax();

            //animate the changes
            KeyValue keyValue = new KeyValue(rectAverageChf.heightProperty(), calculateValueToPixels(averagePrice, maxPrice));
            KeyValue keyValue1 = new KeyValue(rectActualChf.heightProperty(), calculateValueToPixels(price, maxPrice));

            KeyFrame keyFrame = new KeyFrame(Duration.millis(2000), keyValue);
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(2000), keyValue1);

            Timeline timeline = new Timeline(keyFrame);
            Timeline timeline1 = new Timeline(keyFrame1);

            ParallelTransition pt = new ParallelTransition(timeline1, timeline);

            pt.play();

            //highlight bar if max value
            if(price.get() == maxPrice.get()){
                rectActualChf.getStyleClass().add("hitMax");
            }else {
                rectActualChf.getStyleClass().removeAll("hitMax");
            }

            //setting the z-index of the nodes
            if(averagePrice.get() < price.get()){
                rectActualChf.toBack();
            }else {
                rectAverageChf.toBack();
            }
        });

        kmLadungProperty().addListener(observable -> {
            //neccessary, to later calculate the height of the bar's
            calculateAveragesAndMax();

            //animate the changes
            KeyValue keyValue3 = new KeyValue(rectAverageKmLadung.heightProperty(), calculateValueToPixels(averageKmLadung, maxKmLadung));
            KeyValue keyValue4 = new KeyValue(rectActualKmLadung.heightProperty(), calculateValueToPixels(kmLadung, maxKmLadung));

            KeyFrame keyFrame3 = new KeyFrame(Duration.millis(2000), keyValue3);
            KeyFrame keyFrame4 = new KeyFrame(Duration.millis(2000), keyValue4);

            Timeline timeline3 = new Timeline(keyFrame3);
            Timeline timeline4 = new Timeline(keyFrame4);

            ParallelTransition pt1 = new ParallelTransition(timeline3, timeline4);

            pt1.play();

            //highlight bar if max value
            if(kmLadung.get() == maxKmLadung.get()){
                rectActualKmLadung.getStyleClass().add("hitMax");
            }else {
                rectActualKmLadung.getStyleClass().removeAll("hitMax");
            }

            //setting the z-index of the nodes
            if(averageKmLadung.get() < kmLadung.get()){
                rectActualKmLadung.toBack();
            }else {
                rectAverageKmLadung.toBack();
            }
        });

        kwProperty().addListener(observable -> {
            //neccessary, to later calculate the height of the bar's
            calculateAveragesAndMax();

            //animate the changes
            KeyValue keyValue5 = new KeyValue(rectAverageKw.heightProperty(), calculateValueToPixels(averageKw, maxKw));
            KeyValue keyValue6 = new KeyValue(rectActualKw.heightProperty(), calculateValueToPixels(kw, maxKw));

            KeyFrame keyFrame5 = new KeyFrame(Duration.millis(2000), keyValue5);
            KeyFrame keyFrame6 = new KeyFrame(Duration.millis(2000), keyValue6);

            Timeline timeline5 = new Timeline(keyFrame5);
            Timeline timeline6 = new Timeline(keyFrame6);

            ParallelTransition pt3 = new ParallelTransition(timeline5, timeline6);

            pt3.play();

            //highlight bar if max value
            if(kw.get() == maxKw.get()){
                rectActualKw.getStyleClass().add("hitMax");
            }else {
                rectActualKw.getStyleClass().removeAll("hitMax");
            }

            //setting the z-index of the nodes
            if(averageKw.get() < kw.get()){
                rectActualKw.toBack();
            }else {
                rectAverageKw.toBack();
            }
        });

        kmhProperty().addListener(observable -> {
            //neccessary, to later calculate the height of the bar's
            calculateAveragesAndMax();

            //animate the changes
            KeyValue keyValue7 = new KeyValue(rectAverageKmh.heightProperty(), calculateValueToPixels(averageKmh, maxKmh));
            KeyValue keyValue8 = new KeyValue(rectActualKmh.heightProperty(), calculateValueToPixels(kmh, maxKmh));

            KeyFrame keyFrame7 = new KeyFrame(Duration.millis(2000), keyValue7);
            KeyFrame keyFrame8 = new KeyFrame(Duration.millis(2000), keyValue8);

            Timeline timeline7 = new Timeline(keyFrame7);
            Timeline timeline8 = new Timeline(keyFrame8);

            ParallelTransition pt4 = new ParallelTransition(timeline7, timeline8);

            pt4.play();

            //highlight bar if max value
            if(kmh.get() == maxKmh.get()){
                rectActualKmh.getStyleClass().add("hitMax");
            }else {
                rectActualKmh.getStyleClass().removeAll("hitMax");
            }

            //setting the z-index of the nodes
            if(averageKmh.get() < kmh.get()){
                rectActualKmh.toBack();
            }else {
                rectAverageKmh.toBack();
            }
        });

        kgProperty().addListener(observable -> {
            //neccessary, to later calculate the height of the bar's
            calculateAveragesAndMax();

            //animate the changes
            KeyValue keyValue9 = new KeyValue(rectAverageKg.heightProperty(), calculateValueToPixels(averageKg, maxKg));
            KeyValue keyValue0 = new KeyValue(rectActualKg.heightProperty(), calculateValueToPixels(kg, maxKg));

            KeyFrame keyFrame9 = new KeyFrame(Duration.millis(2000), keyValue9);
            KeyFrame keyFrame0 = new KeyFrame(Duration.millis(2000), keyValue0);

            Timeline timeline9 = new Timeline(keyFrame9);
            Timeline timeline0 = new Timeline(keyFrame0);

            ParallelTransition pt5 = new ParallelTransition(timeline9, timeline0);

            pt5.play();

            //highlight bar if max value
            if(kg.get() == maxKg.get()){
                rectActualKg.getStyleClass().add("hitMax");
            }else {
                rectActualKg.getStyleClass().removeAll("hitMax");
            }

            //setting the z-index of the nodes
            if(averageKg.get() < kg.get()){
                rectActualKg.toBack();
            }else {
                rectAverageKg.toBack();
            }
        });

    }

    private void setupBinding() {
        titleChf.textProperty().bindBidirectional(priceProperty(), toMeasurement("CHF"));
        titleKmLadung.textProperty().bindBidirectional(kmLadungProperty(), toMeasurement("km/Lad."));
        titleKw.textProperty().bindBidirectional(kwProperty(), toMeasurement("kW"));
        titleKmh.textProperty().bindBidirectional(kmhProperty(), toMeasurement("km/h"));
        titleKg.textProperty().bindBidirectional(kgProperty(), toMeasurement("kg"));
    }


    private void setupEventHandlers() {
        rectActualChf.setOnMouseDragged(event -> {
            double height = event.getY();
            if(height >= 100){
                rectActualChf.heightProperty().set(100);
            }else{
                rectActualChf.heightProperty().set(height);
            }

            priceProperty().set(Math.floor(calculatePixelToValue(rectActualChf.heightProperty(), maxPriceProperty())));
        });

        rectActualKmLadung.setOnMouseDragged(event -> {
            double height = event.getY();
            if(height >= 100){
                rectActualKmLadung.heightProperty().set(100);
            }else{
                rectActualKmLadung.heightProperty().set(height);
            }

            kmLadungProperty().set(Math.floor(calculatePixelToValue(rectActualKmLadung.heightProperty(), maxKmLadungProperty())));
        });

        rectActualKw.setOnMouseDragged(event -> {
            double height = event.getY();
            //if mouse is dragged over the bar
            if(height >= 100){
                rectActualKw.heightProperty().set(100);
            }else{
                rectActualKw.heightProperty().set(height);
            }

            kwProperty().set(Math.floor(calculatePixelToValue(rectActualKw.heightProperty(), maxKwProperty())));
        });

        rectActualKmh.setOnMouseDragged(event -> {
            double height = event.getY();
            if(height >= 100){
                rectActualKmh.heightProperty().set(100);
            }else{
                rectActualKmh.heightProperty().set(height);
            }

            kmhProperty().set(Math.floor(calculatePixelToValue(rectActualKmh.heightProperty(), maxKmhProperty())));
        });

        rectActualKg.setOnMouseDragged(event -> {
            double height = event.getY();
            if(height >= 100){
                rectActualKg.heightProperty().set(100);
            }else {
                rectActualKg.heightProperty().set(height);
            }

            kgProperty().set(Math.floor(calculatePixelToValue(rectActualKg.heightProperty(), maxKgProperty())));
        });
    }

    private StringConverter<Number> toMeasurement(String measure){

        StringConverter<Number> doubleStringConverter = new StringConverter<Number>() {
            @Override
            public String toString(Number n) {
                return  String.format("%3d", n.intValue()) + "\n" + measure ;
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.valueOf(string);
                }
                catch (NumberFormatException ex){
                    return 0.0;
                }
            }
        };

        return doubleStringConverter;
    }


    private double calculateValueToPixels(DoubleProperty value, DoubleProperty max){
        //max height of bar's are 100px
        double pixel = value.multiply(100).divide(max).get();
        return pixel;
    }

    private double calculatePixelToValue(DoubleProperty pixel, DoubleProperty max){
        double value = pixel.multiply(max).divide(100).get();
        return value;
    }

    //resize by scaling
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        resize();
    }

    private void resize() {
        Insets padding         = getPadding();
        double availableWidth  = getWidth() - padding.getLeft() - padding.getRight();
        double availableHeight = getHeight() - padding.getTop() - padding.getBottom();

        double width = Math.max(Math.min(Math.min(availableWidth, availableHeight * ASPECT_RATIO), MAXIMUM_WIDTH), MINIMUM_WIDTH);

        double scalingFactor = width / ARTBOARD_WIDTH;

        if (availableWidth > 0 && availableHeight > 0) {
            relocateCentered();
            drawingPane.setScaleX(scalingFactor);
            drawingPane.setScaleY(scalingFactor);
        }
    }

    private void relocateCentered() {
        drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, (getHeight() - ARTBOARD_HEIGHT) * 0.5);
    }

    private void relocateCenterBottom(double scaleY, double paddingBottom) {
        double visualHeight = ARTBOARD_HEIGHT * scaleY;
        double visualSpace  = getHeight() - visualHeight;
        double y            = visualSpace + (visualHeight - ARTBOARD_HEIGHT) * 0.5 - paddingBottom;

        drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, y);
    }

    private void relocateCenterTop(double scaleY, double paddingTop) {
        double visualHeight = ARTBOARD_HEIGHT * scaleY;
        double y            = (visualHeight - ARTBOARD_HEIGHT) * 0.5 + paddingTop;

        drawingPane.relocate((getWidth() - ARTBOARD_WIDTH) * 0.5, y);
    }

    // some handy functions

    private double percentageToValue(double percentage, double minValue, double maxValue){
        return ((maxValue - minValue) * percentage) + minValue;
    }

    private double valueToPercentage(double value, double minValue, double maxValue) {
        return (value - minValue) / (maxValue - minValue);
    }

    private double valueToAngle(double value, double minValue, double maxValue) {
        return percentageToAngle(valueToPercentage(value, minValue, maxValue));
    }

    private double mousePositionToValue(double mouseX, double mouseY, double cx, double cy, double minValue, double maxValue){
        double percentage = angleToPercentage(angle(cx, cy, mouseX, mouseY));

        return percentageToValue(percentage, minValue, maxValue);
    }

    private double angleToPercentage(double angle){
        return angle / 360.0;
    }

    private double percentageToAngle(double percentage){
        return 360.0 * percentage;
    }

    private double angle(double cx, double cy, double x, double y) {
        double deltaX = x - cx;
        double deltaY = y - cy;
        double radius = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        double nx     = deltaX / radius;
        double ny     = deltaY / radius;
        double theta  = Math.toRadians(90) + Math.atan2(ny, nx);

        return Double.compare(theta, 0.0) >= 0 ? Math.toDegrees(theta) : Math.toDegrees((theta)) + 360.0;
    }

    private Point2D pointOnCircle(double cX, double cY, double radius, double angle) {
        return new Point2D(cX - (radius * Math.cos(Math.toRadians(angle - 90))),
                cY + (radius * Math.sin(Math.toRadians(angle - 90))));
    }

    private Text createCenteredText(String styleClass) {
        return createCenteredText(ARTBOARD_WIDTH * 0.5, ARTBOARD_HEIGHT * 0.5, styleClass);
    }

    private Text createCenteredText(double cx, double cy, String styleClass) {
        Text text = new Text();
        text.getStyleClass().add(styleClass);
        text.setTextOrigin(VPos.CENTER);
        text.setTextAlignment(TextAlignment.CENTER);
        double width = cx > ARTBOARD_WIDTH * 0.5 ? ((ARTBOARD_WIDTH - cx) * 2.0) : cx * 2.0;
        text.setWrappingWidth(width);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setY(cy);
        text.setX(cx - (width / 2.0));

        return text;
    }

    private Group createTicks(double cx, double cy, int numberOfTicks, double overallAngle, double tickLength, double indent, double startingAngle, String styleClass) {
        Group group = new Group();

        double degreesBetweenTicks = overallAngle == 360 ?
                overallAngle /numberOfTicks :
                overallAngle /(numberOfTicks - 1);
        double outerRadius         = Math.min(cx, cy) - indent;
        double innerRadius         = Math.min(cx, cy) - indent - tickLength;

        for (int i = 0; i < numberOfTicks; i++) {
            double angle = 180 + startingAngle + i * degreesBetweenTicks;

            Point2D startPoint = pointOnCircle(cx, cy, outerRadius, angle);
            Point2D endPoint   = pointOnCircle(cx, cy, innerRadius, angle);

            Line tick = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
            tick.getStyleClass().add(styleClass);
            group.getChildren().add(tick);
        }

        return group;
    }

    private String colorToCss(final Color color) {
        return color.toString().replace("0x", "#");
    }


    // compute sizes

    @Override
    protected double computeMinWidth(double height) {
        Insets padding           = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return MINIMUM_WIDTH + horizontalPadding;
    }

    @Override
    protected double computeMinHeight(double width) {
        Insets padding         = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return MINIMUM_HEIGHT + verticalPadding;
    }

    @Override
    protected double computePrefWidth(double height) {
        Insets padding           = getPadding();
        double horizontalPadding = padding.getLeft() + padding.getRight();

        return ARTBOARD_WIDTH + horizontalPadding;
    }

    @Override
    protected double computePrefHeight(double width) {
        Insets padding         = getPadding();
        double verticalPadding = padding.getTop() + padding.getBottom();

        return ARTBOARD_HEIGHT + verticalPadding;
    }

    // all getter and setter
    public double getAveragePrice() {
        return averagePrice.get();
    }

    public DoubleProperty averagePriceProperty() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice.set(averagePrice);
    }

    public double getAverageKmLadung() {
        return averageKmLadung.get();
    }

    public DoubleProperty averageKmLadungProperty() {
        return averageKmLadung;
    }

    public void setAverageKmLadung(double averageKmLadung) {
        this.averageKmLadung.set(averageKmLadung);
    }

    public double getAverageKw() {
        return averageKw.get();
    }

    public DoubleProperty averageKwProperty() {
        return averageKw;
    }

    public void setAverageKw(double averageKw) {
        this.averageKw.set(averageKw);
    }

    public double getAverageKmh() {
        return averageKmh.get();
    }

    public DoubleProperty averageKmhProperty() {
        return averageKmh;
    }

    public void setAverageKmh(double averageKmh) {
        this.averageKmh.set(averageKmh);
    }

    public double getAverageKg() {
        return averageKg.get();
    }

    public DoubleProperty averageKgProperty() {
        return averageKg;
    }

    public void setAverageKg(double averageKg) {
        this.averageKg.set(averageKg);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public double getKmLadung() {
        return kmLadung.get();
    }

    public DoubleProperty kmLadungProperty() {
        return kmLadung;
    }

    public void setKmLadung(double kmLadung) {
        this.kmLadung.set(kmLadung);
    }

    public double getKw() {
        return kw.get();
    }

    public DoubleProperty kwProperty() {
        return kw;
    }

    public void setKw(double kw) {
        this.kw.set(kw);
    }

    public double getKmh() {
        return kmh.get();
    }

    public DoubleProperty kmhProperty() {
        return kmh;
    }

    public void setKmh(double kmh) {
        this.kmh.set(kmh);
    }

    public double getKg() {
        return kg.get();
    }

    public DoubleProperty kgProperty() {
        return kg;
    }

    public void setKg(double kg) {
        this.kg.set(kg);
    }

    public double getMaxPrice() {
        return maxPrice.get();
    }

    public DoubleProperty maxPriceProperty() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice.set(maxPrice);
    }

    public double getMaxKmLadung() {
        return maxKmLadung.get();
    }

    public DoubleProperty maxKmLadungProperty() {
        return maxKmLadung;
    }

    public void setMaxKmLadung(double maxKmLadung) {
        this.maxKmLadung.set(maxKmLadung);
    }

    public double getMaxKw() {
        return maxKw.get();
    }

    public DoubleProperty maxKwProperty() {
        return maxKw;
    }

    public void setMaxKw(double maxKw) {
        this.maxKw.set(maxKw);
    }

    public double getMaxKmh() {
        return maxKmh.get();
    }

    public DoubleProperty maxKmhProperty() {
        return maxKmh;
    }

    public void setMaxKmh(double maxKmh) {
        this.maxKmh.set(maxKmh);
    }

    public double getMaxKg() {
        return maxKg.get();
    }

    public DoubleProperty maxKgProperty() {
        return maxKg;
    }

    public void setMaxKg(double maxKg) {
        this.maxKg.set(maxKg);
    }

}
