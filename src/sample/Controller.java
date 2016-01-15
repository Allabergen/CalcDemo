package sample;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public Text output;
    public Text output2;
    private String operator = "";
    private float number1 = 0;
    private boolean start = true;
    private boolean start2 = true;

    private Model model = new Model();
    private Scene scene;
    private static Stage primaryStageC;
    private static Scene mainSceneC;
    private static Parent moreFC;

    public static int switchScene = 2;
    public static int sceneSwitch = 0;

    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }

        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!"=".equals(value)) {
            if (!operator.isEmpty())
                return;
            operator = value;
            number1 = Float.parseFloat(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty())
                return;
            output.setText(String.valueOf(model.calculate(number1, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }

    @FXML
    private void processClear(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (value.equals("C"))
            output.setText("");

        if (value.equals("←")) {
            output.setText(output.getText().substring(0, output.getText().length() - 1));
        }
    }

    @FXML
    private void processFunction(ActionEvent event) {
//        if (start2) {
//            output.setText("");
//            start2 = false;
//        }
//
//        String value = ((Button) event.getSource()).getText();
//        output2.textProperty().bind(output.textProperty());
    }

    @FXML
    private void dialog(ActionEvent event) {
        AlertBox.display("About", "My Calculator\nVersion 1.0\nContacts: \tallromis@gmail.com\n\t\tallabergen.su@gmail.com\n\nHappy Calculating!");
    }

    /*@FXML
    private void switchFunction() throws IOException {
        if (switchScene % 2 == 0) {
            ++switchScene;
            if (sceneSwitch == 0) {
                scene = new Scene(moreFC, 225, 243);
                ++sceneSwitch;
            }
            primaryStageC.setScene(scene);
            primaryStageC.show();
        } else if (switchScene % 2 == 1) {
            ++switchScene;
            primaryStageC.setScene(mainSceneC);
            primaryStageC.show();
        }
    }*/

    public static void setStage(Stage primaryStage, Scene mainScene, Parent moreF) {
        primaryStageC = primaryStage;
        mainSceneC = mainScene;
        moreFC = moreF;
    }
}
