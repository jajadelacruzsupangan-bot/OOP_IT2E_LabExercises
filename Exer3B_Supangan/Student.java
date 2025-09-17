public class Student extends Person {
    String course;

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Course: " + course);
    }
}