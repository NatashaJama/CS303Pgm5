import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter("report.txt")) {
            Functions.testPalindrome(out);
            Functions.testPostFixExpression(out);
            Functions.testPreFixExpression(out);
            Functions.testDecToBinary(out);
            Functions.testIsBalanced(out);
            Functions.testUndoRedo(out);
        } catch (IOException e) {
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }
}