package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.myClass.Book;
import sample.myClass.DataBaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class prosmotrOstatkovController {

    @FXML
    private TextField txtBookName;

    @FXML
    private Label label;

    @FXML
    private Button btnGoTo2Display;

    @FXML
    private Label labelResult;

    @FXML
    private Button btnOstatki;

    @FXML
    void goTo2DisplayOnAction(ActionEvent event) {
        btnGoTo2Display.getScene().getWindow().hide();

    }

    @FXML
    void ostatkiToIntOnAction(ActionEvent event) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String bookName = txtBookName.getText();
        Book book = new Book(bookName);
        ResultSet resSet = dbHandler.ostatkiView(book);
        int a = 0;
        try {
            while (resSet.next()) {
                a += resSet.getInt("kolvo");
            }
            if (!(a == 0)) {
                label.setText("Колличество:");
                labelResult.setText(String.valueOf(a));
            }
            else {
                label.setText("Данная книга отсутствует");
                labelResult.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

