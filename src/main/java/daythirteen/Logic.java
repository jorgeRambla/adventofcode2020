package daythirteen;

public class Logic {

  public static long calculateEx1(InputData inputData) {
    long currentDiff = Long.MAX_VALUE;
    long currentResult = 0;
    for(Long line : inputData.getLines()) {
      long currentLine = 0;
      while(currentLine < inputData.getTimestamp()) {
        currentLine += line;
      }

      if (currentLine < currentDiff) {
        currentDiff = currentLine;
        currentResult = (currentDiff - inputData.getTimestamp()) * line;
      }
    }

    return currentResult;
  }
}
