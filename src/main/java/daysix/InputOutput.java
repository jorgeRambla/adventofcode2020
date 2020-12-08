package daysix;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InputOutput {

  public static List<Set<String>> readInputEx1() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/daysix/data");
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    List<Set<String>> groupsLetters = new ArrayList<>();
    Set<String> letters = new HashSet<>();
    String line;
    try {
      while ((line = br.readLine()) != null) {
        if(line.equals(StringUtils.EMPTY)) {
          groupsLetters.add(letters);
          letters = new HashSet<>();
        } else {
          letters.addAll(Arrays.asList(line.split(StringUtils.EMPTY)));
        }
      }
      groupsLetters.add(letters);
    } catch (IOException e) {
      System.err.println("I miss in my life");
    }
    return groupsLetters;
  }

  public static List<Set<String>> readInputEx2() {
    InputStream inputStream = InputOutput.class.getResourceAsStream("/daysix/data");
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    List<Set<String>> groupsLetters = new ArrayList<>();
    Map<String, Long> letters = new HashMap<>();
    long lines = 0;
    String line;
    try {
      while ((line = br.readLine()) != null) {
        if(line.equals(StringUtils.EMPTY)) {
          long finalLines = lines;
          groupsLetters.add(letters.entrySet().stream().filter(entry -> entry.getValue() == finalLines).map(Map.Entry::getKey).collect(Collectors.toSet()));
          lines = 0;
          letters = new HashMap<>();
        } else {
          lines += 1;
          for( String letter: line.split(StringUtils.EMPTY)) {
           if(letters.containsKey(letter)) {
             letters.put(letter, letters.get(letter) + 1);
           } else {
             letters.put(letter, 1L);
           }
          }
        }
      }
      long finalLines = lines;
      groupsLetters.add(letters.entrySet().stream().filter(entry -> entry.getValue() == finalLines).map(Map.Entry::getKey).collect(Collectors.toSet()));
    } catch (IOException e) {
      System.err.println("I miss in my life");
    }
    return groupsLetters;
  }
}