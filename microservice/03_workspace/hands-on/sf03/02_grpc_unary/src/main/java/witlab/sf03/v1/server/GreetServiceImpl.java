package witlab.sf03.v1.server;

import io.grpc.stub.StreamObserver;
import witlab.sf03.v1.GreetRequest;
import witlab.sf03.v1.GreetResponse;
import witlab.sf03.v1.GreetServiceGrpc;
import witlab.sf03.v1.Greeting;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request
            , StreamObserver<GreetResponse> responseObserver) {

        // extract the fields we need
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        // create the response
        String result = "Hello " + firstName;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();
        // send the response
        responseObserver.onNext(response);
        // complete the RPC call
        responseObserver.onCompleted();
    }
}
