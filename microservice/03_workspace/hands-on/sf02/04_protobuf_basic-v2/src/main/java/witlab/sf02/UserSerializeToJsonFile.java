package witlab.sf02;

import com.google.common.base.Charsets;
import com.google.protobuf.util.JsonFormat;
import wistron.witlab.sf02.v1.User;
import java.io.FileOutputStream;
import java.util.Arrays;

public class UserSerializeToJsonFile {
    public static void main(String[] args) throws Exception {
        // 1. create a message builder object
        User.Builder userBuilder = User.newBuilder();
        // 2. set message object properties
        userBuilder.setId(1)
                .setIsActive(true)
                .setName("user001")
                // below demo how to set value for repeated field
                .addFollowers(2)
                .addFollowers(4)
                .addFollowers(7)
                .addAllFollowers(Arrays.asList(10,11,12,13));
        User user = userBuilder.build();
        // 3. convert protobuffer message to JSON
        String user_json = JsonFormat.printer().print(user);
        // 4. write out protobuf message to json file
        FileOutputStream fos = new FileOutputStream("user_message.json");
        // dump message data to file
        fos.write(user_json.getBytes(Charsets.UTF_8));
        fos.close();
    }
}
