package todoapp.stores;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Projections.include;
import javax.naming.OperationNotSupportedException;
import org.bson.Document;
import todoapp.db.MongoDb;
import todoapp.dto.User;

/**
 *
 * @author jkawczynski
 */
public class UserStore {

    private final MongoCollection<Document> collection;

    public UserStore() {
        this.collection = MongoDb.getInstance().getDatabase().getCollection("users");
    }

    public Document getUser(String userName, String hashedPwd) {
        Document filter = new Document("userName", userName)
                .append("password", hashedPwd);
        Document userDocument = collection.find(filter).first();
        return userDocument;
    }

    public void createUser(User user) throws OperationNotSupportedException {
        collection.insertOne(new Document("userName", user.getUserName())
                .append("password", user.getPassword()));
    }

    public Boolean isUserNameAvaliable(String userName) {
        Document filter = new Document("userName", userName);
        Document user = collection.find(filter).projection(include("userName")).first();
        return user == null;
    }

}
