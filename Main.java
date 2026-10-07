import java.io.PrintWriter;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        try{
            PrintWriter out = new PrintWriter("report.txt");
            Functions.testPalindrome(out);
            Functions.testPostFixExpression(out);
            Functions.testPreFixExpression(out);
            Functions.testDecToBinary(out);
            Functions.testIsBalanced(out);
            Functions.testUndoRedo(out);
            out.close();
        }
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
        }
    }
}
