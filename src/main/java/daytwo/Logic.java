package daytwo;


import java.util.List;

public class Logic {

  public static long calculateEx1(List<Data> input) {
    return input.stream().filter(Logic::isValidEx1).count();
  }

  private static boolean isValidEx1(Data input) {
    long count = input.getPassword().chars().filter(ch -> ch == input.getLetter().charAt(0)).count();

    return count >= input.getMin() && count <= input.getMax();
  }

  public static long calculateEx2(List<Data> input) {
    return input.stream().filter(Logic::isValidEx2).count();
  }

  private static boolean isValidEx2(Data input) {
    String password = input.getPassword();
    char letter = input.getLetter().charAt(0);

    if (containsLetterInPos((int) input.getMin(), password, letter)) {
      return !containsLetterInPos((int) input.getMax(), password, letter);
    } else {
      return containsLetterInPos((int) input.getMax(), password, letter);
    }
  }

  private static boolean containsLetterInPos(int pos, String input, char letter) {
    return input.length() >= pos && input.charAt(pos - 1) == letter;
  }
}
