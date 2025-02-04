public abstract class Car {

    String model;
    int year;
    String color;
    int horsePower;
    int mileage;
    double engineVolume;
    int fuel;

    public Car(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
        this.model = model;
        this.year = year;
        this.color = color;
        this.horsePower = horsePower;
        this.mileage = mileage;
        this.engineVolume = engineVolume;
        this.fuel = fuel;
    }


    public static class Suzuki extends Car {
        public Suzuki(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
            super(model, year, color, horsePower, mileage, engineVolume, fuel);
        }
    }


    public static class Toyota extends Car {
        public Toyota(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
            super(model, year, color, horsePower, mileage, engineVolume, fuel);
        }
    }

    public static class Ford extends Car {
        public Ford(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
            super(model, year, color, horsePower, mileage, engineVolume, fuel);
        }
    }

    public static class BMW extends Car {
        public BMW(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
            super(model, year, color, horsePower, mileage, engineVolume, fuel);
        }
    }

    public static class Audi extends Car {
        public Audi(String model, int year, String color, int horsePower, int mileage, double engineVolume, int fuel) {
            super(model, year, color, horsePower, mileage, engineVolume, fuel);
        }
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
}
