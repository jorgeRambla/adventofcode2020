package daytwo;

public class Application {
  public static void main(final String... args) {
    long result = Logic.calculateEx1(InputOutput.readInputEx1());
    System.out.println(String.format("There are %s valid passwords in data", result));

    result = Logic.calculateEx2(InputOutput.readInputEx1());
    System.out.println(String.format("There are %s valid passwords in ex2", result));
  }
}
