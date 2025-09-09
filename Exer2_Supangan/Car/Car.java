public class Car {
    private String color;
    private String plateNo;
    private String chassisNo;
    private String brand;
    private String model;
    private String engineType;

    // No-argument constructor
    public Car() {
        this("No color", "No plate number", "No chassis number yet",
             "No brand", "No model", "No engine type");
    }

    // 3-argument constructor (original version)
    public Car(String color, String plateNo, String chassisNo) {
        this(color, plateNo, chassisNo, "No brand", "No model", "No engine type");
    }

    // 6-argument constructor (with brand, model, engineType)
    public Car(String color, String plateNo, String chassisNo,
               String brand, String model, String engineType) {
        this.color = color;
        this.plateNo = plateNo;
        this.chassisNo = chassisNo;
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
    }

    // Display method
    public void displayInfo() {
        System.out.println("Car Information:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Color: " + color);
        System.out.println("Plate No: " + plateNo);
        System.out.println("Chassis No: " + chassisNo);
        System.out.println("Engine Type: " + engineType);
        System.out.println();
    }
}