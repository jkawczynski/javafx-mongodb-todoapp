package todoapp.scenes.login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import todoapp.actions.AsyncAction;
import todoapp.actions.DefaultErrorActionCallback;
import todoapp.actions.EventAction;
import todoapp.dto.User;
import todoapp.scenes.DefaultController;
import todoapp.services.login.LoginService;
import static todoapp.utils.FXMLLoadUtils.loadView;

/**
 * FXML Controller class
 *
 * @author jkawczynski
 */
public class LoginSceneController extends DefaultController {

    @FXML
    private TextField tfUserName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Hyperlink hlSignUp;

    private LoginService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        this.service = new LoginService();

        initLoginAction();
        initGoToSignUpAction();
    }

    private void initLoginAction() {
        AsyncAction loginAction = getLoginAction();
        btnSignIn.setOnAction(loginAction);
    }

    private void initGoToSignUpAction() {
        hlSignUp.setOnAction((ActionEvent event) -> {
            loadView("register-layout", rootLayout);
        });
    }

    private AsyncAction getLoginAction() {
        AsyncAction loginAction = new AsyncAction.Builder<User>((EventAction) () -> {
            return service.signIn(tfUserName.getText(), pfPassword.getText());
        }).success((data) -> {
            sessionFactory.getCurrentSession().putValue("user", data);
            loadView("todo-layout", rootLayout);
        }).error(new DefaultErrorActionCallback("Niepoprawne dane", "Użytkownik o takim loginie i haśle nie istnieje"))
                .build();
        return loginAction;
    }

}
