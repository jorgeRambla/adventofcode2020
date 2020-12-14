package daythirteen;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InputOutput {

  public static InputData readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/daythirteen/data");
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    String line;
    InputData inputData = new InputData();
    try {
      if ((line = br.readLine()) != null) {
        inputData.setTimestamp(Long.parseLong(line));
      }
      List<Long> lines = new ArrayList<>();
      if ((line = br.readLine()) != null) {
        setLines(line, lines);
      }
      inputData.setLines(lines);
    } catch (IOException iex) {
      System.err.println("I miss reading");
    } catch (NumberFormatException nex) {
      System.err.println("I miss parsing to long");
    }

    return inputData;
  }

  private static void setLines(String input, List<Long> lines) {
    for(String line : input.split(",")) {
      try {
        lines.add(Long.parseLong(line));
      } catch (NumberFormatException nex) {
        // Empty block
      }
    }
  }
}

@Data
class InputData {
  private long timestamp;
  private List<Long> lines;
}
