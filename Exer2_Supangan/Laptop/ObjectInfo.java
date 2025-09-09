public class ObjectInfo {
    private String name;
    private String type;

    public ObjectInfo() {
        this.name = "Unknown";
        this.type = "Unknown";
    }

    public ObjectInfo(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void displayObject() {
        System.out.println("Object Name: " + name);
        System.out.println("Object Type: " + type);
        System.out.println();
    }
}