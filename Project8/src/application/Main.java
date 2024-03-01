package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
	



public class Main extends Application {
    ArrayList<Planets> alPlanets;

    RadioButton rbMercury, rbVenus, rbEarth, rbMars, rbJupiter, rbSaturn, rbUranus, rbNeptune;
    CheckBox cbSurfaceArea, cbCircumference, cbDistanceToSun, cbWeight;
    Button btnDisplay, btnClear;
    TextField tfWeight;
    TextArea taOutput;

    VBox vBoxCheckBoxes;

    double PI = 3.14159;
    double surfaceArea;
    double circumference;
    double convertedWeight;
    double distanceToTheSun;

    double radius, weightRatio;

    String outputCalculation = "";

    String strSurfaceArea = "";
    String strCircumference = "";
    String strConvertedWeight = "";
    String strDistanceToTheSun = "";

    VBox vBoxOutput;
    Label labelOutputPlanet;

    String selectedPlaneName;
    boolean validWeight = false;


    @Override
    public void start(Stage stage) {

        alPlanets = new ArrayList<>();

        VBox mainVBox = new VBox();


        HBox mainHBox = new HBox();
        mainHBox.setPadding(new Insets(20, 20, 20, 20));

        mainHBox.setSpacing(60);

        mainHBox.getChildren().add(createRadioRadioButtons());

        mainHBox.getChildren().add(createCheckBoxes());

        mainHBox.getChildren().add(addOutputBox());

        mainVBox.setSpacing(10);

        mainVBox.getChildren().addAll(mainHBox, createButtons());


        btnDisplay.setOnAction(event -> {

            getAlPlanetsFromFile();

            try {
                taOutput.setText(calculateValueDisplay());
            } catch (NegativeInputValueException | EmptyStringException | InputMismatchException nie) {
                taOutput.setText("");
                System.out.println(nie.getMessage());
            }

        });

        btnClear.setOnAction(event -> {

            rbMercury.setSelected(true);

            cbSurfaceArea.setSelected(false);
            cbWeight.setSelected(false);
            cbCircumference.setSelected(false);
            cbDistanceToSun.setSelected(false);
            tfWeight.setText("");
            vBoxCheckBoxes.getChildren().remove(tfWeight);
            taOutput.setText("");
            labelOutputPlanet.setText("");

        });

        Scene scene = new Scene(mainVBox);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setTitle("Planets");
        stage.show();

    }

    public VBox addOutputBox() {

        vBoxOutput = new VBox();

        labelOutputPlanet = new Label();
        labelOutputPlanet.setAlignment(Pos.CENTER);
        changeLabelSize(labelOutputPlanet, 20);

        taOutput = new TextArea();
        taOutput.setMaxWidth(220);
        taOutput.setMinHeight(200);
        taOutput.setEditable(false);

        vBoxOutput.setSpacing(10);
        vBoxOutput.getChildren().addAll(labelOutputPlanet, taOutput);

        return vBoxOutput;

    }

    public String calculateValueDisplay() throws NegativeInputValueException, EmptyStringException {

        if (rbMercury.isSelected()) {
            assignValueForSelectedRadioButton(rbMercury);
        } else if (rbVenus.isSelected()) {
            assignValueForSelectedRadioButton(rbVenus);
        } else if (rbEarth.isSelected()) {
            assignValueForSelectedRadioButton(rbEarth);
        } else if (rbMercury.isSelected()) {
            assignValueForSelectedRadioButton(rbMercury);
        } else if (rbJupiter.isSelected()) {
            assignValueForSelectedRadioButton(rbJupiter);
        } else if (rbSaturn.isSelected()) {
            assignValueForSelectedRadioButton(rbSaturn);
        } else if (rbUranus.isSelected()) {
            assignValueForSelectedRadioButton(rbUranus);
        } else if (rbNeptune.isSelected()) {
            assignValueForSelectedRadioButton(rbNeptune);
        }

        outputCalculation = strSurfaceArea + strCircumference + strDistanceToTheSun + strConvertedWeight;

        if (validWeight) {
            return outputCalculation;
        } else {
            return "";
        }
    }

