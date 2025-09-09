public class CarTester {
    public static void main(String[] args) {
        Car c1 = new Car("Black", "123 ABC", "CHS-987654",
                         "Toyota", "Corolla", "Gasoline");
        Car c2 = new Car("Red", "XYZ 321", "CHS-123456"); // 3-arg
        Car c3 = new Car(); // default

        c1.displayInfo();
        c2.displayInfo();
        c3.displayInfo();
    }
}