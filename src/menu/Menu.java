package menu;

import file.ReadFile;
import student.Student;
import student.StudentManager;

import java.util.Scanner;

public class Menu {
    public final String[] menus = {"""
           ---- CHƯƠNG TRÌNH QUẢN LÝ NHÂN VIÊN ----
           Chọn chức năng theo số (để tiếp tục)
           1 Xem danh sách sinh viên
           2 Thêm mới
           3 Cập nhật
           4 Xóa 
           5 Sắp xếp
           6 Đọc từ file
           7 Ghi vào file
           8 Thoát"""};
    public final String[] menusAdd = {"""
          Mã sinh viên
          Họ tên
          Tuổi Giới tính
          Địa chỉ
          Điểm trung bình
"""};
    public final String[] display5Student = {"Lựa chọn \"Thêm mới\"", "Hiển thị 5 sinh viên"};
    public final String[] menuSorting = {"Sắp xếp điểm trung bình tăng dần", "Sắp xếp điểm trung bình giảm dần", "Thoát"};
    private ReadFile.WriteFile writeFile;
    private ReadFile readFile;

    public Menu ( ) {
        writeFile = new ReadFile.WriteFile();
        readFile = new ReadFile();
    }

    public void display ( ) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            for (int index = 1; index <= menus.length; index++) {
                System.out.println(index + ". " + menus[index - 1]);
            }
            System.out.println("Chọn chức năng:");
            int choosen = scanner.nextInt();
            switch (choosen) {
                case 1 -> {
                    System.out.println("Mời lựa chọn:");
                    for (int index = 0; index < display5Student.length; index++) {
                        System.out.println(index + ". " + display5Student[index]);
                    }
                    int select = scanner.nextInt();
                    if (select == 0) {
                        Student student = addOneStudent();
                        if (student != null) {
                            StudentManager.getInstance().addStudent(student);
                        }
                    } else if (select == 1) {
                        displayFiveStudent();
                    }

                }
                case 2 -> {
                    Student student = addOneStudent();
                    if (student != null) {
                        StudentManager.getInstance().addStudent(student);
                    }
                }
                case 3 -> {
                    editStudent();
                }
                case 4 -> {
                    deleteStudent();
                }
                case 5 -> {
                    sortingStudent();
                }
                case 6 -> {
                    readFile.readFile();
                }
                case 7 -> {
                    System.out.println("Cập nhật file thành công");
//                    String select = scanner.next();
                        writeFile.writeToFile();

                }
                case 8 -> {
                    exit = true;
                }
                default -> {
                    System.out.println("Nhập sai, mời nhập lại:");
                }
            }
        }
    }

    public void displayFiveStudent ( ) {// hien thi
        Scanner scanner = new Scanner(System.in);
        for (int index = 0; index < 5; index++) {
            String txt = scanner.nextLine();
            System.out.println(StudentManager.getInstance().getStudentList().get(index));
        }
    }

    public Student addOneStudent ( ) {// them
        Student newStudent = null;
        Scanner scanner = new Scanner(System.in);
        boolean notFinish = true;
        while (notFinish) {
            try {
                System.out.println("Nhập mã sinh viên:");
                String id = scanner.next();
                System.out.println("Nhập họ tên:");
                String name = scanner.next();
                System.out.println("Nhập tuổi:");
                int age = scanner.nextInt();
                System.out.println("Nhập giới tính (1-Nam,0-Nữ):");
                int sexInt = scanner.nextInt();
                boolean sex = sexInt == 0 ? true : false;
                System.out.println("Nhập địa chỉ:");
                String address = scanner.next();
                System.out.println("Nhập điểm trung bình:");
                double score = scanner.nextDouble();
                newStudent = new Student(id, name, age, sex, address, score);
                notFinish = false;
            } catch (Exception e) {
                System.out.println("Nhập sai, mời nhập lại!");
            }

        }
        return newStudent;
    }

    public void editStudent ( ) {//sua
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào mã sinh viên cần chỉnh sửa");
        String id = scanner.next();
        int index = StudentManager.getInstance().searchAndReturnIndex(id);
        if (index != -1) {
            Student editStudent = addOneStudent();
            System.out.println(editStudent);
            StudentManager.getInstance().getStudentList().set(index, editStudent);
            StudentManager.getInstance().display();
        } else {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
        }
    }

    public void deleteStudent ( ) {// xoa
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào mã sinh viên cần chỉnh sửa");
        String id = scanner.next();
        int index = StudentManager.getInstance().searchAndReturnIndex(id);
        if (index != -1) {
            System.out.println("Bạn có muốn xóa(Có/Không)");
            String select = scanner.next();
            if (select.equals("Có")) {
                StudentManager.getInstance().getStudentList().remove(index);
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Không thành công");
            }

        } else {
            System.out.println("Không tìm được sinh viên với mã sinh viên trên");
        }
    }

    public void sortingStudent ( ) {//sap xep
        Scanner scanner = new Scanner(System.in);
        boolean notFinish = true;
        while (notFinish) {
            for (int index = 1; index <= menuSorting.length; index++) {
                System.out.println(index + ". " + menuSorting[index - 1]);
            }
            System.out.println("Chọn chức năng:");
            int select = scanner.nextInt();

            switch (select) {
                case 1 -> {
                    StudentManager.getInstance().sorting(true);
                    StudentManager.getInstance().display();
                }
                case 2 -> {
                    StudentManager.getInstance().sorting(false);
                    StudentManager.getInstance().display();
                }
                case 3 -> {
                    notFinish = false;
                }
            }
        }
    }
}