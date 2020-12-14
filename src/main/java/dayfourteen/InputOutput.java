package dayfourteen;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputOutput {

  private static final String REGEX = "^mem\\[(?<mem>\\d+)\\] = (?<value>\\d+)$";

  public static List<Instruction> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayfourteen/data");
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    String line;
    Instruction instruction = new Instruction();
    List<Instruction> instructions = new ArrayList<>();
    try {
      if((line = br.readLine()) != null) {
        instruction.setMask(line.replace("mask = ", StringUtils.EMPTY));
      }
      while ((line = br.readLine()) != null) {
        if (line.startsWith("mask = ")) {
          instructions.add(instruction);
          instruction = new Instruction();
          instruction.setMask(line.replace("mask = ", StringUtils.EMPTY));
        } else {
          Optional<Map.Entry<String, String>> entry = getMemData(line);
          if(entry.isPresent()) {
            instruction.getOperations().add(entry.get());
          }
        }
      }
      instructions.add(instruction);
    } catch (IOException iex) {
      System.err.println("I miss in my life");
    }
    return instructions;
  }

  private static Optional<Map.Entry<String, String>> getMemData(String input) {
    Pattern pattern = Pattern.compile(REGEX);
    Matcher matcher = pattern.matcher(input);
    if(matcher.matches()) {
      return Optional.of(new AbstractMap.SimpleImmutableEntry<>(matcher.group("mem"), matcher.group("value")));
    }

    return Optional.empty();
  }
}

@Data
class Instruction {
  private String mask;
  private List<Map.Entry<String, String>> operations = new ArrayList<>();
}
