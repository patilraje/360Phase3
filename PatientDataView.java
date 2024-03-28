import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//Patient Side View after log in
public class PatientDataView {
    private Stage primaryStage;
    private String patientId;

    public PatientDataView(Stage primaryStage, String patientId) {
        this.primaryStage = primaryStage;
        this.patientId = patientId;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();

        //read patient info file to get patient's name
        String patientInfoFileName = patientId + "_PatientInfo.txt";
        File infoFile = new File("/Users/apple/cse240/360/HW4/patient_info/", patientInfoFileName);
        String patientName = "Patient";

        if (infoFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(infoFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                	//checking if line starts with the string, it is followed by patient name
                    if (line.startsWith("First Name:")) {
                        patientName = line.substring(line.indexOf(":") + 2);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //greet patient using name
        Label greetingLabel = new Label("Hello " + patientName + "!");
        greetingLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //display patient ID
        //Label patientIdLabel = new Label("Patient ID: " + patientId);

        //read patient result file
        String resultFileName = patientId + "_PatientResult.txt";
        File resultFile = new File("/Users/apple/cse240/360/HW4/patient_result/", resultFileName);

        VBox dataView = new VBox(20, greetingLabel);
        dataView.setStyle("-fx-padding: 20px;");

        if (resultFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(resultFile))) {
                String line;
                //read until file is empty / end of file is reached
                while ((line = reader.readLine()) != null) {
                    Label resultLabel = new Label(line);
                    dataView.getChildren().add(resultLabel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Label noResultLabel = new Label("No result found for this patient.");
            dataView.getChildren().add(noResultLabel);
        }

        root.setCenter(dataView);

        return new Scene(root, 900, 600);
    }
}
