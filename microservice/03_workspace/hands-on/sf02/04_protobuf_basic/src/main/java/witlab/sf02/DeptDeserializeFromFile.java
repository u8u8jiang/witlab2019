package witlab.sf02;

import witlab.sf02.v1.DeptProto;
import witlab.sf02.v1.ProjectProto;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class DeptDeserializeFromFile {
    public static void main(String[] args) throws Exception {
        System.out.println("Deserialize message from file..");
        // 1. open file stream
        FileInputStream fis = new FileInputStream("dept_message.bin");
        // 2. use .parseFrom() method to read binary protobuf data
        DeptProto.Department dept =  DeptProto.Department.parseFrom(fis);
        // 3. let's read the content
        // Who is "Dept Manger"?
        System.out.println("Dept Manager: " + dept.getManager().getName());
        // How many staffs this department has?
        System.out.println("Staff count: " + dept.getStaffsCount());
        // How many projects this department handled?
        System.out.println("Project count: " + dept.getProjectsCount());
        // How many projects in each Status: UNKNOWN, START, PENDING & CLOSE?
        Map<ProjectProto.ProjectStatus, Integer> projectStatusMap = new HashMap<>();
        projectStatusMap.put(ProjectProto.ProjectStatus.UNKOWN, 0);
        projectStatusMap.put(ProjectProto.ProjectStatus.START, 0);
        projectStatusMap.put(ProjectProto.ProjectStatus.PENDING, 0);
        projectStatusMap.put(ProjectProto.ProjectStatus.CLOSE, 0);

        for(ProjectProto.Project project : dept.getProjectsList()) {
            ProjectProto.ProjectStatus status = project.getStatus();
            int current_value = projectStatusMap.get(status);
            projectStatusMap.put(status, ++current_value);
        }
        System.out.println(projectStatusMap);
    }
}
