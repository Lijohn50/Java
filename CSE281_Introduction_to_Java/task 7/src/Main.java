import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> arraylist = new ArrayList<Integer>();

        arraylist.add(6);
        arraylist.add(7);
        arraylist.add(5);
        arraylist.add(4);
        arraylist.add(3);
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(10);
        arraylist.add(9);
        arraylist.add(8);

        /*int sum = 0;
        for(int i = 0; i < arraylist.size(); i++)
        {
            sum += arraylist.get(i);
        }
        System.out.println(sum);*/

        /*for(int i = 0; i < arraylist.size() - 1; i++)
        {
            for(int j = 0; j < arraylist.size() - 1 - i; j++)
            {
                if(arraylist.get(j) >= arraylist.get(j + 1))
                {
                    int temp = arraylist.get(j);
                    arraylist.set(j, arraylist.get(j + 1));
                    arraylist.set(j + 1, temp);
                }
            }
        }
        System.out.println(arraylist.get(9));*/

        /*for(int i = 0; i < arraylist.size() - 1; i++)
        {
            for(int j = 0; j < arraylist.size() - 1 - i; j++)
            {
                if(arraylist.get(j) >= arraylist.get(j + 1))
                {
                    int temp = arraylist.get(j);
                    arraylist.set(j, arraylist.get(j + 1));
                    arraylist.set(j + 1, temp);
                }
            }
        }

        System.out.println(arraylist.get(0));*/

        /*double sum = 0;
        for(int i = 0; i < arraylist.size(); i++)
        {
            sum += arraylist.get(i);
        }
        System.out.println(sum / arraylist.size());*/
        /*Set<String> countryNames = new HashSet<String>();
        countryNames.add("India");
        countryNames.add("USA");
        countryNames.add("Germany");
        countryNames.add("France");
        countryNames.add("Finland");

        for(String n : countryNames)
        {
            System.out.println(n);
        }*/

        /*Map<Integer, Double> employeeData = new HashMap<Integer, Double>();
        employeeData.put(101, 120.23);
        employeeData.put(102, 150.23);
        employeeData.put(103, 160.23);
        employeeData.put(104, 170.23);
        employeeData.put(105, 180.23);

        for(Map.Entry<Integer, Double> entry : employeeData.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/

        Map<Integer, Double> employeeData = new HashMap<Integer, Double>();
        employeeData.put(101, 120.23);
        employeeData.put(102, 150.23);
        employeeData.put(103, 160.23);
        employeeData.put(104, 170.23);
        employeeData.put(105, 180.23);

        double totalSalary = 0;
        for(Map.Entry<Integer, Double> entry : employeeData.entrySet())
        {
            totalSalary += entry.getValue();
        }

        System.out.println(totalSalary / employeeData.size());







    }
}