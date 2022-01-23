package witlab.sf02;

import com.google.protobuf.util.JsonFormat;
import witlab.sf02.v1.Employee;
import java.io.FileInputStream;

// read v1 schema data
public class EmployeeV2ReadV1 {
    public static void main(String[] args) throws Exception {
        String[] v2EmployeeIdRange = new String[] {"001","002","003"};
        for(String empId : v2EmployeeIdRange) {
            FileInputStream inputStream = new FileInputStream(empId+".bin");
            Employee employee = Employee.parseFrom(inputStream);

            // print message as JSON
            String jsonString = JsonFormat.printer()
                    .includingDefaultValueFields() // <-- show
                    .print(employee);
            System.out.println(jsonString);
            inputStream.close();
        }
    }
}
