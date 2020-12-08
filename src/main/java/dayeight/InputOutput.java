package dayeight;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputOutput {

  private static final String REGEX = "(?<instruction>acc|jmp|nop) (?<value>(\\+|-)\\d+)";

  public static List<InstructionSet> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/dayeight/data");
    return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines()
            .map(InputOutput::readLine)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
  }

  private static Optional<InstructionSet> readLine(final String input){
    Pattern pattern = Pattern.compile(REGEX);
    Matcher matcher = pattern.matcher(input);
    if(matcher.matches()) {
      return Optional.of(getInstruction(matcher.group("instruction"), matcher.group("value")));
    } else {
      return Optional.empty();
    }
  }

  private static InstructionSet getInstruction(final String instruction, String value) {
    InstructionSet.Instruction instructionBuilder;
    long valueBuilder;
    try {
      valueBuilder = Long.parseLong(value);
    } catch (NumberFormatException nex) {
      valueBuilder = 0;
    }
    switch (instruction) {
      case "acc":
        instructionBuilder = InstructionSet.Instruction.ACC;
        break;
      case "jmp":
        instructionBuilder = InstructionSet.Instruction.JMP;
        break;
      case "nop":
        instructionBuilder = InstructionSet.Instruction.NOP;
        break;
      default:
        instructionBuilder = InstructionSet.Instruction.NO_VALID;
        break;
    }
    return new InstructionSet(instructionBuilder, valueBuilder, false);
  }

}

@Data
@AllArgsConstructor
class InstructionSet {
  public enum Instruction  {
    NOP,
    ACC,
    JMP,
    NO_VALID
  }

  private Instruction instruction;

  private long value;

  private boolean visited;

}
