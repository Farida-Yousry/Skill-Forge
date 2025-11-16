package pack.pkg7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserJsonDB {
     private static final String FILE = "users.json";
   
     public JSONArray loadUsers() throws IOException {
        File file = new File(FILE);
        if (!file.exists()) return new JSONArray();

        String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
        return new JSONArray(content);
    }

    public void saveUsers(JSONArray users) throws IOException {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(users.toString(4));
        }
    }
}
