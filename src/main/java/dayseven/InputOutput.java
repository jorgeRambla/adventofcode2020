package dayseven;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputOutput {

  public static Map<String, List<String>> readInput() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayseven/data");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(InputOutput::readLine)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private static Map.Entry<String, List<String>> readLine(final String input){
    String formattedLine = input
            .replaceAll("bags", "")
            .replaceAll("bag", "")
            .replaceAll("\\.", "")
            .replaceAll(StringUtils.SPACE, StringUtils.EMPTY);

    String mainColor = formattedLine.split("contain")[0];
    String contain = formattedLine.split("contain")[1];

    if(contain.equals("noother")) {
      return new AbstractMap.SimpleEntry<>(mainColor, new ArrayList<>());
    } else {
      return new AbstractMap.SimpleEntry<>(mainColor, new ArrayList<>(Arrays.asList(contain.split(","))));
    }
  }
}
