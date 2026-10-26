import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Functions {

    // PRE: Receives a string.
    // POST: Returns true if every character is a digit.
    public static boolean isDigits(String inString) {
        if (inString == null || inString.isEmpty()) {
            return false;
        }

        for (char c : inString.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    // STACK 1: PALINDROME
    public static void testPalindrome(PrintWriter out) {
        System.out.println("\nSTACK 1: PALINDROME");
        out.println("\nSTACK 1: PALINDROME");

        MyStack<Character> stack = new MyStack<>();
        ArrayList<String> words = new ArrayList<>(Arrays.asList(
            "tacocat", "bubba", "a", "mom",
            "abba", "abccba", "abcba"
        ));

        // PRE: A list of words is provided.
        // POST: Prints whether each word is a palindrome.
        for (String word : words) {
            stack.clear();

            for (int i = 0; i < word.length(); i++) {
                stack.push(word.charAt(i));
            }

            boolean palindrome = true;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != stack.pop()) {
                    palindrome = false;
                    break;
                }
            }

            printResult(out, word + " is a palindrome: " + palindrome);
        }
    }

    // STACK 2: POSTFIX EVALUATOR
    public static void testPostFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 2: POSTFIX EVALUATOR");
        out.println("\nSTACK 2: POSTFIX EVALUATOR");

        ArrayList<String> expressions = new ArrayList<>(Arrays.asList(
            "5 7 * 15 + 25 *",
            "5 0 / 4 +",
            "15 2 * 3 / 15 +",
            "3 5 7 + *",
            "3 5 6"
        ));

        // PRE: A list of postfix expressions is provided.
        // POST: Prints each result or an invalid-expression message.
        for (String expression : expressions) {
            Integer result = evaluatePostfix(expression);

            if (result == null) {
                printResult(out,
                    "The expression: " + expression + " is invalid.");
            } else {
                printResult(out,
                    "The result of the postfix expression: "
                    + expression + " = " + result);
            }
        }
    }

    // STACK 3: PREFIX EVALUATOR
    public static void testPreFixExpression(PrintWriter out) {
        System.out.println("\nSTACK 3: PREFIX EVALUATOR");
        out.println("\nSTACK 3: PREFIX EVALUATOR");

        ArrayList<String> expressions = new ArrayList<>(Arrays.asList(
            "+ * 7 5 + 6 7",
            "- + 7 * 4 5 + 2 0",
            "- / 18 / 6 3 2",
            "* 25 + 15 * 7 5",
            "+ 4 / 0 5",
            "+ 15 / 3 * 2 15",
            "* + 7 5 3",
            "6 5 3"
        ));

        // PRE: A list of prefix expressions is provided.
        // POST: Prints each result or an invalid-expression message.
        for (String expression : expressions) {
            Integer result = evaluatePrefix(expression);

            if (result == null) {
                printResult(out,
                    "The expression: " + expression + " is invalid.");
            } else {
                printResult(out,
                    "The result of the prefix expression: "
                    + expression + " = " + result);
            }
        }
    }

    // PRE: Receives a postfix expression with space-separated tokens.
    // POST: Returns its result, or null if the expression is invalid.
    public static Integer evaluatePostfix(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return null;
        }

        MyStack<Integer> stack = new MyStack<>();
        String[] tokens = expression.trim().split("\\s+");

        try {
            for (String token : tokens) {
                if (isInteger(token)) {
                    stack.push(Integer.parseInt(token));
                } else if (isOperator(token)) {
                    if (stack.size() < 2) {
                        return null;
                    }

                    int right = stack.pop();
                    int left = stack.pop();

                    Integer result = calculate(left, right, token);

                    if (result == null) {
                        return null;
                    }

                    stack.push(result);
                } else {
                    return null;
                }
            }

            if (stack.size() != 1) {
                return null;
            }

            return stack.pop();

        } catch (NumberFormatException e) {
            return null;
        }
    }

    // PRE: Receives a prefix expression with space-separated tokens.
    // POST: Returns its result, or null if the expression is invalid.
    public static Integer evaluatePrefix(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return null;
        }

        MyStack<Integer> stack = new MyStack<>();
        String[] tokens = expression.trim().split("\\s+");

        try {
            for (int i = tokens.length - 1; i >= 0; i--) {
                String token = tokens[i];

                if (isInteger(token)) {
                    stack.push(Integer.parseInt(token));
                } else if (isOperator(token)) {
                    if (stack.size() < 2) {
                        return null;
                    }

                    int left = stack.pop();
                    int right = stack.pop();

                    Integer result = calculate(left, right, token);

                    if (result == null) {
                        return null;
                    }

                    stack.push(result);
                } else {
                    return null;
                }
            }

            if (stack.size() != 1) {
                return null;
            }

            return stack.pop();

        } catch (NumberFormatException e) {
            return null;
        }
    }

    // PRE: Receives a token.
    // POST: Returns true if the token is an integer.
    private static boolean isInteger(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // PRE: Receives a token.
    // POST: Returns true if the token is a supported operator.
    private static boolean isOperator(String token) {
        return token.equals("+")
            || token.equals("-")
            || token.equals("*")
            || token.equals("/");
    }

    // PRE: Receives two operands and an operator.
    // POST: Returns the result, or null if arithmetic is invalid.
    private static Integer calculate(int left, int right, String operator) {
        try {
            switch (operator) {
                case "+":
                    return Math.addExact(left, right);

                case "-":
                    return Math.subtractExact(left, right);

                case "*":
                    return Math.multiplyExact(left, right);

                case "/":
                    if (right == 0
                        || (left == Integer.MIN_VALUE && right == -1)) {
                        return null;
                    }
                    return left / right;

                default:
                    return null;
            }
        } catch (ArithmeticException e) {
            return null;
        }
    }

    // STACK 4: DECIMAL TO BINARY
    public static void testDecToBinary(PrintWriter out) {
        System.out.println("\nSTACK 4: DECIMAL TO BINARY EVALUATOR");
        out.println("\nSTACK 4: DECIMAL TO BINARY EVALUATOR");

        ArrayList<Integer> numbers = new ArrayList<>(
            Arrays.asList(15, 81, 105, 255, 256, 0, 1)
        );

        // PRE: A list of non-negative decimal numbers is provided.
        // POST: Converts each number to binary and prints the result.
        for (int number : numbers) {
            printResult(out, "The decimal value " + number
                + " is " + decToBinary(number) + " in binary");
        }
    }

    // PRE: Receives a non-negative integer.
    // POST: Returns the binary representation using MyStack.
    private static String decToBinary(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(
                "Number must be non-negative.");
        }

        if (number == 0) {
            return "0";
        }

        MyStack<Integer> stack = new MyStack<>();

        while (number > 0) {
            stack.push(number % 2);
            number /= 2;
        }

        StringBuilder binary = new StringBuilder();

        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }

        return binary.toString();
    }

    // STACK 5: BALANCED PARENTHESES
    public static void testIsBalanced(PrintWriter out) {
        System.out.println(
            "\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");
        out.println(
            "\nSTACK 5: PARENTHESIS IS BALANCED EVALUATOR");

        ArrayList<String> expressions = new ArrayList<>(Arrays.asList(
            "[7 + (4 - 3)] - (3 * 4)",
            "[7 + (4 - 3] - (3 * 4)",
            "4 + 6 - 7",
            "([x + y] + {z * w})",
            "{(1 + [2 * 3]) - 4}",
            "([x + y] + z}"
        ));

        // PRE: A list of expressions is provided.
        // POST: Prints whether each expression has balanced brackets.
        for (String expression : expressions) {
            MyStack<Character> stack = new MyStack<>();
            boolean balanced = true;

            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);

                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else if (c == ')' || c == ']' || c == '}') {
                    if (stack.isEmpty()) {
                        balanced = false;
                        break;
                    }

                    char opening = stack.pop();

                    if ((c == ')' && opening != '(')
                        || (c == ']' && opening != '[')
                        || (c == '}' && opening != '{')) {
                        balanced = false;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()) {
                balanced = false;
            }

            String result = balanced ? "balanced" : "not balanced";

            printResult(out,
                "The expression " + expression + " is " + result);
        }
    }

    // STACK 6: UNDO/REDO
    public static void testUndoRedo(PrintWriter out) {
        System.out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");
        out.println("\nSTACK 6: UNDO REDO FUNCTION EVALUATOR");

        printResult(out,
            "NOTE: D = Do, R = Redo, U = Undo, Q = Quit");
        printResult(out,
            "Input commands on the left and the result is on the right");

        ArrayList<String> commands = new ArrayList<>(Arrays.asList(
            "D add a", "D add b", "D add c", "R", "U", "U",
            "R", "U", "D add d", "U", "U", "U", "R", "F", "Q"
        ));

        MyStack<String> done = new MyStack<>();
        MyStack<String> undone = new MyStack<>();

        // PRE: A list of commands is provided.
        // POST: Processes do, undo, redo, invalid, and quit commands.
        for (String command : commands) {
            if (command.equals("Q")) {
                printResult(out, "program ending");
                break;
            } else if (command.startsWith("D ")) {
                String action = command.substring(2);
                done.push(action);
                undone.clear();
                printResult(out, "performing: " + action);
            } else if (command.equals("U")) {
                if (done.isEmpty()) {
                    printResult(out, "undo: stack is empty");
                } else {
                    String action = done.pop();
                    undone.push(action);
                    printResult(out, "undoing: command: " + action);
                }
            } else if (command.equals("R")) {
                if (undone.isEmpty()) {
                    printResult(out, "redo: stack is empty");
                } else {
                    String action = undone.pop();
                    done.push(action);
                    printResult(out, "redoing: command: " + action);
                }
            } else {
                printResult(out,
                    "invalid command entered: " + command);
            }
        }
    }

    // PRE: Receives a report writer and a message.
    // POST: Prints the message to the console and report file.
    private static void printResult(PrintWriter out, String message) {
        System.out.println(message);
        out.println(message);
    }
}