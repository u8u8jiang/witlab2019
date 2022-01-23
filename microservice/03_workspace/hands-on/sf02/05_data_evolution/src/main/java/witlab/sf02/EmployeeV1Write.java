package witlab.sf02;

import witlab.sf02.v1.Employee;
import java.io.FileOutputStream;

public class EmployeeV1Write {
    public static void main(String[] args) throws Exception {
        String[] v1EmployeeIdRange = new String[] {"001","002","003"};
        for(String empId : v1EmployeeIdRange) {
            Employee employee = Employee.newBuilder()
                    .setId(empId)
                    .setFirstName("FN_"+empId)
                    .setLastName("LN_"+empId)
                    .build();
            // create file stream
            FileOutputStream outputStream = new FileOutputStream(empId+".bin");
            // write the protocol buffers binary to file
            employee.writeTo(outputStream);
            // close file stream
            outputStream.close();
        }
        // once complete, you should find 3 files (v1 Employee) under project root path
        // -- 001.bin, 002.bin & 003.bin
    }
}
