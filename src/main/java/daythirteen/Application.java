package daythirteen;

public class Application {
  public static void main(final String... args) {
    long resultEx1 = Logic.calculateEx1(InputOutput.readInputEx1());
    System.out.println(String.format("The ex1 result is: %s", resultEx1));
  }
}
