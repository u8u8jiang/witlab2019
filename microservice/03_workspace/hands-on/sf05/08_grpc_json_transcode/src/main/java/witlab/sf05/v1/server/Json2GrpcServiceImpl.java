package witlab.sf05.v1.server;

import io.grpc.stub.StreamObserver;
import witlab.sf05.v1.*;

import java.util.UUID;

public class Json2GrpcServiceImpl extends JsonToGrpcGrpc.JsonToGrpcImplBase {

    // 1. This enables an HTTP REST to gRPC mapping as below:
    //
    // HTTP | gRPC
    // -----|-----
    // `GET /v1/messages/123456`  | `GetMessage(name: "messages/123456")`
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

    // 2. This enables a HTTP JSON to RPC mapping as below:
    //
    // Any fields in the request message which are not bound by the path template
    // automatically become HTTP query parameters if there is no HTTP request body.
    //
    // HTTP | gRPC
    // -----|-----
    // `GET /v1/messages2/123456?revision=2&sub.subfield=foo` | `GetMessage2(message_id: "123456" revision: 2 sub: SubMessage(subfield: "foo"))`
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

    // 3. This enables the following two alternative HTTP JSON to RPC mappings:
    //
    // HTTP | gRPC
    // -----|-----
    // `GET /v1/messages3/123456?user_id=me` | `GetMessage3(user_id: "me" message_id:"123456")`
    // `GET /v1/users/me/messages/123456`   | `GetMessage3(user_id: "me" message_id:"123456")`
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

    // 4. The following HTTP JSON to RPC mapping is enabled, where the
    // representation of the JSON in the request body is determined by
    // protos JSON encoding:
    //
    // HTTP | gRPC
    // -----|-----
    // `PATCH /v1/messages/123456 { "text": "Hi!" }` | `UpdateMessage(message_id:
    // "123456" message { text: "Hi!" })`
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

    // 5. The special name `*` can be used in the body mapping to define that
    // every field not bound by the path template should be mapped to the
    // request body.
    //
    // The following HTTP JSON to RPC mapping is enabled:
    //
    // HTTP | gRPC
    // -----|-----
    // `PATCH /v1/messages2/123456 { "text": "Hi!" }` | `Update2Message(message_id: "123456" text: "Hi!")`
    //
    // Note that when using `*` in the body mapping, it is not possible to
    // have HTTP parameters, as all fields not bound by the path end in
    // the body. This makes this option more rarely used in practice when
    // defining REST APIs. The common usage of `*` is in custom methods
    // which don't use the URL at all for transferring data.
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