    public void assignValueForSelectedRadioButton(RadioButton rbSelected) throws NegativeInputValueException, EmptyStringException {

        for (int i = 0; i < alPlanets.size(); i++) {
            if (alPlanets.get(i).planetName.equals(rbSelected.getText())) {
                radius = alPlanets.get(i).planetRadius;
                weightRatio = alPlanets.get(i).planetWeightRatio;
                distanceToTheSun = alPlanets.get(i).planetDistanceFromTheSun;
            }
        }

        selectedPlaneName = rbSelected.getText();
        labelOutputPlanet.setText(selectedPlaneName);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        if (cbSurfaceArea.isSelected()) {
            surfaceArea = 4 * PI * radius * radius;
            strSurfaceArea = "Surface Area : " + decimalFormat.format(surfaceArea) + " km \n";
        } else {
            strSurfaceArea = "";
        }

        if (cbCircumference.isSelected()) {
            circumference = 2 * PI * radius;
            strCircumference = "Circumference : " + decimalFormat.format(circumference) + " km\n";
        } else {
            strCircumference = "";
        }

        if (cbDistanceToSun.isSelected()) {
            distanceToTheSun = distanceToTheSun;
            strDistanceToTheSun = "Distance To The Sun : " + decimalFormat.format(distanceToTheSun) + " AU\n";
        } else {
            strDistanceToTheSun = "";
        }

        if (cbWeight.isSelected()) {

            try {
                double weight;

                if (tfWeight.getText().length() == 0) {
                    taOutput.setText("");
                    validWeight = false;
                    throw new EmptyStringException("Weight can not be empty.\nPlease enter Positive value of Weight.");
                } else {

                    weight = Double.parseDouble(tfWeight.getText());


                    if (tfWeight.getText().length() == 0) {

                        taOutput.setText("");
                        validWeight = false;

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Empty Input");
                        alert.setHeaderText("Weight can not be empty Please enter Positive value of Weight.");
                        alert.showAndWait();

                        //To End Program when OK button is clicked in error dialog
                        if (alert.getResult() == ButtonType.OK) {
                            tfWeight.requestFocus();
                        }
                        throw new EmptyStringException("Empty String");
                    }

                    if (weight < 0) {

                        taOutput.setText("");
                        validWeight = false;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Negative Input");
                        alert.setHeaderText("Weight (" + weight + ") Can not be Negative.\nPlease enter Positive value of Weight.");
                        alert.showAndWait();

                        //To End Program when OK button is clicked in error dialog
                        if (alert.getResult() == ButtonType.OK) {
                            tfWeight.requestFocus();
                        }

                        throw new NegativeInputValueException("Weight " + weight + " Can not be Negative.Please enter Positive value of Weight.");
                    }

                }


                convertedWeight = weight * weightRatio;
                strConvertedWeight = "Weight : " + decimalFormat.format(convertedWeight) + " kg\n";

            } catch (NumberFormatException | InputMismatchException ime) {

                taOutput.setText("");

                validWeight = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Mismatch Input");
                alert.setHeaderText("Weight can not be " + tfWeight.getText() + "\nPlease enter Positive value of Weight.");
                alert.showAndWait();

                //To End Program when OK button is clicked in error dialog
                if (alert.getResult() == ButtonType.OK) {
                    tfWeight.requestFocus();
                }
            } catch (EmptyStringException e) {

                taOutput.setText("");

                validWeight = false;

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Input");
                alert.setHeaderText("Weight can not be empty.\nPlease enter Positive value of Weight.");
                alert.showAndWait();

                //To End Program when OK button is clicked in error dialog
                if (alert.getResult() == ButtonType.OK) {
                    tfWeight.requestFocus();
                }
            }

        } else {
            validWeight = true;
            strConvertedWeight = "";
        }
    }


