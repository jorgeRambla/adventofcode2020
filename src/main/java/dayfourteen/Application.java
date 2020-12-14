package dayfourteen;

public class Application {
  public static void main(final String... args) {
    long result = Logic.calculateEx1(InputOutput.readInputEx1());
    System.out.println(String.format("The result of ex1 is: %s", result));

    result = Logic.calculateEx2(InputOutput.readInputEx1());
    System.out.println(String.format("The result of ex2 is: %s", result));
  }
}
