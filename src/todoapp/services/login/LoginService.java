package todoapp.services.login;

import java.util.Optional;
import javax.naming.OperationNotSupportedException;
import org.bson.Document;
import todoapp.dto.User;
import static todoapp.security.HashUtils.md5;
import todoapp.stores.UserStore;

/**
 *
 * @author jkawczynski
 */
public class LoginService {

    private final UserStore store;

    public LoginService() {
        this.store = new UserStore();
    }

    public User signIn(String userName, String password) throws UserNotFoundException, OperationNotSupportedException {
        Optional<String> hashedPwd = md5(password);
        if (hashedPwd.isPresent()) {
            return getUser(userName, hashedPwd.get());
        }
        throw new OperationNotSupportedException();
    }

    public Boolean isUserNameAvaliable(String userName) {
        return store.isUserNameAvaliable(userName);
    }

    private User getUser(String userName, String hashedPwd) throws UserNotFoundException {
        Document userDoc = store.getUser(userName, hashedPwd);
        if (userDoc == null) {
            throw new UserNotFoundException();
        }
        User user = new User(userDoc.get("userName", String.class),
                userDoc.get("password", String.class));

        return user;
    }

    public User createUser(String userName, String password) throws OperationNotSupportedException {
        Optional<String> hashedPassword = md5(password);
        if (hashedPassword.isPresent()) {
            User user = new User(userName, hashedPassword.get());
            store.createUser(user);
            return user;
        }
        throw new OperationNotSupportedException();
    }

}
