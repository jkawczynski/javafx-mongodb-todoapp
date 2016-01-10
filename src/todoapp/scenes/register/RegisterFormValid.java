package todoapp.scenes.register;

/**
 *
 * @author jkawczynski
 */
public class RegisterFormValid {

    private Boolean userNameValid = false;
    private Boolean passwordValid = false;

    public Boolean getUserNameValid() {
        return userNameValid;
    }

    public void setUserNameValid(Boolean userNameValid) {
        this.userNameValid = userNameValid;
    }

    public Boolean getPasswordValid() {
        return passwordValid;
    }

    public void setPasswordValid(Boolean passwordValid) {
        this.passwordValid = passwordValid;
    }

    public Boolean isFormValid() {
        return userNameValid && passwordValid;
    }

}
