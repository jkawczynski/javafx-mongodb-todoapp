package todoapp.scenes.register;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import todoapp.actions.AsyncAction;
import todoapp.actions.EventAction;
import todoapp.dto.User;
import todoapp.scenes.DefaultController;
import todoapp.services.login.LoginService;
import static todoapp.utils.FXMLLoadUtils.loadView;

/**
 *
 * @author jkawczynski
 */
public class RegisterSceneController extends DefaultController {

    @FXML
    private TextField tfUserName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfPasswordRetype;

    @FXML
    private Button btnSignUp;

    @FXML
    private Hyperlink hlGoToSignIn;
    private LoginService loginService;

    private RegisterFormValid formValid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.loginService = new LoginService();
        this.formValid = new RegisterFormValid();
        setBtnDisableState();
        initUserNameChangeListener();
        initPasswordFieldListeners();

        AsyncAction asyncSignUp = new AsyncAction.Builder<User>((EventAction) () -> {
            return loginService.createUser(tfUserName.getText(), pfPassword.getText());
        }).success((User data) -> {
            sessionFactory.getCurrentSession().putValue("user", data);
            loadView("todo-layout", rootLayout);
        }).build();

        btnSignUp.setOnAction(asyncSignUp);

        hlGoToSignIn.setOnAction((ActionEvent event) -> {
            loadView("login-layout", rootLayout);
        });
    }

    private void initPasswordFieldListeners() {
        pfPassword.textProperty().addListener((ObservableValue<? extends String> observable,
                String oldValue, String newValue) -> {
            setValidPasswords(pfPasswordRetype.getText().equals(newValue));
        });
        pfPasswordRetype.textProperty().addListener((ObservableValue<? extends String> observable,
                String oldValue, String newValue) -> {
            setValidPasswords(pfPassword.getText().equals(newValue));
        });
    }

    private void initUserNameChangeListener() {
        tfUserName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 3) {
                AsyncAction checkUserNameAction = new AsyncAction.Builder<Boolean>((EventAction) () -> {
                    return loginService.isUserNameAvaliable(newValue);
                }).success((Boolean isAvaliabale) -> {
                    onTfUserNameValid(isAvaliabale);
                }).build();
                checkUserNameAction.execute();
                return;
            }
            formValid.setUserNameValid(false);
            setBtnDisableState();
        });
    }

    private void setValidPasswords(boolean equals) {
        setValidClass(pfPassword, equals);
        setValidClass(pfPasswordRetype, equals);
        formValid.setPasswordValid(equals);
        setBtnDisableState();
    }

    private void onTfUserNameValid(Boolean avaliabale) {
        setValidClass(tfUserName, avaliabale);
        formValid.setUserNameValid(avaliabale);
        setBtnDisableState();
    }

    private void setValidClass(TextField tf, boolean isValid) {
        if (isValid) {
            tf.getStyleClass().removeAll("error");
        } else {
            tf.getStyleClass().add("error");
        }
    }

    private void setBtnDisableState() {
        btnSignUp.setDisable(!formValid.isFormValid());
    }

}
