package witlab.sf02;
import witlab.sf02.v1.Employee;
import java.io.FileOutputStream;
public class EmployeeV2Write {
    public static void main(String[] args) throws Exception {
        String[] v2EmployeeIdRange = new String[] {"004","005","006"};
        for(String empId : v2EmployeeIdRange) {
            Employee employee = Employee.newBuilder()
                    .setId(empId)
                    .setFirstName("FN_"+empId)
                    .setLastName("LN_"+empId)
                    .setPhone("PHONE_"+empId)
                    .build();
            // create file stream
            FileOutputStream outputStream = new FileOutputStream(empId+".bin");
            // write the protocol buffers binary to file
            employee.writeTo(outputStream);
            // close file stream
            outputStream.close();
        }
        // once complete, you should find 3 files (v2 Employee) under project root path
        // -- 004.bin, 005.bin & 006.bin
    }
}
