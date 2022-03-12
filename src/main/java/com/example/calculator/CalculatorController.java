package com.example.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CalculatorController {

    ArithmeticApp arithmeticApp;
    String expretion="";
    int base = 10;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button A;

    @FXML
    private Button B;

    @FXML
    private Button C;

    @FXML
    private Button Clear;

    @FXML
    private Button D;

    @FXML
    private Button E;

    @FXML
    private Button F;

    @FXML
    private Button add;

    @FXML
    private Button div;

    @FXML
    private Button eight;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private ComboBox<String> listBox;

    @FXML
    private Button mul;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button six;

    @FXML
    private Button solve;

    @FXML
    private Button sub;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;

    @FXML
    private TextField textField;

    @FXML
    private Button seven;

    @FXML
    void chooseFromList(ActionEvent event) {
        String chose = listBox.getSelectionModel().getSelectedItem();
        switch (chose){
            case "hex":
                base = 16;
                break;
            case "dec":
                base=10;
                break;
            case "oct":
                base=8;
                break;
            case "bin":
                base=2;
                break;
        }
    }

    @FXML
    void clear(ActionEvent event) {
        expretion="";
        setLine();
    }

    @FXML
    void solve(ActionEvent event) {
        expretion = ArithmeticApp.solve(expretion, base);
        setLine();
    }

    @FXML
    void typeButton(ActionEvent event) {

    }

    private void setLine(){
        textField.setText(expretion);
    }

    private EventHandler<ActionEvent> type1(char c){
        expretion+=c;
        setLine();
        return null;
    }

    @FXML
    void initialize() {
        //init text field
        textField.setText("");

        //init the list box
        listBox.getItems().add("hex");
        listBox.getItems().add("dec");
        listBox.getItems().add("oct");
        listBox.getItems().add("bin");

        //init on action parameter fot buttom
        one.setOnAction((even)->type1('1'));
        two.setOnAction((even)->type1('2'));
        three.setOnAction((even)->type1('3'));
        four.setOnAction((even)->type1('4'));
        five.setOnAction((even)->type1('5'));
        six.setOnAction((even)->type1('6'));
        seven.setOnAction((even)->type1('7'));
        eight.setOnAction((even)->type1('8'));
        nine.setOnAction((even)->type1('9'));
        A.setOnAction((even)->type1('A'));
        B.setOnAction((even)->type1('B'));
        C.setOnAction((even)->type1('C'));
        D.setOnAction((even)->type1('D'));
        E.setOnAction((even)->type1('E'));
        F.setOnAction((even)->type1('F'));

        sub.setOnAction((even)->type1('-'));
        add.setOnAction((even)->type1('+'));
        mul.setOnAction((even)->type1('*'));
        div.setOnAction((even)->type1('/'));

    }

}
