package daythree;

public class Application {
  public static void main(final String... args) {
    long result = Logic.calculateEx1(InputOutput.readInputEx1(), 3, 1);
    System.out.println(String.format("You hit %s trees in data", result));

    long ex2result = 0;
    long oneOne = Logic.calculateEx1(InputOutput.readInputEx1(),1,1);
    long threeOne = Logic.calculateEx1(InputOutput.readInputEx1(), 3, 1);
    long fiveOne= Logic.calculateEx1(InputOutput.readInputEx1(),5,1);
    long sevenOne = Logic.calculateEx1(InputOutput.readInputEx1(),7,1);
    long oneTwo = Logic.calculateEx1(InputOutput.readInputEx1(),1,2);
    ex2result = oneOne * threeOne * fiveOne * sevenOne * oneTwo;
    System.out.println(String.format("R:1,D:1[%s], R:3,D:1[%s], R:5,D:1[%s], R:7,D:1[%s], R:1,D:2[%s]", oneOne, threeOne, fiveOne, sevenOne, oneTwo));

    System.out.println(String.format("Result in ex 2 is: %s", ex2result));
  }
}
