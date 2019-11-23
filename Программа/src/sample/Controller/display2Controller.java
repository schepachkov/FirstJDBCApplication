package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.myClass.OpenWindow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class display2Controller {


    @FXML
    private Button btnGetBook;

    @FXML
    private Button btnAddBook;

    @FXML
    private Button btnAddClient;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnOstatki;

    @FXML
    private Button btnReturnBook;

    private OpenWindow openWindow = new OpenWindow();


    public void addClientOnAction(ActionEvent actionEvent) {
        openWindow.openNewWindow("addClient.fxml");
    }

    public void exitOnAction(ActionEvent actionEvent) {

        System.exit(0);
    }

    public void getBookOnAction(ActionEvent actionEvent) {
        openWindow.openNewWindow("giveBook.fxml");
    }

    public void prosmotrOstatkovOnAction(ActionEvent actionEvent) {
        openWindow.openNewWindow("prosmotrOstatkov.fxml");
    }

    public void addBookOnAction(ActionEvent actionEvent) {
        openWindow.openNewWindow("addBook.fxml");
    }

    public void returnBookOnAction(ActionEvent actionEvent) {
        openWindow.openNewWindow("returnBook.fxml");
    }
}
