package dayfour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputOutput {

  public static List<Passport> readInputEx2() {
    return readInputEx1().stream()
            .map(item -> new Passport(
                    Arrays.stream(item.split(StringUtils.SPACE))
                            .filter(second -> !second.equals(StringUtils.EMPTY))
                            .map(second -> second.split(":"))
                            .filter(second -> second.length >= 2)
                            .map(second -> new AbstractMap.SimpleEntry<>(second[0], second[1]))
                            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue))
            ))
            .collect(Collectors.toList());
  }

  public static List<String> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayfour/ex1");
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    String lines = StringUtils.EMPTY;
    String line;
    try {
      while ((line = br.readLine()) != null) {
        if(line.equals(StringUtils.EMPTY)) {
          lines = lines.concat(StringUtils.LF);
        } else {
          lines = lines.concat(line).concat(StringUtils.SPACE);
        }
      }
    } catch (IOException e) {
      System.err.println("I miss in my life");
    }
      return Arrays.stream(lines.split("\n"))
            .map(item -> item.split(" "))
            .filter(item -> item.length >= 7)
            .filter(item -> item.length <= 8)
            .map(item -> String.join(StringUtils.SPACE, item))
            .filter(item -> item.contains(Passport.BYR))
            .filter(item -> item.contains(Passport.IYR))
            .filter(item -> item.contains(Passport.EYR))
            .filter(item -> item.contains(Passport.HGT))
            .filter(item -> item.contains(Passport.HCL))
            .filter(item -> item.contains(Passport.ECL))
            .filter(item -> item.contains(Passport.PID))
            .collect(Collectors.toList());
  }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Passport {
  public static final String BYR = "byr"; //birth year REQ
  public static final String IYR = "iyr"; //issue year REQ
  public static final String EYR = "eyr"; //expiration year REQ
  public static final String HGT = "hgt"; //height REQ
  public static final String HCL = "hcl"; //hair color REQ
  public static final String ECL = "ecl"; //hair color REQ
  public static final String PID = "pid"; //passport id REQ
  public static final String CID = "cid"; //country id NO REQ

  private static final String HEX_WEBCOLOR_PATTERN
          = "^#([a-fA-F0-9]{6})$";

  private static final String HEIGHT_PATTERN
          = "^\\d*(cm|in)$";

  private static final String PID_PATTERN
          = "^\\d{9}$";

  private static final String[] VALID_ECL = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};

  private Map<String, String> data;

  public boolean isValid() {
    return data.size() >= 7 &&
            !StringUtils.isEmpty(data.get(BYR)) && isNumericValid(data.get(BYR)) && valueBetween(Long.parseLong(data.get(BYR)), 1920, 2002) &&
            !StringUtils.isEmpty(data.get(IYR)) && isNumericValid(data.get(IYR)) && valueBetween(Long.parseLong(data.get(IYR)), 2010, 2020) &&
            !StringUtils.isEmpty(data.get(EYR)) && isNumericValid(data.get(EYR)) && valueBetween(Long.parseLong(data.get(EYR)), 2020, 2030) &&
            !StringUtils.isEmpty(data.get(HGT)) && isHeightValid(data.get(HGT)) &&
            !StringUtils.isEmpty(data.get(HCL)) && isColorValid(data.get(HCL)) &&
            !StringUtils.isEmpty(data.get(ECL)) && data.get(ECL).length() == 3 && isColorEyeValid(data.get(ECL)) &&
            !StringUtils.isEmpty(data.get(PID)) && isNumericValid(data.get(PID)) && isPidValid(data.get(PID));
  }

  private boolean isColorValid(final String colorCode) {
    Pattern pattern = Pattern.compile(HEX_WEBCOLOR_PATTERN);
    Matcher matcher = pattern.matcher(colorCode);
    return matcher.matches();
  }

  private boolean isNumericValid(String input) {
    try {
      Long.parseLong(input);
      return true;
    } catch (NumberFormatException nex) {
      return false;
    }
  }

  private boolean valueBetween(long value, long from, long to) {
    return value >= from && value <= to;
  }

  private boolean isHeightValid(String input) {
    Pattern pattern = Pattern.compile(HEIGHT_PATTERN);
    Matcher matcher = pattern.matcher(input);
    try {
      if(matcher.matches()) {
        String unit = input.substring(Math.max(input.length() - 2, 0));
        if(unit.equals("cm")) {
          long height = Long.parseLong(input.replace("cm", StringUtils.EMPTY));
          return valueBetween(height, 150,193);
        } else if (unit.equals("in")){
          long height = Long.parseLong(input.replace("in", StringUtils.EMPTY));
          return valueBetween(height, 59, 76);
        }
      }
      return false;
    } catch (NumberFormatException nex) {
      return false;
    }
  }

  private boolean isColorEyeValid(String colorEye) {
    return new ArrayList<>(Arrays.asList(VALID_ECL)).contains(colorEye);
  }

  private boolean isPidValid(String pid) {
    Pattern pattern = Pattern.compile(PID_PATTERN);
    Matcher matcher = pattern.matcher(pid);
    return matcher.matches();
  }
}