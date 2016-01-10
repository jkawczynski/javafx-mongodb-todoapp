package todoapp.scenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import todoapp.factories.SessionFactory;

/**
 *
 * @author jkawczynski
 */
public abstract class DefaultController implements Initializable {

    protected BorderPane rootLayout;

    protected SessionFactory sessionFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sessionFactory = new SessionFactory();

    }

    public void setRootLayout(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
    }

}
