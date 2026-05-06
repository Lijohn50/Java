import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
                List<String> names = Arrays.asList("Java", "java", "Python", "python", "JAVA", "PHP");
                Set<String> check = new HashSet<>();
                for(String n : names)
                {
                    if(check.add(n.toLowerCase()))
                    {
                        System.out.println(n);
                    }
                }
            }
        }