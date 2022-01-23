package witlab.sf02;

import com.google.protobuf.util.JsonFormat;
import witlab.sf02.v1.Employee;
import java.io.FileInputStream;

public class EmployeeV2Read {
    public static void main(String[] args) throws Exception {
        String[] v2EmployeeIdRange = new String[] {"004","005","006"};

        for(String empId : v2EmployeeIdRange) {
            FileInputStream inputStream = new FileInputStream(empId+".bin");
            Employee employee = Employee.parseFrom(inputStream);

            // print message as JSON
            String jsonString = JsonFormat.printer().print(employee);
            System.out.println(jsonString);

            inputStream.close();
        }

    }
}
