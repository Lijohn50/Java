public class Main {
    public static void main(String[] args) {

        /*Rectangle rectangle = new Rectangle();
        rectangle.length = 4.5;
        rectangle.width = 3.2;
        System.out.println("Length is " + rectangle.length + " and width is  " + rectangle.width);

        Employee employee = new Employee();
        employee.name = "john doe";
        employee.id = 2021;
        employee.salary = 15000.00;
        System.out.println("Employee name is " + employee.name + " and his id is " + employee.id + " and his salary is " + employee.salary);

        Circle circle = new Circle();
        circle.radius = 5.9;
        System.out.println("radius is " + circle.radius);

        Car car = new Car();
        car.brand = "MClaren";
        car.model = "20A3";
        car.year = 2023;
        System.out.println("Car brand is " + car.brand + " and model number is " + car.model + " and released year is " + car.year);

        Student student = new Student();
        student.name = "john doe";
        student.roll = 201;
        student.program = "B.sc in CSE";
        student.batch = 64;
        System.out.println("Student name is " + student.name + " and his roll is " + student.roll + ". His program is " + student.program + " and his batch is " + student.batch);*/

        Rectangle rectangle = new Rectangle();
        rectangle.setLength(21);
        rectangle.setWidth(34.5);
        rectangle.setAngle(45);
        rectangle.setSize(23);
        System.out.println(rectangle.getLength() + " " + rectangle.getWidth() + " " + rectangle.getSize() + " " + rectangle.getAngle());

        Employee employee = new Employee();
        employee.setName("john doe");
        employee.setId(34);
        employee.setSalary(10000);
        System.out.println(employee.getName() + " " + employee.getId() + " " + employee.getSalary());

        Circle circle = new Circle();
        circle.setRadius(22);
        circle.setColor("green");
        circle.setSize(12);
        System.out.println(circle.getRadius() + " " + circle.getColor() + " " + circle.getSize());

        Car car = new Car();
        car.setBrand("bugatti");
        car.setModel("vyron");
        car.setPrice(50000);
        car.setYear(2014);
        System.out.println(car.getBrand() + " " + car.getModel() + " " + car.getPrice() + " " + car.getYear());

        Student student = new Student();
        student.setName("john doe");
        student.setRoll(34);
        student.setProgram("cse");
        student.setBatch(64);
        System.out.println(student.getName() + " " + student.getRoll() + " " + student.getProgram() + " " + student.getBatch());

    }
}