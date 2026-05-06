import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter text: ");
        String text = input.nextLine();
        write(text);
        read();
    }

    public static void write(String text)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile("list.txt", "rw");
            raf.seek(raf.length());
            raf.writeBytes(text + "\n");
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File not found");
        }
        catch (IOException ex)
        {
            System.err.println("Failed to write");
        }

    }
    public static void read()
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile("list.txt", "r");
            String line;
            while ((line = raf.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch(FileNotFoundException ex)
        {
            System.err.println("File not found");
        }
        catch(IOException ex)
        {
            System.err.println("Failed to read");
        }

    }
}