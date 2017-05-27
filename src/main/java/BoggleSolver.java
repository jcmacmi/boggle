import java.util.Random;

import java.util.List;

import java.util.HashMap;

import java.util.stream.Collectors;

public class BoggleSolver {

  static final int GridLength = 5;
  static final int GL = GridLength;
  static final int MinWordLength = 3;
 

  // Want to make this work, 8+ = 11 points.
  //static int[][] LenghtToPoints = new [][] { {3,1}, {4,1}, {5,2}, {6,3} {7,5}, {8,11} };  

  public static void main( String[] args ){
    
    int i = 0, j;
    String arg;
    char flag;
    boolean vflag = false;
    String outputfile = "";

    char boggleGrid[][];

    while( i < args.length && args[i].startsWith("-")) {
     arg = args[i++];
    
     if (arg.equals("-generate")) {
       boggleGrid = BoggleGrid.generateRandomBoggle( 50 );
       BoggleGrid.renderBoggle( boggleGrid );
     }

    }

     List<String> list = WordList.retrieve( MinWordLength );

     /*
     char boggleGrid[][] = BoggleGrid.generateRandomBoggle( 50 );
     boggleGrid[0][2] = 'Q';
     BoggleGrid.renderBoggle( boggleGrid );
   
     BoggleGrid bg = new BoggleGrid( boggleGrid );

     long startTime = System.nanoTime();
     List<String> wordsInGrid = list.stream().filter( w -> bg.search(w) ).collect(Collectors.toList());
     long endTime = System.nanoTime();

     System.out.println("It took: " + (endTime-startTime)/1000000);
     
     startTime = System.nanoTime();
     List<String> exactWordsInGrid = list.stream().filter( w -> bg.find(w) ).collect(Collectors.toList());
     endTime = System.nanoTime();

     System.out.println("It took: " + (endTime-startTime)/1000000);

     startTime = System.nanoTime();
     List<String> exactWordsInGrid2 = wordsInGrid.stream().filter( w -> bg.find(w) ).collect(Collectors.toList());
     endTime = System.nanoTime();

     System.out.println("It took: " + (endTime-startTime)/1000000);

     System.out.println("wordsInGrid : 0 : " + wordsInGrid.get(0) );
     System.out.println("wordsInGrid : 1 : " + wordsInGrid.get(1) );
     System.out.println("wordsInGrid : 2 : " + wordsInGrid.get(2) );

     System.out.println("Size of orignal WordList : " + list.size() );
     System.out.println("Size of wordsInGrid : " + wordsInGrid.size() );
     System.out.println("Size of exactWordsInGrid: " + exactWordsInGrid.size() );
     System.out.println("Size of exactWordsInGrid2: " + exactWordsInGrid2.size() );

     exactWordsInGrid.forEach( word->System.out.println( word ) );

     */
  }
}
