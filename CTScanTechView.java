import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// Technician view
public class CTScanTechView {
    private String patientId;
	private Scene previousScene;

    public CTScanTechView(String patientId, Scene previousScene) {
        this.patientId = patientId;
        this.previousScene = previousScene;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 600);

        //heading for patient ID
        Label headingLabel = new Label("Patient ID: " + patientId);
        headingLabel.setFont(Font.font("Arial", 24));

        //labels and text fields for input
        Label totalAgastonLabel = new Label("Total Agaston CAC Score:");
        TextField totalAgastonField = new TextField();

        Label lmLabel = new Label("LM:");
        TextField lmField = new TextField();

        Label ladLabel = new Label("LAD:");
        TextField ladField = new TextField();

        Label lcxLabel = new Label("LCX:");
        TextField lcxField = new TextField();

        Label rcsLabel = new Label("RCS:");
        TextField rcsField = new TextField();

        Label pdaLabel = new Label("PDA:");
        TextField pdaField = new TextField();

        //button to submit input
        Button submitButton = new Button("Submit");
        
        
        submitButton.setOnAction(e -> {
            //processing data recieved here
            String totalAgaston = totalAgastonField.getText();
            String lm = lmField.getText();
            String lad = ladField.getText();
            String lcx = lcxField.getText();
            String rcs = rcsField.getText();
            String pda = pdaField.getText();

//	            //displaying input data here
//	            System.out.println("Total Agaston CAC Score: " + totalAgaston);
//	            System.out.println("LM: " + lm);
//	            System.out.println("LAD: " + lad);
//	            System.out.println("LCX: " + lcx);
//	            System.out.println("RCS: " + rcs);
//	            System.out.println("PDA: " + pda);

            //updating patient file with CT scan data
            updatePatientFile(totalAgaston, lm, lad, lcx, rcs, pda);
            
            
        });

        // back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
        });
        
        HBox buttonBox = new HBox(10, submitButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        //layout setup
        VBox inputFields = new VBox(20, 
            new HBox(10, totalAgastonLabel, totalAgastonField),
            new HBox(10, lmLabel, lmField),
            new HBox(10, ladLabel, ladField),
            new HBox(10, lcxLabel, lcxField),
            new HBox(10, rcsLabel, rcsField),
            new HBox(10, pdaLabel, pdaField)
        );
        inputFields.setAlignment(Pos.CENTER_LEFT);
        inputFields.setPadding(new Insets(50));

        VBox centerLayout = new VBox(20, headingLabel, inputFields, buttonBox);
        centerLayout.setAlignment(Pos.CENTER);

        root.setCenter(centerLayout);

        return scene;
    }

 //method to update patient file with CT scan data
    private void updatePatientFile(String totalAgaston, String lm, String lad, String lcx, String rcs, String pda) {
        try {
            //my machine's directory path where you I'm storing the text files
            String directoryPath = "/Users/apple/cse240/360/HW4/patient_result/";
            File directory = new File(directoryPath);

            //create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //create the file name based on patient ID
            String fileName = patientId + "_PatientResult.txt";

            //create the full file path
            String filePath = directoryPath + fileName;
            File file = new File(filePath);

            //append CT scan data to the file
            FileWriter writer = new FileWriter(file, true);
            writer.write("\nCT Scan Data:\n");
            writer.write("Total Agaston CAC Score: " + totalAgaston + "\n");
            writer.write("LM: " + lm + "\n");
            writer.write("LAD: " + lad + "\n");
            writer.write("LCX: " + lcx + "\n");
            writer.write("RCS: " + rcs + "\n");
            writer.write("PDA: " + pda + "\n");

            //closing the writer
            writer.close();

            System.out.println("CT scan data added to patient file: " + fileName);
        } catch (IOException ex) {
            System.out.println("An error occurred while updating the patient file.");
            ex.printStackTrace();
        }
    }
}
