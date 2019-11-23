package sample.Controller;


import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.animations.Shake;
import sample.myClass.DataBaseHandler;
import sample.myClass.OpenWindow;
import sample.myClass.Worker;

public class Controller {

    @FXML
    private PasswordField txtPass;

    @FXML
    public TextField txtName;

    @FXML
    private Button btnEnter;


    public void enterOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String pass = txtPass.getText().trim();
        if (!name.equals("") && !pass.equals(""))
            loginWorker(name,pass);
        else System.out.println("Ничего не введено");
    }

    private void loginWorker(String name, String pass) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Worker worker = new Worker(name,pass);
        ResultSet resultSet = dbHandler.getWorker(worker);

        int count = 0;
        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            count++;
        }
        if (count >= 1){
            btnEnter.getScene().getWindow().hide();     //спрятать окно
            OpenWindow openWindow = new OpenWindow();
            openWindow.openNewWindow("display2.fxml");
        } else {
            Shake nameAnim = new Shake(txtName);
            Shake passAnim = new Shake(txtPass);
            nameAnim.playAnim();
            passAnim.playAnim();
        }
    }
}
