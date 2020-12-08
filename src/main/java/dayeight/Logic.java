package dayeight;


import java.security.InvalidParameterException;
import java.util.List;

public class Logic {

  public static long calculateAccEx1(List<InstructionSet> input) {
    long acc = 0;
    int i = 0;
    while(i < input.size() && !input.get(i).isVisited()) {
      if (input.get(i).getInstruction().equals(InstructionSet.Instruction.NOP)) {
        input.get(i).setVisited(true);
        i += 1;
      } else if (input.get(i).getInstruction().equals(InstructionSet.Instruction.JMP)) {
        input.get(i).setVisited(true);
        if (i + input.get(i).getValue() < 0) {
          System.err.println("I miss in my life - point is below index 0");
          throw new InvalidParameterException();
        }
        i += input.get(i).getValue();
      } else if (input.get(i).getInstruction().equals(InstructionSet.Instruction.ACC)) {
        input.get(i).setVisited(true);
        acc += input.get(i).getValue();
        i += 1;
      } else {
        System.err.println(String.format("I miss in my life - i found %s", input.get(i).getInstruction()));
      }
    }
    return acc;
  }

  public static long calculateAccEx2(List<InstructionSet> input) {
    long acc = 0;
    int i = 0;
    while(i < input.size() && !input.get(i).isVisited()) {
      List<InstructionSet> newList = InputOutput.readInputEx1();
      if (input.get(i).getInstruction().equals(InstructionSet.Instruction.NOP)) {
        newList.get(i).setInstruction(InstructionSet.Instruction.JMP);
        if(finish(newList)) {
          newList.forEach(item -> item.setVisited(false));
          return calculateAccEx1(newList);
        }
        input.get(i).setVisited(true);
        i += 1;
      } else if (input.get(i).getInstruction().equals(InstructionSet.Instruction.JMP)) {
        newList.get(i).setInstruction(InstructionSet.Instruction.NOP);
        if(finish(newList)) {
          newList.forEach(item -> item.setVisited(false));
          return calculateAccEx1(newList);
        }
        input.get(i).setVisited(true);
        if (i + input.get(i).getValue() < 0) {
          System.err.println("I miss in my life - point is below index 0");
          throw new InvalidParameterException();
        }
        i += input.get(i).getValue();
      } else if (input.get(i).getInstruction().equals(InstructionSet.Instruction.ACC)) {
        input.get(i).setVisited(true);
        acc += input.get(i).getValue();
        i += 1;
      } else {
        System.err.println(String.format("I miss in my life - i found %s", input.get(i).getInstruction()));
      }
    }
    return acc;
  }

  public static boolean finish(List<InstructionSet> input) {
    int index = 0;
    while (index < input.size() && !input.get(index).isVisited()) {
      switch (input.get(index).getInstruction()) {
        case NOP:
          input.get(index).setVisited(true);
          index += 1;
          break;
        case JMP:
          input.get(index).setVisited(true);
          index += input.get(index).getValue();
          break;
        case ACC:
          input.get(index).setVisited(true);
          index += 1;
          break;
        default:
          System.err.println(String.format("I miss in my life - i found %s", input.get(index).getInstruction()));
          throw new InvalidParameterException();
      }
    }

    return index >= input.size();
  }
}
