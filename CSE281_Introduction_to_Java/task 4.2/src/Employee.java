public class Employee {
    private String name;
    private int id;
    private double salary;

    Employee() {
        name = "john doe";
        id = 12;
        salary = 1500;
    }

    void employee(String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    void employee(int id, double salary)
    {
        this.id = id;
        this.salary = salary;
    }

    void employee(String name, int id, double salary)
    {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    void setName(String name)
    {
        this.name = name;
    }
    void setId(int id)
    {
        this.id = id;
    }
    void setSalary(double salary)
    {
        this.salary = salary;
    }

    String getName()
    {
        return name;
    }
    int getId()
    {
        return id;
    }
    double getSalary()
    {
        return salary;
    }
}
