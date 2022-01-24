package witlab.sf03.v1.server;

import io.grpc.stub.StreamObserver;
import witlab.sf03.v1.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PhotoServiceImpl extends PhotoServiceGrpc.PhotoServiceImplBase {
    String imgFolderPath = "";
    public PhotoServiceImpl(String imgFolderPath) {
        this.imgFolderPath = imgFolderPath;
    }

    @Override
    public void uploadPhoto(UploadPhotoRequest request
            , StreamObserver<UploadPhotoResponse> responseObserver) {
        System.out.println("uploadPhoto() is called...");
        System.out.println("received " + request.getPhotosCount() + " photos！");
        // prepare a list of UploadStatus for ack back client
        List<UploadStatus> results = new ArrayList<>();
        for(Photo photo : request.getPhotosList()) {
            Map<String, String> meta = photo.getMetaMap();
            Photo.PhotoFormat photoFormat = photo.getFormat();
            byte[] content = photo.getContent().toByteArray();
            // in real case, we should save these photo to disk, but we just ingore here
            // ...
            try {
                // save the photo to file or persistent storage
                String imgName = UUID.randomUUID().toString() + "." + photo.getFormat().toString().toLowerCase();
                Path path = Paths.get(imgFolderPath+"/"+imgName);
                Files.write(path, content);

                 // construct response
                results.add(UploadStatus.newBuilder()
                        .putMeta("server_file_name", imgName)
                        .putAllMeta(photo.getMetaMap())
                        .setCode(UploadStatus.UploadStatusCode.OK).build());
            } catch (Exception e) {
                // fail to save the photo
                results.add(UploadStatus.newBuilder()
                        .putAllMeta(photo.getMetaMap())
                        .setCode(UploadStatus.UploadStatusCode.FAIL)
                        .setErrorMessage(e.getMessage())
                        .build());
            }
        }
        // send the upload status back to client
        responseObserver.onNext(UploadPhotoResponse.newBuilder()
                .addAllResults(results)
                .build());
        // singal the client the server complete sending message
        responseObserver.onCompleted();
    }
}



