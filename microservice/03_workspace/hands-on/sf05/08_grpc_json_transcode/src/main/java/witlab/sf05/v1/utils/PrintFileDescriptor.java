package witlab.sf05.v1.utils;

import com.google.protobuf.DescriptorProtos;

import java.io.FileInputStream;

public class PrintFileDescriptor {
    public static void main(String[] args) throws Exception {
        String descriptor_path = "target/generated-resources/protobuf/descriptor-sets/";
        String descriptor_filename = "json-to-grpc.pb";

        // read in proto file descriptor binary file (FileDescriptorSet)
        FileInputStream fin = new FileInputStream(descriptor_path + descriptor_filename);

        // DescriptorSet contains the contents of .proto file
        DescriptorProtos.FileDescriptorSet fileDescriptorSet = DescriptorProtos
                .FileDescriptorSet.parseFrom(fin);

        for(DescriptorProtos.FileDescriptorProto fileDescriptorProto : fileDescriptorSet.getFileList()) {
            // iterate each .proto structure
            System.out.println(fileDescriptorProto);
        }
    }
}
