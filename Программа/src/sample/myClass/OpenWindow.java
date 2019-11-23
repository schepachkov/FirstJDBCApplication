package sample.myClass;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenWindow {

    public void openNewWindow(String text){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/fileFXML/"+ text));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
