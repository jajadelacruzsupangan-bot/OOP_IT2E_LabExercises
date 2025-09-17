public class TransportationTester {
    public static void main(String[] args) {
        Truck truck = new Truck("Truck", 3, "Blue", "Isuzu", "ENG123", "Diesel", "ABC-123");
        SUV suv = new SUV("SUV", 5, "Black", "Toyota", "ENG234", "Gasoline", "XYZ-456");
        Tricycle tricycle = new Tricycle("Tricycle", 3, "Red", "Honda", "ENG345", "Gasoline", "TRI-789");
        Motorcycle motorcycle = new Motorcycle("Motorcycle", 2, "Green", "Kawasaki", "ENG456", "Gasoline", "MOTO-321");
        Kariton kariton = new Kariton("Kariton", 1, "Brown");
        Airplane airplane = new Airplane("Airplane", 150, "White", "Airbus", "ENG567", "Jet", "AIR-001");
        Helicopter helicopter = new Helicopter("Helicopter", 5, "Gray", "Bell", "ENG678", "Turbine", "HEL-002");
        Submarine submarine = new Submarine("Submarine", 50, "Black", "Navy", "ENG789", "Nuclear", "SUB-003");
        WaterBoat waterBoat = new WaterBoat("Water Boat", 20, "White", "Yamaha", "ENG890", "Diesel", "BOAT-004");

        truck.showInfo();
        System.out.println();
        suv.showInfo();
        System.out.println();
        tricycle.showInfo();
        System.out.println();
        motorcycle.showInfo();
        System.out.println();
        kariton.showInfo();
        System.out.println();
        airplane.showInfo();
        System.out.println();
        helicopter.showInfo();
        System.out.println();
        submarine.showInfo();
        System.out.println();
        waterBoat.showInfo();
    }
}
