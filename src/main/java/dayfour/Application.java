package dayfour;

public class Application {
  public static void main(final String... args) {
    long result = InputOutput.readInputEx1().stream().count();
    System.out.println(String.format("There are %s valid passports in Ex1", result));

    result = InputOutput.readInputEx2().stream().filter(Passport::isValid).count();
    System.out.println(String.format("There are %s valid passports in Ex2", result));

  }
}
