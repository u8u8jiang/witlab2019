package witlab.sf05.v1.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Json2GrpcApp implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(Json2GrpcApp.class);
    @Value("${grpc.server.port}")
    private int port;

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(Json2GrpcApp.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        final Json2GrpcServer server = new Json2GrpcServer(port);
        // start gRPC service
        server.start();
        // wait until shutdown signal
        server.blockUntilShutdown();
    }
}
