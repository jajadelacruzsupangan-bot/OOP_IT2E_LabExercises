public class ProjectTester {
    public static void main(String[] args) {

        // Student
        Student s = new Student();
        s.name = "Jaja Cruz";
        s.age = 19;
        s.course = "BSIT";
        s.displayInfo();

        System.out.println();

        // Teacher
        Teacher t = new Teacher();
        t.name = "Mr. Dongiapon";
        t.age = 40;
        t.subject = "Java Programming";
        t.displayInfo();

        System.out.println();

        // Lecture
        Lecture lec = new Lecture();
        lec.code = "IT101";
        lec.title = "Introduction to IT";
        lec.hours = 3;
        lec.displayInfo();

        System.out.println();

        // Laboratory
        Laboratory lab = new Laboratory();
        lab.code = "IT102";
        lab.title = "Computer Lab";
        lab.labRoom = "Lab-1";
        lab.displayInfo();
    }
}