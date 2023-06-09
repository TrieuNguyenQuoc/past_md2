package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class StudentManager {
    private List<Student> studentList;
    private static StudentManager instance;

    private StudentManager() {
        studentList = new ArrayList<>();
        studentList.add(new Student("SV1","A", 19, false,"HàTây",2));
        studentList.add(new Student("SV2","B", 25, false,"HàTây",3));
        studentList.add(new Student("SV3","C", 25, false,"HàTây",4));
        studentList.add(new Student("SV4","D", 25, false,"HàTây",5));
        studentList.add(new Student("SV5","E", 24, false,"HàTây",6));
        studentList.add(new Student("SV6","F", 21, false,"HàTây",7));
        studentList.add(new Student("SV7","G", 20, false,"HàTây",8));
    }
    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }
    public void addStudent(Student student) {
        Pattern pattern = Pattern.compile("[A-az-z]+");
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }
    public Student search(String id) {
        Student student = studentList.stream().filter(e->e.getId().equals(id)).findFirst().orElse(null);
        return student;
    }
    public int searchAndReturnIndex(String id) {
        int index = -1;
        for (int i = 0; i < studentList.size(); i ++) {
            if (studentList.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void sorting( boolean ascending) {
        if (ascending == true) {
            studentList.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o1.getScore(),o2.getScore());
                }
            });
        } else {
            studentList.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return Double.compare(o2.getScore(), o1.getScore());
                }
            });
        }
    }
    public void display() {
        String format = "%-5s %-10s %-10d %-10b %-20s %-10f\n";
        String formatH = "%-5s %-10s %-10s %-10s %-20s %-10s\n";
        System.out.printf(formatH,"id","Name","Age","Sex","Address","Score");
        studentList.forEach(element -> {
            System.out.printf(format,element.getId(),element.getName(),element.getAge(),element.isSex(),element.getAddress(),element.getScore());
        });
    }
}
