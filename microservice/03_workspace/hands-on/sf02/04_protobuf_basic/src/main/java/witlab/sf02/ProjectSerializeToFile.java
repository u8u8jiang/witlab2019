package witlab.sf02;

import witlab.sf02.v1.ProjectProto;
import witlab.sf02.v1.UserProto;

import java.io.FileOutputStream;
import java.util.Arrays;

public class ProjectSerializeToFile {
    public static void main(String[] args) throws Exception {
        // 1. create a message builder object
        ProjectProto.Project.Builder projectBuilder = ProjectProto.Project.newBuilder();

        // 2. set message object properties
        UserProto.User pm = UserProto.User.newBuilder()
                .setId(1)
                .setName("pm_001")
                .build();

        projectBuilder.setProjectId("richme")
                .setProjectManager(pm)
                .setStatus(ProjectProto.ProjectStatus.START);

        System.out.println(projectBuilder);

        // 3. write out protobuf message to file / or to network
        ProjectProto.Project project = projectBuilder.build();
        FileOutputStream fos = new FileOutputStream("project_message.bin");
        project.writeTo(fos); // dump message data to file
        fos.close();
    }
}
