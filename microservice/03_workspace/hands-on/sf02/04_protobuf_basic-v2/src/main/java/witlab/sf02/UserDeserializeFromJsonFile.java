package witlab.sf02;

import com.google.protobuf.util.JsonFormat;
import wistron.witlab.sf02.v1.User;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserDeserializeFromJsonFile {
    public static void main(String[] args) throws Exception {
        System.out.println("Deserialize message from file..");
        // 1. read json string from file
        String user_json =
                new String(Files.readAllBytes(Paths.get("user_message.json")));

        // 2. use .parseFrom() method to read binary protobuf data
        User.Builder userBuilder = User.newBuilder();
        JsonFormat.parser()
                .ignoringUnknownFields()
                .merge(user_json, userBuilder);

        // 3. print out to console
        System.out.println(userBuilder);
    }
}
