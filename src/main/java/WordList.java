import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Retrieve a wordList converted to uppercase and excluding any words that contain anything other
   than letters, and those 3 letters or shorted from the system dictionary */
 
public class WordList {

  static String dictionary = "/usr/share/dict/words";

  public static List<String> retrieve( int minWordLength ){

     // Filter out words with punctation and shorter than 4 letters
     Pattern wordValidator = Pattern.compile("^[a-zA-Z]+");
     Predicate<String> wordFilter = 
	w -> w.length() > minWordLength && wordValidator.matcher(w).matches();
   
     return readDictionary(dictionary, wordFilter, w -> w.toUpperCase() );
  }

  private static List<String> readDictionary(
    String d, Predicate<String> filterCriteria, UnaryOperator<String> mapWords ){

    List<String> list = new ArrayList<>();

    try (BufferedReader br = Files.newBufferedReader(Paths.get(d))) {
      //br returns as stream and convert it into a List
      list = br.lines().filter(filterCriteria).map(mapWords).collect(Collectors.toList());

     } catch (IOException e) {
      e.printStackTrace();
     }
   
     return list;
  }
}
