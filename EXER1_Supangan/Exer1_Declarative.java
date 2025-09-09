import java.util.Arrays;

public class Exer1_Declarative {
    public static void main(String[] args) {
      
        int[] numbers = {3, 7, 2, 10, 6};

        int max = Arrays.stream(numbers)
                        .max()
                        .getAsInt();

        System.out.println("Maximum number (Declarative): " + max);
    }
}