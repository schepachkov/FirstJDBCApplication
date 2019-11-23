package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.myClass.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class giveBookController {

    @FXML
    private TextField txtTelephone;

    @FXML
    private  TextField txtGodIzd;

    @FXML
    private Label labelResult;

    @FXML
    private TextField txtNameBook;

    @FXML
    private TextField txtNameClient;

    @FXML
    private Button btnGive;

    @FXML
    private Button btnGoTo2Display;


    @FXML
    void giveBookOnAction(ActionEvent event) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        String bookName = txtNameBook.getText();
        String bookGodIzd = txtGodIzd.getText();
        String clientName = txtNameClient.getText();
        String clientTelephone = txtTelephone.getText();
        Book book = new Book(bookName,bookGodIzd);
        Client client = new Client(clientName,clientTelephone);

        ResultSet resSet = dataBaseHandler.ostatkiView2(book);
        ResultSet resSet2 = dataBaseHandler.clientView(client);
        int a = 0;
        int b = 0;
        while (true){
            try {
                if (!resSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            a++;
        }
        while (true){
            try {
                if (!resSet2.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            b++;
        }
        if (a > 0){
            if (b > 0) {
                dataBaseHandler.giveBook(book);
                dataBaseHandler.takeClient(client);
                labelResult.setText("Книга успешно выдана клиенту - " + client.getFIO());
                dataBaseHandler.addResult(book,client, Operation.Выдача);
                //=== добавление в файл
                BufferedWriter bufferedWriter = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(new File
                            ("C:\\УНИВЕР\\3КУРС\\2 Семестр\\РУЗАНОВ БД\\Курсовая\\Программа\\src\\sample\\txtFiles\\" + clientName+"_"+ clientTelephone +".doc")));
                    bufferedWriter.write("Читательский билет " + clientName + ".\n"+ "\n"+ "\n");
                    ResultSet resSet3 = dataBaseHandler.addTxt(client);
                    while (resSet3.next()){
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_OPERATION)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_FIO)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_TELEPHONE)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_BOOKNAME)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_GODIZD)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_DATEGIVE)+ " ");
                        bufferedWriter.write(resSet3.getString(ConstResult.RESULT_DATERETURN)+ "\n"+ "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                labelResult.setText("Такого клиента не существует");
            }

        }else {
            labelResult.setText("Данной книги нет в начличии");
        }

    }

    @FXML
    void goTo2DisplayOnAction(ActionEvent event) {
        btnGoTo2Display.getScene().getWindow().hide();
    }

}
