package cars;

public abstract class Car {

    private final String model;
    private final int year;
    private String color;
    private final int horsePower;
    private final int mileage;
    private final double engineVolume;
    private final int fuel;

    public Car(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
        this.model = model;
        this.year = year;
        this.color = color;
        this.horsePower = horsePower;
        this.mileage = mileage;
        this.engineVolume = engineVolume;
        this.fuel = fuel;
    }

    public String getFullInfo() {
        return "Model: " + model + ", Year: " + year + ", Color: " + color + ", horsePower " + horsePower + ", Mileage: " + mileage + ", Engine Volume: " + engineVolume + "L, Fuel: " + fuel;
    }

    public String getColor() {
        return color;
    }
    public int HorsePower() {
        return horsePower;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getYear() {
        return year;
    }
    public int mileage() {return mileage;}
    }
