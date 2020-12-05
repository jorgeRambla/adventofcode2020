package dayfive;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputOutput {

  public enum Site {
    FRONT,
    BACK,
    LEFT,
    RIGHT
  }

  public static List<List<Site>> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayfive/data");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(InputOutput::readLine)
            .collect(Collectors.toList());
  }

  public static List<Site> readLine(final String input){
    return Arrays.stream(input.split(StringUtils.EMPTY))
            .map(InputOutput::getSite)
            .collect(Collectors.toList());
  }

  public static Site getSite(final String input) {
    switch (input) {
      case "F":
        return Site.FRONT;
      case "B":
        return Site.BACK;
      case "R":
        return Site.RIGHT;
      case "L":
        return Site.LEFT;
      default:
        System.err.println(String.format("I cannot read input: %s", input));
        return null;
    }
  }
}
