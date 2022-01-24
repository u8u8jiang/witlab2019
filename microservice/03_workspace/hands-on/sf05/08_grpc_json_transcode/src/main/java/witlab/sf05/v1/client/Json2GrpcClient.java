package witlab.sf05.v1.client;

import com.google.protobuf.Duration;
import com.google.protobuf.FieldMask;
import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import witlab.sf05.v1.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Json2GrpcClient {

    public static void main(String[] args) throws Exception{
        String serviceUrl = "10.34.127.5";
        int servicePort = 5478;
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serviceUrl, servicePort)
                .usePlaintext() // disable TLS which is enabled by default and requires certificates
                .build();
        JsonToGrpcGrpc.JsonToGrpcBlockingStub client = JsonToGrpcGrpc.newBlockingStub(channel);
        // call gRPC: Unary #1 - GetMessage()
        callGetMessage(client);
        // call gRPC: Unary #2 - GetMessage2()
        callGetMessage2(client);
        // call gRPC: Unary #3 - GetMessage3()
        callGetMessage3(client);
        // call gRPC: Unary #4 - UpdateMessage()
        callUpdateMessage(client);
        // call gRPC: Unary #5 - Update2Message()
        callUpdate2Message(client);
        // call gRPC: Unary #6 - PostMessage()
        callPostMessage(client);
        // gracefully close the connection
        channel.shutdown();
        channel.awaitTermination(20, TimeUnit.SECONDS);
    }

    // call gRPC: Unary #1 - GetMessage()
    private static void callGetMessage(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        GetMessageRequest request = GetMessageRequest.newBuilder()
                .setName("messages/654321")
                .build();

        System.out.println("1. call getMessages()");
        System.out.println(client.getMessage(request));
        System.out.println("==================================================");
        System.out.println();
    }

    // call gRPC: Unary #2 - GetMessage2()
    private static void callGetMessage2(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        GetMessage2Request request2 = GetMessage2Request.newBuilder()
                .setMessageId("654321")
                .setRevision(2)
                .setSub(SubMessage.newBuilder()
                        .setSubfield("foo"))
                .build();

        System.out.println("2. call getMessages2()");
        System.out.println(client.getMessage2(request2));
        System.out.println("==================================================");
        System.out.println();
    }

    // call gRPC: Unary #3 - GetMessage3()
    private static void callGetMessage3(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        GetMessage3Request request3 = GetMessage3Request.newBuilder()
                .setMessageId("654321")
                .setUserId("User:007")
                .build();

        System.out.println("3. call GetMessage3()");
        System.out.println(client.getMessage3(request3));
        System.out.println("==================================================");
        System.out.println();
    }

    // call gRPC: Unary #4 - UpdateMessage()
    private static void callUpdateMessage(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        UpdateMessageRequest request4 = UpdateMessageRequest.newBuilder()
                .setMessageId("654321")
                .setMessage(Message.newBuilder()
                        .setText("It's not easy to make API right!")
                        .setUpdateMask(FieldMask.newBuilder()
                                .addPaths("filed1.fname")
                                .addPaths("field1.lname")
                                .build())
                        .build())
                .build();

        System.out.println("4. call UpdateMessage()");
        System.out.println(client.updateMessage(request4));System.out.println("==================================================");
        System.out.println();
    }

    // call gRPC: Unary #5 - Update2Message()
    private static void callUpdate2Message(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        UpdateMessage2Request request5 = UpdateMessage2Request.newBuilder()
                .setMessageId("654321")
                .setMessage(Message2.newBuilder()
                        .setFieldStr("This is string field")
                        .setFieldInt(5488)
                        .setFieldFloat(5.488f)
                        .setFieldBool(true)
                        .build())
                .build();

        System.out.println("5. call Update2Message()");
        System.out.println(client.update2Message(request5));
        System.out.println("==================================================");
        System.out.println();
    }

    // call gRPC: Unary #6 - PostMessage()
    private static void callPostMessage(JsonToGrpcGrpc.JsonToGrpcBlockingStub client) {
        Map<String,String> myMeta = new HashMap<>();
        myMeta.put("key1","111");
        myMeta.put("key2","222");

        PostMessageRequest request6 = PostMessageRequest.newBuilder()
                .setProgress(EnumProgress.RUNNING)
                .putAllMetadata(myMeta)
                .addMessages(Message.newBuilder().setText("myMsg1").build())
                .addMessages(Message.newBuilder().setText("myMsg2").build())
                .setStartDate(Timestamp.newBuilder().setSeconds(System.currentTimeMillis()/1000).build())
                .setDuration(Duration.newBuilder().setSeconds(54321).build())
                .setUpdateMask(FieldMask.newBuilder()
                        .addPaths("filed1.fname")
                        .addPaths("field1.lname")
                        .build())
                .build();

        System.out.println("6. call PostMessage()");
        System.out.println(client.postMessage(request6));
        System.out.println("==================================================");
        System.out.println();
    }
}
