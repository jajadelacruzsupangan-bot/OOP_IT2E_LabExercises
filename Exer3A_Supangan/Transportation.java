public class Transportation {
    protected String name;
    protected int capacity;
    protected String color;

    public Transportation(String name, int capacity, String color) {
        this.name = name;
        this.capacity = capacity;
        this.color = color;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("Capacity: " + capacity);
        System.out.println("Color: " + color);
    }

    public void move() {
        System.out.println(name + " is moving.");
    }
}
