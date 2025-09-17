public class LandTransport extends Transportation {
    protected String brand;
    protected String engineNo;
    protected String engineType;
    protected String plateNo;

    public LandTransport(String name, int capacity, String color, 
                         String brand, String engineNo, String engineType, String plateNo) {
        super(name, capacity, color);
        this.brand = brand;
        this.engineNo = engineNo;
        this.engineType = engineType;
        this.plateNo = plateNo;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Brand: " + brand);
        System.out.println("Engine No: " + engineNo);
        System.out.println("Engine Type: " + engineType);
        System.out.println("Plate No: " + plateNo);
    }
}
