import javafx.application.HostServices;
import java.awt.Desktop;
import java.net.URI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HealthWellnessResources {
    private String lastName;
    private String firstName;
    private HostServices hostServices;

    public HealthWellnessResources(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Implement the scene content for Health & Wellness Resources
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.setSpacing(20);

        // Hyperlinks to various health and wellness resources
        Hyperlink counselingLink = new Hyperlink("Counseling Services");
        counselingLink.setOnAction(e -> openURL("https://eoss.asu.edu/counseling"));

        Hyperlink healthServicesLink = new Hyperlink("Health Services");
        healthServicesLink.setOnAction(e -> openURL("https://eoss.asu.edu/health"));

        Hyperlink fitnessWellnessLink = new Hyperlink("Sun Devil Fitness and Wellness");
        fitnessWellnessLink.setOnAction(e -> openURL("https://fitness.asu.edu/"));

        // Add hyperlinks to the content VBox
        content.getChildren().addAll(counselingLink, healthServicesLink, fitnessWellnessLink);

        // Set content VBox in the center of the BorderPane
        root.setCenter(content);

        // Set padding for the root BorderPane
        root.setPadding(new Insets(20));

        return new Scene(root, 400, 300);
    }

    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getContent(Stage primaryStage) {
        return getScene(primaryStage).getRoot();
    }
}
