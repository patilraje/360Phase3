import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PatientDataView {
    private String firstName;
    private String lastName;

    public PatientDataView(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Scene getScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create the TabPane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Create and add tabs to the TabPane
        Tab personalInfoTab = new Tab("Personal Information");
        PersonalInformation personalInformation = new PersonalInformation(firstName, lastName);
        personalInfoTab.setContent(personalInformation.getContent(primaryStage));

        Tab appointmentSummaryTab = new Tab("Appointment Summary");
        AppointmentSummary appointmentSummary = new AppointmentSummary(firstName, lastName);
        appointmentSummaryTab.setContent(appointmentSummary.getContent(primaryStage));

        Tab insuranceInfoTab = new Tab("Insurance Information");
        InsuranceInformation insuranceInformation = new InsuranceInformation(firstName, lastName);
        insuranceInfoTab.setContent(insuranceInformation.getContent(primaryStage));

        Tab pharmacyInfoTab = new Tab("Pharmacy Information");
        PharmacyInformation pharmacyInformation = new PharmacyInformation(firstName, lastName);
        pharmacyInfoTab.setContent(pharmacyInformation.getContent(primaryStage));

        
        Tab healthResourcesTab = new Tab("Health and Wellness Resources");
        HealthWellnessResources healthWellnessResources = new HealthWellnessResources(firstName, lastName);
        healthResourcesTab.setContent(healthWellnessResources.getContent(primaryStage));

        // Add tabs to the tab pane
        tabPane.getTabs().addAll(personalInfoTab, appointmentSummaryTab, insuranceInfoTab, pharmacyInfoTab, healthResourcesTab);

        // Set the TabPane to the top of the BorderPane
        root.setTop(tabPane);

        // Create and return the scene
        Scene scene = new Scene(root, 900, 600);
        return scene;
    }
}
