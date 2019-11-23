package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.myClass.Book;
import sample.myClass.DataBaseHandler;

public class addBookController {

    @FXML
    private TextField txtGodIzd;

    @FXML
    private TextField txtNameBook;

    @FXML
    private Button btnAddBookInDB;

    @FXML
    private Button btnGoToDisplay2;

    @FXML
    private TextField txtKolvo;

    @FXML
    private TextField txtAuthor;

    @FXML
    void goToDisplay2OnAction(ActionEvent event) {
        btnGoToDisplay2.getScene().getWindow().hide();
    }

    @FXML
    void addBookOnAction(ActionEvent event) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String author = txtAuthor.getText();
        String bookName = txtNameBook.getText();
        String godIzd = txtGodIzd.getText();
        int kolvo = -1;
        if (!(txtKolvo.getText().equals(""))) {
             kolvo = Integer.parseInt(txtKolvo.getText());
        }

        Book book = new Book(author,bookName,godIzd,kolvo);
        if (!(author.equals("")) && !(bookName.equals("")) && !(godIzd.equals("")) && (kolvo>0)) {
            dbHandler.addBook(book);
            btnAddBookInDB.getScene().getWindow().hide();
        }
        else
            System.out.println("Книга не записана, т.к. введены не все данные");

    }
}
