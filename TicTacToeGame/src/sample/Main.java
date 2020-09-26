package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tic Tac Toe");
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        Label label = (Label) scene.lookup("#whoseTurn");

        Controller controller = new Controller();
        label.setText("Turn: "+controller.getTurn());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
