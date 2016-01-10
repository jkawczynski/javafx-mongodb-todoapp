package todoapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import todoapp.scenes.login.LoginSceneController;

/**
 *
 * @author jkawczynski
 */
public class ToDoApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ToDoApp");

        initRootLayout(primaryStage);

        showLoginLayout();
    }

    private void initRootLayout(Stage primaryStage1) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ToDoApp.class.getResource("views/root-layout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage1.setScene(scene);
        primaryStage1.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void showLoginLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ToDoApp.class.getResource("views/login-layout.fxml"));

            AnchorPane loginLayout = (AnchorPane) loader.load();
            LoginSceneController controller = loader.getController();
            controller.setRootLayout(rootLayout);

            rootLayout.setCenter(loginLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
