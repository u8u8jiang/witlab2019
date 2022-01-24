package witlab.sf03.v1.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

public class GreetServer {
    public static void main(String[] args) throws Exception {
        // step1. define which tcp port to listen
        int servicePort = 5488;
        System.out.println("gRPC [GreetService] run port: " + servicePort);
        // step2. build gRPC server instance
        Server server = ServerBuilder
                .forPort(servicePort)
                // enable gRPC service contract reflection service
                .addService(ProtoReflectionService.newInstance())
                // enable gRPC self-define service implementation
                .addService(new GreetServiceImpl())
                .build()
                .start();
        // step3. add shutdown hook if Ctrl+c event is captured
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received gRPC Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the gRPC server");
        }));
        // step4. keep gRPC service running until receive shutdown event
        server.awaitTermination();
    }
}

