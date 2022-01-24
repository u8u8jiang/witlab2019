package witlab.sf05.v1.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Json2GrpcServer {
    private static Logger LOG = LoggerFactory.getLogger(Json2GrpcServer.class);
    private Server server;
    private int port;

    public Json2GrpcServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                // enable gRPC service contract reflection service
                .addService(ProtoReflectionService.newInstance())
                // enable gRPC self-define service implementation
                .addService(new Json2GrpcServiceImpl())
                .build()
                .start();

        LOG.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            LOG.info("shutting down gRPC server since JVM is shutting down");
            Json2GrpcServer.this.stop();
            LOG.info("server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
