package todoapp.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author jkawczynski
 */
public class MongoDb {

    private static MongoDb INSTANCE;
    private final MongoClient client;
    private final MongoDatabase database;

    private MongoDb() {
        client = new MongoClient();
        database = client.getDatabase("todoapp");
    }

    public synchronized static MongoDb getInstance() {
        if(INSTANCE == null){
            INSTANCE = new MongoDb();
        }
        return INSTANCE;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

}
