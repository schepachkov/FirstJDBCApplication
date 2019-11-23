package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node) {
        tt = new TranslateTransition(Duration.millis(100),node);
        tt.setFromX(0f);        //первлначальное значение
        tt.setByX(10f);         //смещение на 10f вправо
        tt.setCycleCount(3);    //количество раз
        tt.setAutoReverse(true);        //возврат к первоначальному значению
    }
    public void playAnim(){
        tt.playFromStart();
    }
}
