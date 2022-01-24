package witlab.sf05.v1.server;

import io.grpc.stub.StreamObserver;
import witlab.sf05.v1.*;
import java.util.UUID;

public class Json2GrpcServiceImpl extends JsonToGrpcGrpc.JsonToGrpcImplBase {

    // gRPC: Unary #1
    @Override
    public void getMessage(GetMessageRequest request
            , StreamObserver<GetMessageResponse> responseObserver) {

        System.out.println("getMessage() is called!");
        System.out.println(request);

        responseObserver.onNext(GetMessageResponse.newBuilder()
                .setText(request.getName())
                .build());

        responseObserver.onCompleted();
    }


    // gRPC: Unary #2
    @Override
    public void getMessage2(GetMessage2Request request
            , StreamObserver<GetMessage2Response> responseObserver) {

        System.out.println("getMessage2() is called!");
        System.out.println(request);
        // do some business logic ....
        // send back the result
        responseObserver.onNext(
                GetMessage2Response.newBuilder()
                        .setMessageId(request.getMessageId())
                        .setRevision(request.getRevision())
                        .setSub(SubMessage.newBuilder()
                                .setSubfield(request.getSub().getSubfield()))
                        .setAck(AckMessage.newBuilder()
                                // generate uuid as unique identifer
                                .setTaskId(UUID.randomUUID().toString())
                                .setStatus(AckStatus.OK))
                        .build()
        );

        responseObserver.onCompleted();
    }


    // gRPC: Unary #3
    @Override
    public void getMessage3(GetMessage3Request request
            , StreamObserver<GetMessage3Response> responseObserver) {

        System.out.println("getMessage3() is called!");
        System.out.println(request);
        // do some business logic ....
        // send back the result
        responseObserver.onNext(
                GetMessage3Response.newBuilder()
                        .setMessageId(request.getMessageId())
                        .setUserId(request.getUserId())
                        .setAck(AckMessage.newBuilder()
                                // generate uuid as unique identifer
                                .setTaskId(UUID.randomUUID().toString())
                                .setStatus(AckStatus.OK))
                        .build()
        );

        responseObserver.onCompleted();
    }


    // gRPC: Unary #4
    @Override
    public void updateMessage(UpdateMessageRequest request
            , StreamObserver<UpdateMessageResponse> responseObserver) {
        System.out.println("updateMessage() is called!");
        System.out.println(request);
        // do some business logic ....
        // send back the result
        responseObserver.onNext(
                UpdateMessageResponse.newBuilder()
                        .setMessageId(request.getMessageId())
                        .setMessage(request.getMessage())
                        .setAck(AckMessage.newBuilder()
                                // generate uuid as unique identifer
                                .setTaskId(UUID.randomUUID().toString())
                                .setStatus(AckStatus.OK))
                        .build()
        );

        responseObserver.onCompleted();
    }


    // gRPC: Unary #5
    @Override
    public void update2Message(UpdateMessage2Request request, StreamObserver<UpdateMessage2Response> responseObserver) {
        System.out.println("update2Message() is called!");
        System.out.println(request);
        // do some business logic ....

        // send back the result
        responseObserver.onNext(
                UpdateMessage2Response.newBuilder()
                        .setMessageId(request.getMessageId())
                        .setMessage(request.getMessage())
                        .setAck(AckMessage.newBuilder()
                                // generate uuid as unique identifer
                                .setTaskId(UUID.randomUUID().toString())
                                .setStatus(AckStatus.OK))
                        .build()
        );

        responseObserver.onCompleted();
    }

    // gRPC: Unary #6
    @Override
    public void postMessage(PostMessageRequest request, StreamObserver<PostMessageResponse> responseObserver) {
        System.out.println("postMessage() is called!");
        System.out.println(request);
        // do some business logic ....
        // send back the result
        responseObserver.onNext(
                PostMessageResponse.newBuilder()
                        .setProgress(request.getProgress())
                        .putAllMetadata(request.getMetadataMap())
                        .addAllMessages(request.getMessagesList())
                        .setStartDate(request.getStartDate())
                        .setDuration(request.getDuration())
                        .setUpdateMask(request.getUpdateMask())
                        .setAck(AckMessage.newBuilder().setStatus(AckStatus.OK).build())
                        .build()
        );

        responseObserver.onCompleted();
    }
}


