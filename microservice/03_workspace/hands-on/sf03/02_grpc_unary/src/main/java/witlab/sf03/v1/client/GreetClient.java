package witlab.sf03.v1.client;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import witlab.sf03.v1.GreetRequest;
import witlab.sf03.v1.GreetResponse;
import witlab.sf03.v1.GreetServiceGrpc;
import witlab.sf03.v1.Greeting;

public class GreetClient {
    public static void main(String[] args) throws Exception {
        // step1. define gRPC service host & port
        String serviceHost = "localhost";
        int servicePort = 5488;
        System.out.println("Connecting to gRPC Service ["+serviceHost+":"+servicePort+"]...");
        // step2. build a communication channel to connect gRPC service
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(serviceHost, servicePort)
                .usePlaintext()
                .build();
        // step3. created a greet service client (blocking - synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        // step4. base on gRPC service to create greeting message
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();
        // step5.build up GreetRequest object
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();
        // step6. call the gRPC and get back a GreetResponse (protocol buffers)
        GreetResponse greetResponse = greetClient.greet(greetRequest);
        System.out.println(greetResponse.getResult());
        // step7. close the communication channel if we are not going to use any more
        System.out.println("Shutting down channel");
        channel.shutdown();
    }
}