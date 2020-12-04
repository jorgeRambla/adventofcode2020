package dayone;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class InputOutput {

  public static List<Long> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayone/ex1");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(Long::parseLong)
            .collect(Collectors.toList());
  }
}
