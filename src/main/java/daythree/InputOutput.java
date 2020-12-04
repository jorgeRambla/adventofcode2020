package daythree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputOutput {
public enum Type {
  SQUARE,
  TREE
}

  public static List<List<Type>> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/daythree/ex1");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(InputOutput::transform)
            .collect(Collectors.toList());
  }

  private static List<Type> transform(String input) {
    return Arrays.stream(input.split(""))
            .map(InputOutput::getType)
            .collect(Collectors.toList());

  }

  private static Type getType(String input) {
    return input.equals(".") ? Type.SQUARE : Type.TREE;
  }
}