package witlab.sf02;

import wistron.witlab.sf02.v1.Department;
import wistron.witlab.sf02.v1.Project;
import wistron.witlab.sf02.v1.User;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeptSerializeToFile {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // 1. create 100 dummy users;
        List<User> users = genDummyUsers(100);
        // 2. create 100 dummy projects;
        List<Project> projects = genDummyProjects(100, users);
        // 3. create Dept message builder object
        Department.Builder deptBuilder = Department.newBuilder();
        // 4. set message object properties
        deptBuilder.setManager(users.get(getRandomUserId(100)));
        deptBuilder.addAllProjects(projects);
        deptBuilder.addAllStaffs(users);

        System.out.println(deptBuilder);
        // 5. write out protobuf message to file / or to network
        Department department = deptBuilder.build();
        FileOutputStream fos = new FileOutputStream("dept_message.bin");
        department.writeTo(fos); // dump message data to file
        fos.close();
    }

    // method to create dummy users
    private static List<User> genDummyUsers(int user_count) {
        List<User> users = new ArrayList<>();
        // user.id is increment from 0 to count-1
        for(int i=0; i<user_count; i++) {
            User user = User.newBuilder()
                    .setId(i)
                    .setName("name_"+i)
                    .setIsActive(true)
                    .build();

            users.add(user);
        }
        return users;
    }

    // method to create dummy users
    private static List<Project> genDummyProjects(int project_count, List<User> users) {
        List<Project> projects = new ArrayList<>();
        // project.id is increment from 0 to count-1
        for(int i=0; i<project_count; i++) {
            Project project = Project.newBuilder()
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
