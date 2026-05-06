public class Car {
    private String brand;
    private String model;
    private int year;
    private double price;

    Car()
    {
        brand = "Bugatti";
        model = "vyron";
        year = 2018;
        price = 50000;
    }

    void car(String brand, int year)
    {
        this.brand = brand;
        this.year = year;
    }
    void car(String model, double price)
    {
        this.model = model;
        this.price = price;
    }
    void car(String model, int year, double price)
    {
        this.model = model;
        this.year = year;
        this.price = price;
    }

    void setBrand(String brand)
    {
        this.brand = brand;
    }
    void setModel(String model)
    {
        this.model = model;
    }
    void setYear(int year)
    {
        this.year = year;
    }
    void setPrice(double price)
    {
        this.price = price;
    }

    String getBrand()
    {
        return brand;
    }
    String getModel()
    {
        return model;
    }
    int getYear()
    {
        return year;
    }
    double getPrice()
    {
        return price;
    }
}
