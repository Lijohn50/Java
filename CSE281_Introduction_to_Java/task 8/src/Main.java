import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int choice = 0;
        while(choice != 5)
        {
            System.out.println("1.Add student.");
            System.out.println("2.View student Info.");
            System.out.println("3.Add course");
            System.out.println("4.Search by student ID");
            System.out.println("5.Exit");

            System.out.println("Enter your choice: ");
            choice = input.nextInt();

            switch(choice)
            {
                case 1: addStudentInfo(input);
                break;
                case 2: viewStudent();
                break;
                case 3: addCourse(input);
                break;
                case 4: searchStudent(input);
            }

        }
    }

    static void addStudentInfo(Scanner input)
    {
        try
        {
            System.out.println("Enter student ID: ");
            int studentID = input.nextInt();
            System.out.println("Enter student name: ");
            String studentName = input.next();
            System.out.println("Enter student password: ");
            String studentPassword = input.next();
            System.out.println("Enter course program: ");
            String courseProgram = input.next();
            System.out.println("Enter batch : ");
            String batch = input.next();
            System.out.println("Enter CGPA : ");
            String cgpa = input.next();

            FileWriter fw = new FileWriter("Student.txt", true);
            fw.write(studentID + "," + studentName + "," + studentPassword + "," + courseProgram + "," + batch + "," + cgpa + "\n");
            fw.close();
        }
        catch (IOException ex)
        {
            System.err.println("File not found");
        }
    }

    static void viewStudent()
    {
        try
        {
            File file = new File("Student.txt");
            Scanner reader = new Scanner(file);

            while(reader.hasNext())
            {
                String line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("Not found");
        }
    }

    static void addCourse(Scanner input)
    {
        try
        {
            System.out.println("Enter student ID: ");
            String studentID = input.nextLine();
            System.out.println("Enter course code: ");
            String courseCode = input.nextLine();

            FileWriter fw = new FileWriter("addedCourse.txt", true);
            fw.write(studentID + "," + courseCode + "\n");
            fw.close();
        }
        catch(IOException ex)
        {
            System.err.println("Not able to write");
        }
    }

    static void searchStudent(Scanner input)
    {
        try
        {
            System.out.println("Enter student ID: ");
            String searchID = input.nextLine();

            File file = new File("addedCourse.txt");
            Scanner reader = new Scanner(file);
            boolean found = false;

            while(reader.hasNextLine())
            {
                String line = reader.nextLine();
                String[] parts = line.split(",");
                if(parts[0].equals(searchID))
                {
                    System.out.println("Assigned course: " + parts[1]);
                    found = true;
                }
            }
            if(!found)
            {
                System.err.println("course not found");
            }
            reader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("No Course found");
        }

    }
}