    public VBox createRadioRadioButtons() {

        VBox vBoxRadioButtons = new VBox();

        ToggleGroup toggleGroup = new ToggleGroup();

        rbMercury = new RadioButton("Mercury");
        rbEarth = new RadioButton("Earth");
        rbJupiter = new RadioButton("Jupiter");
        rbMars = new RadioButton("Mars");
        rbNeptune = new RadioButton("Neptune");
        rbSaturn = new RadioButton("Saturn");
        rbUranus = new RadioButton("Uranus");
        rbVenus = new RadioButton("Venus");

        rbMercury.setToggleGroup(toggleGroup);
        rbEarth.setToggleGroup(toggleGroup);
        rbJupiter.setToggleGroup(toggleGroup);
        rbMars.setToggleGroup(toggleGroup);
        rbNeptune.setToggleGroup(toggleGroup);
        rbSaturn.setToggleGroup(toggleGroup);
        rbUranus.setToggleGroup(toggleGroup);
        rbVenus.setToggleGroup(toggleGroup);

        rbMercury.setSelected(true);
        vBoxRadioButtons.setSpacing(10);

        Label labelPlanet = new Label("Planets");
        changeLabelSize(labelPlanet, 20);

        vBoxRadioButtons.getChildren().addAll(labelPlanet, rbMercury, rbEarth, rbJupiter, rbMars, rbNeptune, rbSaturn, rbUranus, rbVenus);

//        vBoxRadioButtons.setStyle("-fx-padding: 20;" +
//                "-fx-border-style: solid inside;" +
//                "-fx-border-width: 1;" +
//                "-fx-border-insets: 5;" +
//                "-fx-border-radius: 5;");

        return vBoxRadioButtons;


    }

    public HBox createButtons() {

        HBox hBoxButtons = new HBox();

        hBoxButtons.setAlignment(Pos.CENTER);

        btnDisplay = new Button("Display");
        btnDisplay.setMinWidth(100);
        btnClear = new Button("Clear");
        btnClear.setMinWidth(100);

        hBoxButtons.setSpacing(20);
        hBoxButtons.getChildren().addAll(btnDisplay, btnClear);

        return hBoxButtons;

    }

    public VBox createCheckBoxes() {

        vBoxCheckBoxes = new VBox();

        cbSurfaceArea = new CheckBox("Surface area");
        cbCircumference = new CheckBox("Circumference");
        cbDistanceToSun = new CheckBox("Distance to Sun");
        cbWeight = new CheckBox("Weight");

        vBoxCheckBoxes.setSpacing(10);

        Label labelCalculations = new Label("Calculations");
        changeLabelSize(labelCalculations, 20);

        tfWeight = new TextField();
        tfWeight.setMaxWidth(100);

        vBoxCheckBoxes.getChildren().addAll(labelCalculations, cbSurfaceArea, cbCircumference, cbDistanceToSun, cbWeight);


        cbWeight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (cbWeight.isSelected()) {
                    vBoxCheckBoxes.getChildren().add(tfWeight);
                } else {
                    vBoxCheckBoxes.getChildren().remove(tfWeight);
                }

            }
        });
        return vBoxCheckBoxes;
    }


    public void changeLabelSize(Label label, int size) {
        label.setFont(Font.font("", FontWeight.BOLD, size));
    }

    public void getAlPlanetsFromFile() {

        String fileName = "PlanetInfo.txt";

        try {
            Scanner sc = new Scanner(new File(fileName));

            String line;

            while (sc.hasNextLine())  //returns a boolean value
            {
                line = sc.nextLine();
                String[] words = line.split(",");

                Planets planets = new Planets(words[0], Double.parseDouble(words[1]), (float) Double.parseDouble(words[2]), Double.parseDouble(words[3]));  //find and returns the next complete token from this scanner
                alPlanets.add(planets);

            }
            sc.close();

        } catch (FileNotFoundException fileNotFoundException) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Not Found");
            alert.setHeaderText("Can not Locate File : " + fileName + " Please check File name with Path");
            alert.showAndWait();

            //To End Program when OK button is clicked in error dialog
            if (alert.getResult() == ButtonType.OK) {
                System.exit(0);
            }

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
