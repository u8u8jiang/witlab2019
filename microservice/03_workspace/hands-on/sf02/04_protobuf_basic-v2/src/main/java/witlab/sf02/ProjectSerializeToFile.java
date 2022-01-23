package witlab.sf02;

import wistron.witlab.sf02.v1.Project;
import wistron.witlab.sf02.v1.ProjectStatus;
import wistron.witlab.sf02.v1.User;
import java.io.FileOutputStream;

public class ProjectSerializeToFile {
    public static void main(String[] args) throws Exception {
        // 1. create a message builder object
        Project.Builder projectBuilder = Project.newBuilder();

        // 2. set message object properties
        User pm = User.newBuilder()
                .setId(1)
                .setName("pm_001")
                .build();

        projectBuilder.setProjectId("richme")
                .setProjectManager(pm)
                .setStatus(ProjectStatus.START);

        System.out.println(projectBuilder);

        // 3. write out protobuf message to file / or to network
        Project project = projectBuilder.build();
        FileOutputStream fos = new FileOutputStream("project_message.bin");
        project.writeTo(fos); // dump message data to file
        fos.close();
    }
}
