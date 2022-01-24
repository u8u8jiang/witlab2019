package witlab.sf03.v1.client;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import witlab.sf03.v1.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class PhotoClient {
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
        // step3. created a photo service client (blocking - synchronous)
        PhotoServiceGrpc.PhotoServiceBlockingStub client = PhotoServiceGrpc.newBlockingStub(channel);
        // step4. base on gRPC service to create photo upload message
        int photos_to_upload = 5;   // the max message size limit is around 4MB
                                    // if the message size reach the max limit, gRPC would throws exception (151)
        long total_bytes = 0;
        // let's use micro-batch to upload multiple small photos in one request
        UploadPhotoRequest.Builder uploadPhotoRequestBuilder = UploadPhotoRequest.newBuilder();
        for(int i=0; i< photos_to_upload; i++) {
            Photo photo = getCatPhoto(i);
            total_bytes += photo.getContent().size();
            uploadPhotoRequestBuilder.addPhotos(photo);
        }
        // step5.build up GreetRequest object
        UploadPhotoRequest request =  uploadPhotoRequestBuilder.build();
        // step6. call the gRPC and get back a UploadPhotoResponse (protocol buffers)
        try {
            UploadPhotoResponse uploadPhotoResponse = client.uploadPhoto(request);

            System.out.println(uploadPhotoResponse); // print result for debug
            System.out.println("Total photos to upload: " + photos_to_upload);
            System.out.println("Total bytes to upload: " + getSize(total_bytes));

        }catch (Exception e) {
            e.printStackTrace();
        }
        // step7. close the communication channel if we are not going to use any more
        System.out.println("Shutting down channel");
        channel.shutdownNow(); // tell channel to close connection
        channel.awaitTermination(10, TimeUnit.SECONDS); // gracefully wait for termination
    }

    // method to create Photo object
    private static Photo getCatPhoto(int imgId) throws IOException {
        String photo_folder = "images_to_upload/cat";
        // The path to the image file to annotate
        String fileName = imgId + ".jpg";
        String fullFilePath = photo_folder+"/"+fileName;
        // Reads the image file into memory
        Path path = Paths.get(fullFilePath);
        byte[] content = Files.readAllBytes(path);
        ByteString imgBytes = ByteString.copyFrom(content);
        Photo photo = Photo.newBuilder()
                .putMeta("origin_file_name", fileName)
                .putMeta("byte_size",content.length+"")
                .setFormat(Photo.PhotoFormat.JPG)
                .setContent(imgBytes)
                .build();
        return photo;
    }

    // utility method to convert byte size to human readable format
    public static String getSize(long size) {
        String s = "";
        double kb = size / 1024;
        double mb = kb / 1024;
        double gb = kb / 1024;
        double tb = kb / 1024;
        if(size < 1024) {
            s = size + " Bytes";
        } else if(size >= 1024 && size < (1024 * 1024)) {
            s =  String.format("%.2f", kb) + " KB";
        } else if(size >= (1024 * 1024) && size < (1024 * 1024 * 1024)) {
            s = String.format("%.2f", mb) + " MB";
        } else if(size >= (1024 * 1024 * 1024) && size < (1024 * 1024 * 1024 * 1024)) {
            s = String.format("%.2f", gb) + " GB";
        } else if(size >= (1024 * 1024 * 1024 * 1024)) {
            s = String.format("%.2f", tb) + " TB";
        }
        return s;
    }
}


