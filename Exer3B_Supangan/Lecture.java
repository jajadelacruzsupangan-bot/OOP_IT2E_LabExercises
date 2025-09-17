public class Lecture extends Course {
    int hours;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Lecture Hours: " + hours);
    }
}