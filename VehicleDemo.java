class Vehicle {
    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
        System.out.println("Vehicle constructor: " + plate);
    }

    public String getPlate() { return plate; }

    public void describe() {
        System.out.println("Vehicle: " + plate);
    }
}

class Car extends Vehicle {
    private final int doors;

    public Car(String plate, int doors) {
        super(plate); // calls Vehicle's constructor
        this.doors = doors;
        System.out.println("Car constructor: " + plate);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("  doors: " + doors);
    }
}

class ElectricCar extends Car {
    private final int rangeKm;

    public ElectricCar(String plate, int doors, int rangeKm) {
        super(plate, doors); // calls Car's constructor (which calls Vehicle's)
        this.rangeKm = rangeKm;
        System.out.println("ElectricCar constructor: " + plate);
    }

    @Override
    public void describe() {
        super.describe();
        System.out.println("  range: " + rangeKm + " km");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("--- creating ElectricCar ---");
        ElectricCar e = new ElectricCar("EV-001", 5, 400);
        System.out.println("--- describe ---");
        e.describe();
    }
}