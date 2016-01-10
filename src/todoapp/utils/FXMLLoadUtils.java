package todoapp.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import todoapp.ToDoApp;
import todoapp.scenes.DefaultController;
import todoapp.scenes.login.LoginSceneController;

/**
 *
 * @author jkawczynski
 */
public class FXMLLoadUtils {

    public static Node loadView(String viewName, BorderPane rootLayout) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ToDoApp.class.getResource("views/" + viewName + ".fxml"));
            AnchorPane layout = loader.load();
            DefaultController controller = loader.getController();
            controller.setRootLayout(rootLayout);
            rootLayout.setCenter(layout);
            return layout;
        } catch (IOException ex) {
            Logger.getLogger(LoginSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
