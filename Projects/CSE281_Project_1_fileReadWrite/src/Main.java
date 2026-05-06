import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        security(input);
        int choice = 0;
        while(choice != 5)
        {
            System.out.println("Your choices are :");
            System.out.println("*******************");
            System.out.println("1.Add student.");
            System.out.println("2.View student.");
            System.out.println("3.Assign course to students.");
            System.out.println("4.Search student.");
            System.out.println("5.Exit.");
            System.out.println("Enter your choice :");
            choice = input.nextInt();
            input.nextLine();

            switch(choice)
            {
                case 1:
                {
                    addStudents(input);
                    break;
                }
                case 2:
                {
                    viewStudent();
                    break;
                }
                case 3:
                {
                    assignCourse(input);
                    break;
                }
                case 4:
                {
                    searchStudent(input);
                    break;
                }
            }
        }
    }
    static void security(Scanner input)
    {
        String userName;
        String password;
        try
        {
            RandomAccessFile raf = new RandomAccessFile("security.txt", "r");
            String line = raf.readLine();
            String[] texts = line.split(",");

            do
            {
                System.out.println("Enter your username: ");
                userName = input.nextLine();
                System.out.println("Enter your password: ");
                password = input.nextLine();

                if(userName.equals(texts[0]) && password.equals(texts[1]))
                {
                    System.out.println("You have successfully logged in!");
                }
                else
                {
                    System.err.println("Invalid username or password!");
                }
            } while(!userName.equals(texts[0]) || !password.equals(texts[1]));
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("File not found");
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            System.err.println("Error reading file");
            ex.printStackTrace();
        }
    }
    static void addStudents(Scanner input)
    {
        System.out.println("Enter student ID: ");
        String id = input.nextLine();
        System.out.println("Enter student name: ");
        String name = input.nextLine();
        System.out.println("Enter program: ");
        String program = input.nextLine();
        System.out.println("Enter batch: ");
        String batch = input.nextLine();
        System.out.println("Enter student cgpa: ");
        String cgpa = input.nextLine();
        System.out.println("Student added successfully!");

        String info = id + "," + name + "," + program + "," + batch + "," + cgpa + "." + "\n";

        try
        {
            RandomAccessFile raf = new RandomAccessFile("studentInfo.txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(info);
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File not found!");
        }
        catch(IOException ex)
        {
            System.err.println("Error writing file!");
        }
    }
    static void viewStudent()
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile("studentInfo.txt", "r");
            String line;

            int serial = 1;
            while((line = raf.readLine()) != null)
            {
                System.out.println(serial++ + "." + line);
            }
            System.out.println("Viewing student completed successfully!");
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File not found!");
        }
        catch(IOException ex)
        {
            System.err.println("Error reading file!");
        }
    }
    static void assignCourse(Scanner input)
    {
        try {
            String studentId, courseName;
            List<String> courses = new ArrayList<>();
            System.out.println("Enter student ID: ");
            studentId = input.nextLine();
            System.out.println("How many courses: ");
            int amount = input.nextInt();
            input.nextLine();
            RandomAccessFile raf = new RandomAccessFile("assignedCourses.txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(studentId);
            for (int i = 0; i < amount; i++)
            {
                System.out.println("Enter course no." + (i + 1) + ": ");
                courseName = input.nextLine();
                courses.add(courseName);
            }
            for (String course : courses) {
                raf.seek(raf.length());
                raf.writeBytes("," + course);
            }
            raf.writeBytes("\n");
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File not found!");
        }
        catch(IOException ex)
        {
            System.err.println("Error writing file!");
        }
    }

    static void searchStudent(Scanner input)
    {
        System.out.println("Enter student ID: ");
        String id = input.nextLine();
        try
        {
            RandomAccessFile raf = new RandomAccessFile("assignedCourses.txt", "r");
            String line;
            int cnt = 0;
            while((line = raf.readLine()) != null)
            {
                String[] text = line.split(",");
                if(id.equals(text[0]))
                {
                    System.out.println("Advised courses are: ");
                    System.out.println("*********************");
                    System.out.println(line);
                    cnt++;
                }
            }
            if(cnt == 0)
            {
                System.err.println("Student not found!");
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("File not found!");
        }
        catch(IOException ex)
        {
            System.err.println("Error writing file!");
        }
    }
}