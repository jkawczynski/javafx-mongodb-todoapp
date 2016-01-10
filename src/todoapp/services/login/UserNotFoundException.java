
package todoapp.services.login;

/**
 *
 * @author jkawczynski
 */
class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found");
    }
    
}
