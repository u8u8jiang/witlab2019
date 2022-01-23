package witlab.sf02;

import witlab.sf02.v1.DeptProto;
import witlab.sf02.v1.ProjectProto;
import witlab.sf02.v1.UserProto;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeptSerializeToFile {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // 1. create 100 dummy users;
        List<UserProto.User> users = genDummyUsers(100);
        // 2. create 100 dummy projects;
        List<ProjectProto.Project> projects = genDummyProjects(100, users);
        // 3. create Dept message builder object
        DeptProto.Department.Builder deptBuilder = DeptProto.Department.newBuilder();
        // 4. set message object properties
        deptBuilder.setManager(users.get(getRandomUserId(100)));
        deptBuilder.addAllProjects(projects);
        deptBuilder.addAllStaffs(users);

        System.out.println(deptBuilder);
        // 5. write out protobuf message to file / or to network
        DeptProto.Department department = deptBuilder.build();
        FileOutputStream fos = new FileOutputStream("dept_message.bin");
        department.writeTo(fos); // dump message data to file
        fos.close();
    }

    // method to create dummy users
    private static List<UserProto.User> genDummyUsers(int user_count) {
        List<UserProto.User> users = new ArrayList<>();
        // user.id is increment from 0 to count-1
        for(int i=0; i<user_count; i++) {
            UserProto.User user = UserProto.User.newBuilder()
                    .setId(i)
                    .setName("name_"+i)
                    .setIsActive(true)
                    .build();

            users.add(user);
        }
        return users;
    }

    // method to create dummy users
    private static List<ProjectProto.Project> genDummyProjects(int project_count, List<UserProto.User> users) {
        List<ProjectProto.Project> projects = new ArrayList<>();
        // project.id is increment from 0 to count-1
        for(int i=0; i<project_count; i++) {
            ProjectProto.Project project = ProjectProto.Project.newBuilder()
                    .setProjectId("project_"+i)
                    .setProjectManager(users.get(getRandomUserId(users.size())))
                    .setStatusValue(getRandomProjectStatus())
                    .build();

            projects.add(project);
        }
        return projects;
    }

    // method to get a random userId
    private static int getRandomUserId(int user_count) {
        return rand.nextInt(user_count);
    }

    // method to get a random project status
    private static int getRandomProjectStatus() {
        // status betwwn [0 - 4]
        return rand.nextInt(4);
    }

}
