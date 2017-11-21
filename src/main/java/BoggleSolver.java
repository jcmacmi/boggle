package boggle;

import java.util.Random;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

public class BoggleSolver {

  static final int GridLength = 5;
  static final int GL = GridLength;
  static final int MinWordLength = 3;
 
  public static void main( String[] args ){
    
    int i = 0, j;

    char boggleGrid[][];
    BoggleGrid bg;

    if ( args.length == 0 ) {
       System.err.println("No arguments supplied, generating grid...");
       boggleGrid = BoggleGrid.generateRandomBoggle( 50 );
       BoggleGrid.renderBoggle( boggleGrid );
       bg = new BoggleGrid( boggleGrid );
    }
    else {
       System.err.println("Treating first argument as a file...");
       try {
         List<String> lines = Files.readAllLines(Paths.get( args[0] ) );
         bg = new BoggleGrid( lines );
       }
       catch (IOException e) {
        System.err.println("Failed to read file : " + args[0] );
        return;
       } 
    }

    List<String> list = WordList.retrieve( MinWordLength );
    List<String> result = solveGrid( list, bg );

    result.forEach( w->System.err.println( w ) );
    Integer count = result.stream().mapToInt(BoggleSolver::scoreWord).sum();
    System.err.format( "Total score is %d\n", count );
  }

  public static List<String> solveGrid( List<String> list, BoggleGrid bg  ){

    long startTime = System.nanoTime();
    List<String> wordsInGrid = list.stream().filter( w -> bg.search(w) ).collect(Collectors.toList());
    long endTime = System.nanoTime();

    //System.err.println("It took: " + (endTime-startTime)/1000000);
     
    startTime = System.nanoTime();
    List<String> exactWordsInGrid = list.stream().filter( w -> bg.find(w) ).collect(Collectors.toList());
    endTime = System.nanoTime();

    //System.err.println("It took: " + (endTime-startTime)/1000000);

    startTime = System.nanoTime();
    List<String> exactWordsInGrid2 = wordsInGrid.stream().filter( w -> bg.find(w) ).collect(Collectors.toList());
    endTime = System.nanoTime();

    //System.err.println("It took: " + (endTime-startTime)/1000000);

    //System.err.println("Size of orignal WordList : " + list.size() );
    //System.err.println("Size of wordsInGrid : " + wordsInGrid.size() );

    //System.err.println("Size of exactWordsInGrid: " + exactWordsInGrid.size() );
    //System.err.println("Size of exactWordsInGrid2: " + exactWordsInGrid2.size() );

    return exactWordsInGrid;
  }

  public static Integer scoreWord(String w) {
    switch ( w.length() ) {
      case 0: return 0;
      case 1: return 0;
      case 2: return 0;
      case 3: return 1;
      case 4: return 1;
      case 5: return 2;
      case 6: return 3;
      case 7: return 5;
      case 8: return 11;
    }
    return 11;
  }
}
