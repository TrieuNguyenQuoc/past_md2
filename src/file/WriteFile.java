package file;

import student.Student;
import student.StudentManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private final String FILENAME = "students.csv";
//    private final String HEADING="index,id,name,age,sex,address,score\n";
    public void writeToFile() {
        try {
            File file = new File(FILENAME);
            FileWriter fw = new FileWriter(file);
//            fw.write(HEADING);
            for(int index = 0; index < StudentManager.getInstance().getStudentList().size(); index++) {
                Student student = StudentManager.getInstance().getStudentList().get(index);
                String line = index + "," + student.getId() +","+student.getName()+","+student.getAge()+","+student.isSex()+","+student.getAddress()+","+student.getScore()+"\n";
                fw.write(line);
            }
            System.out.println("Ghi thành công");
            fw.close();
        } catch (IOException e) {
            System.err.println("Lỗi file");
            System.err.println(e.getMessage());
        }
    }
}