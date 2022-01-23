package witlab.sf02;

import wistron.witlab.sf02.v1.User;
import java.io.FileInputStream;

public class UserDeserializeFromFile {
    public static void main(String[] args) throws Exception {
        System.out.println("Deserialize message from file..");
        // 1. open file stream
        FileInputStream fis = new FileInputStream("user_message.bin");
        // 2. use .parseFrom() method to read binary protobuf data
        User user =  User.parseFrom(fis);
        // 3. print out to console
        System.out.println(user);
    }
}
