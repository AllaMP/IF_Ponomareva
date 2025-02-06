import cars.Car;
import cars.models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Suzuki("Swift", 2000, "Blue", 105, 120000, 1.2, 95));
        cars.add(new Toyota("Corolla", 2010, "Green", 150, 95000, 1.8, 92));
        cars.add(new Ford("Focus", 2007, "Red", 120, 110000, 2.0, 92));
        cars.add(new BMW("X5", 2015, "Black", 200, 60000, 3.0, 95));
        cars.add(new Audi("A4", 2008, "Green", 180, 80000, 2.0, 95));
        cars.add(new Suzuki("Vitara", 2018, "White", 170, 40000, 1.6, 95));
        cars.add(new Toyota("Camry", 2012, "Silver", 200, 70000, 2.5, 95));
        cars.add(new Ford("Mustang", 2019, "Yellow", 466, 20000, 5.0, 100));
        cars.add(new BMW("320i", 2005, "Silver", 200, 90000, 2.0, 95));
        cars.add(new Audi("Q7", 2017, "Gold", 200, 50000, 3.0, 95));

        printCarsAfter2006(cars);
        changeGreenToRed(cars);
        printHighMileageCars(cars);
        getHorsePower(cars);
    }

    public static void printCarsAfter2006(List<Car> cars) {
        for (Car car : cars) {
            if (car.getYear() < 2006){
                System.out.println("Устаревший авто: " + car.getFullInfo());
            }
        }
    }

    public static void changeGreenToRed(List<Car> cars) {
        for (Car car : cars) {
            if ("Green".equals(car.getColor())) {
                car.setColor("Red");
                System.out.println("Цвет автомобиля изменен на красный: " + car.getFullInfo());
            }
        }
    }

    public static void getHorsePower(List<Car> cars) {
        for (Car car : cars) {
            if (car.HorsePower() > 200) {
                System.out.println("Самый мощный автомобиль :" + car.getFullInfo());
            }
        }
    }

    public static void printHighMileageCars(List<Car> cars) {
        for (Car car : cars) {
            if (car.mileage() > 100000) {
                System.out.println("Автомобиль с большим пробегом: " + car.getFullInfo());
            }
        }
    }
}
