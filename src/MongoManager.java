import com.mongodb.*;

import java.util.logging.Level;
import java.util.logging.Logger;


public class MongoManager {

    DBCollection collection;

    MongoManager() {
        MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("test");
        collection = database.getCollection("Gewinner");
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
    }

    public void addNewWinner(String pName, int pNumberOfTurns) {

        DBObject player = new BasicDBObject("Name", pName)
                .append("NumberOfTurns", pNumberOfTurns);
        collection.insert(player);
    }

    public String getPastWinners() {
        StringBuilder sb = new StringBuilder();
        sb.append("LEADERBOARD:\n");
        DBObject query = new BasicDBObject();
        DBCursor cursor = collection.find(query);
        if (!(collection.count() == 0)) {
            sb.append("   Name  Turns" + "\n");

            for (int i = 1; i <= collection.count(); i++) {

                DBObject test = cursor.next();
                sb.append(i + ": " + test.get("Name") + "   " + test.get("NumberOfTurns") + "\n");

            }
        } else {
            sb.append("No Winners Yet!");
        }
        return sb.toString();
    }


}
