package dayfourteen;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {

  public static long calculateEx1(List<Instruction> input) {
    Map<String, Long> memory = new HashMap<>();
    for(Instruction instruction : input) {
      for(Map.Entry<String, String> op : instruction.getOperations()) {
        try {
          long value = Long.parseLong(op.getValue());
          memory.put(op.getKey(), calculateValue(value, instruction.getMask()));
        } catch (NumberFormatException nex) {
          System.err.println("I miss in my life (logic)");
        }
      }
    }

    return memory.values().stream().reduce(0L, Long::sum);
  }

  private static long calculateValue(long value, String mask) {
    String binaryValue = Long.toBinaryString(value);
    while(binaryValue.length() < 36) {
      binaryValue = "0".concat(binaryValue);
    }
    String[] maskArray = mask.split(StringUtils.EMPTY);
    String[] valueArray = binaryValue.split(StringUtils.EMPTY);

    for(int i = 0; i < binaryValue.length(); i++) {
      if(!maskArray[i].equals("X")) {
        valueArray[i] = maskArray[i];
      }
    }

    return Long.parseLong(String.join(StringUtils.EMPTY, valueArray), 2);
  }

  public static long calculateEx2(List<Instruction> input) {
    Map<String, Long> memory = new HashMap<>();
    for(Instruction instruction : input) {
      for(Map.Entry<String, String> op : instruction.getOperations()) {
        try {
          long calculatedValue = Long.parseLong(op.getValue());
          calculateMem(op.getKey(), instruction.getMask()).forEach(item -> memory.put(item, calculatedValue));
        } catch (NumberFormatException nex) {
          System.err.println("I miss in my life (logic)");
        }
      }
    }

    return memory.values().stream().reduce(0L, Long::sum);
  }

  private static List<String> calculateMem(String inputMem, String mask) {
    List<String> memoryPos = new ArrayList<>();
    String binaryValue = Long.toBinaryString(Long.parseLong(inputMem));
    while(binaryValue.length() < 36) {
      binaryValue = "0".concat(binaryValue);
    }
    String[] maskArray = mask.split(StringUtils.EMPTY);
    String[] memoryArray = binaryValue.split(StringUtils.EMPTY);

    for(int i = 0; i < binaryValue.length(); i++) {
      if(maskArray[i].equals("X") || maskArray[i].equals("1")) {
        memoryArray[i] = maskArray[i];
      }
    }

    allMemories(String.join(StringUtils.EMPTY, memoryArray), memoryPos);

    return memoryPos;
  }

  private static void allMemories(String value, List<String> memories) {
    if(!value.contains("X")) {
      memories.add(Long.toString(Long.parseLong(value, 2)));
    } else {
      allMemories(value.replaceFirst("X", "1"), memories);
      allMemories(value.replaceFirst("X", "0"), memories);
    }
  }
}
