public class Rectangle {
    private double length;
    private double width;
    private int angle;
    private int size;


    Rectangle()
    {
        length = 2.3;
        width = 3.4;
        angle = 34;
        size = 12;
    }

    void rectangle(double length, double width)
    {
        this.length = length;
        this.width = width;
    }

    void rectangle(double length, int angle)
    {
        this.length = length;
        this.angle = angle;
    }

    void rectangle(double length, double width, int angle, int size)
    {
        this.length = length;
        this.width = width;
        this.angle = angle;
        this.size = size;
    }

    void setLength(double length)
    {
        this.length = length;
    }
    void setWidth(double width)
    {
        this.width = width;
    }
    void setAngle(int angle)
    {
        this.angle = angle;
    }
    void setSize(int size)
    {
        this.size = size;
    }

    double getLength()
    {
        return length;
    }
    double getWidth()
    {
        return width;
    }
    int getAngle()
    {
        return angle;
    }
    int getSize()
    {
        return size;
    }

}
