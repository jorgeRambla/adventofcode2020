package daytwo;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputOutput {

  private static final String REGEX = "(?<min>\\d*)-(?<max>\\d*)\\s(?<letter>.{1}):\\s(?<password>.*)";

  public static List<Data> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/daytwo/ex1");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(InputOutput::transform)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
  }

  private static Optional<Data> transform(String input) {
    Matcher matcher = Pattern.compile(REGEX).matcher(input);
    if(matcher.find()) {
      return Optional.of(
              new Data(
                      Long.parseLong(matcher.group("min")),
                      Long.parseLong(matcher.group("max")),
                      matcher.group("letter"),
                      matcher.group("password")));
    }
    return Optional.empty();
  }
}

@lombok.Data
@AllArgsConstructor
class Data {
  private long min;
  private long max;
  private String letter;
  private String password;
}
