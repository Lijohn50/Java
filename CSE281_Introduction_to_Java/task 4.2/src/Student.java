public class Student {
    private String name;
    private int roll;
    private String program;
    private int batch;

    Student()
    {
        name = "john doe";
        roll = 12;
        program = "CSE";
        batch = 64;
    }

    void student(String name, int roll)
    {
        this.name = name;
        this.roll = roll;
    }
    void student(int roll, int batch)
    {
        this.roll = roll;
        this.batch = batch;
    }
    void student (int roll, String program, int batch)
    {
        this.roll = roll;
        this.program = program;
        this.batch = batch;
    }

    void setName(String name)
    {
        this.name = name;
    }
    void setRoll(int roll)
    {
        this.roll = roll;
    }
    void setProgram(String program)
    {
        this.program = program;
    }
    void setBatch(int batch)
    {
        this.batch = batch;
    }

    String getName()
    {
        return name;
    }
    int getRoll()
    {
        return roll;
    }
    String getProgram()
    {
        return program;
    }
    int getBatch()
    {
        return batch;
    }

}
