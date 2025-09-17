public class Laboratory extends Course {
    String labRoom;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Lab Room: " + labRoom);
    }
}