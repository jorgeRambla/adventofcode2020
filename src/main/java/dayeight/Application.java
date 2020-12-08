package dayeight;

public class Application {
  public static void main(final String... args) {
    long result = Logic.calculateAccEx1(InputOutput.readInputEx1());
    System.out.println(String.format("The result of acc is: %s", result));

    result = Logic.calculateAccEx2(InputOutput.readInputEx1());
    System.out.println(String.format("The result of acc is: %s", result));
  }
}
