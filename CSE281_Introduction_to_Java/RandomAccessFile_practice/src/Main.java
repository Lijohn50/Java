import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
//        int id = 3;
//        String name = "Bob";
//        double cgpa = 3.10;

//        String line = id + ", " + name + ", " + cgpa + "\n"; // 1, John Doe, 3.52
//        write(line);

        read();
    }

    public static void read() {
        try {
            RandomAccessFile raf = new RandomAccessFile("students.txt", "r");
            String line;
            while ((line = raf.readLine()) != null) {

                String[] strings = line.split(","); // 1, John, 3.52

                int id = Integer.parseInt(strings[0]); // String to Integer "1" -> 1
                String name = strings[1];
                double cgpa = Double.parseDouble(strings[2]); // String to Double "3.52" -> 3.52

                System.out.println(id + " " + name + " " + cgpa);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
            ex.printStackTrace();
        }
    }

    public static void write(String line) {
        try {
            RandomAccessFile raf = new RandomAccessFile("students.txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(line);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to open file");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
            ex.printStackTrace();
        }
    }
}