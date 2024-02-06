package frontend.wheel;

import frontend.backend.Wheel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Common {
    public static final String HomeButtonImagePath = "/frontend/Style/Home.png";

    public static final Wheel wheel = new Wheel();

}
