import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AppointmentSummary {
    private String patientId;

    public AppointmentSummary(String patientId, String lastName) {
        this.patientId = patientId;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // TextArea to display appointment summary
        TextArea summaryTextArea = new TextArea();
        summaryTextArea.setPrefRowCount(10);
        summaryTextArea.setEditable(false);

        // Read and populate appointment summary
        readAndPopulateSummary(summaryTextArea);

        // Button to save changes
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            // Save changes to the file
            saveSummary(summaryTextArea.getText());
        });

        // Layout setup
        VBox vbox = new VBox(20, summaryTextArea, saveButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));

        root.setCenter(vbox);

        return new Scene(root, 400, 300);
    }

    private void readAndPopulateSummary(TextArea summaryTextArea) {
        File summaryFile = new File("/Users/apple/cse240/360/HW4/patient_info/", patientId + "_AppointmentSummary.txt");
        if (summaryFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(summaryFile))) {
                String line;
                StringBuilder summaryBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    summaryBuilder.append(line).append("\n");
                }
                summaryTextArea.setText(summaryBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSummary(String summary) {
        File summaryFile = new File("/Users/apple/cse240/360/HW4/patient_info/", patientId + "_AppointmentSummary.txt");
        try (FileWriter writer = new FileWriter(summaryFile)) {
            writer.write(summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getContent(Stage primaryStage) {
        return getScene(primaryStage).getRoot();
    }
}
