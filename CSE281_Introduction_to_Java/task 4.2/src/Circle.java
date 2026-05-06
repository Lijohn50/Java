public class Circle {
    private double radius;
    private String color;
    private int size;

    Circle()
    {
        radius = 3.4;
        color = "blue";
        size = 3;
    }

    void circle(double radius, int size)
    {
        this.radius = radius;
        this.size = size;
    }
    void circle(String color, int size)
    {
        this.color = color;
        this.size = size;
    }
    void circle(double radius, String color, int size)
    {
        this.radius = radius;
        this.color = color;
        this.size = size;
    }

    void setRadius(double radius)
    {
        this.radius = radius;
    }
    void setColor(String color)
    {
        this.color = color;
    }
    void setSize(int size)
    {
        this.size = size;
    }

    double getRadius()
    {
        return radius;
    }
    String getColor()
    {
        return color;
    }
    int getSize()
    {
        return size;
    }
}
