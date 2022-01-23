package witlab.sf02;

import wistron.witlab.sf02.v1.User;
import java.io.FileOutputStream;
import java.util.Arrays;

public class UserSerializeToFile {
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
        System.out.println(userBuilder);
        // 3. write out protobuf message to file / or to network
        User user = userBuilder.build();
        FileOutputStream fos = new FileOutputStream("user_message.bin");
        user.writeTo(fos); // dump message data to file
        fos.close();
        // 4. write out protobuf message to byte[]
        byte[] bytes = user.toByteArray();
    }
}
