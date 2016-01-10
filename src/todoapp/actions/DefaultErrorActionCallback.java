package todoapp.actions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author jkawczynski
 */
public class DefaultErrorActionCallback implements ErrorActionCallback {

    private String title;
    private String message;

    public DefaultErrorActionCallback() {
    }

    public DefaultErrorActionCallback(String message) {
        this.message = message;
    }

    public DefaultErrorActionCallback(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public void execute(Exception exception) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Błąd");

        alert.setHeaderText(title == null ? "Bład aplikacji" : title);
        alert.setContentText(message == null ? exception.getMessage() : message);

        alert.showAndWait();
    }

}
