package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.myClass.Client;
import sample.myClass.DataBaseHandler;

public class addClientController {

    @FXML
    private Button btnGoTo2display;

    @FXML
    private TextField txtAdress;

    @FXML
    private Button btnAddInDB;

    @FXML
    private TextField txtFio;

    @FXML
    private TextField txtTelephone;

    @FXML
    void initialize() {
        methodAddNewClient();

    }

    private void methodAddNewClient() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        btnAddInDB.setOnAction(event -> {
            String fio = txtFio.getText();
            String adress = txtAdress.getText();
            String telephone = txtTelephone.getText();

            Client client = new Client(fio,adress,telephone);
            if (!(fio.equals("")) && !(adress.equals("")) && !(telephone.equals(""))){
            dbHandler.addClient(client);
            btnAddInDB.getScene().getWindow().hide();
            }else {
                System.out.println("Клиент не создан, введены не все данные");
            }
        });
    }

    public void goTo2DisplayOnAction(ActionEvent actionEvent) {
        btnGoTo2display.getScene().getWindow().hide();
    }
}

