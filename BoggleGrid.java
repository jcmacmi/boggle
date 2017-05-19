import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class BoggleGrid {

  static final int GridLength = 5;
  static final int GL = GridLength;

  private char _boggleGrid[][];
  private char _boggleLetters[];

  public BoggleGrid(){
    _boggleGrid = new char[GL][GL];
    _boggleLetters = new char[GL * GL];
  }

  public BoggleGrid( char[][] bg){
    _boggleGrid = new char[GL][GL];
    _boggleLetters = new char[GL * GL];
 
   for( int i=0; i< GL; i++ ) {
     for( int j=0; j<GL; j++ ) {
       _boggleGrid[i][j] = bg[i][j];
       _boggleLetters[i+5*j] = bg[i][j];
     }
   }

   Arrays.sort(_boggleLetters);
  }

  public static char[][] generateRandomBoggle() {
     String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
     int alphabetLength = alphabet.length();

     Random random = new Random(50);

     char boggleGrid[][] = new char[GL][GL];

     for( int i=0; i<GL; i++ ) {
       for( int j=0; j<GL; j++ ) {
         boggleGrid[i][j] =  alphabet.charAt( random.nextInt( alphabetLength ) );
       }
     }
     
     return boggleGrid;
  }

  public static void renderBoggle( char[][] boggleGrid ) {

     for( int i=0; i<GL; i++ ) {
       for( int j=0; j<GL; j++ ) {
         System.out.print( boggleGrid[i][j] );
       }
       System.out.println();
     }

  }

  /* At least make sure we have all the letters in the word */
  public boolean search( String word ) {

   char [] wordChars = word.toCharArray();
   Arrays.sort(wordChars);  
   
   int j=0;
   for( int i = 0 ; i < word.length(); i++ ) {
     while( j!=GL*GL && _boggleLetters[j] != wordChars[i] ){
      j++;
     }
     if( j == GL*GL ) {
      return false;
     }
   }
   return true;
  }

  public boolean find( String word ) {
    for ( int i = 0 ; i < GL; i++ ) {
      for (int j = 0 ; j < GL; j++ ) {
        boolean[][] location = new boolean[GL][GL];
        if( matchCell( i, j , word, 0, location) ) { return true; }
      }
    }
    return false;
  }
  
  // Need to fix that it takes accout of the fact QU occurs on the same tile
  // The change for location check slows this down by a lot, it might be worth
  // doing two passes, without then with the location check.
  private boolean matchCell( int i, int j, String word, int position, boolean location[][] ) {
    if ( i < 0 || j < 0 || i ==GL || j == GL || location[i][j] || position==word.length() ) { return false; }

    if( word.charAt(position) == _boggleGrid[i][j]) {

     // Handle the QU situation
     if( word.charAt(position) == 'Q' ){
       if (position+1 < word.length() && word.charAt(position+1) == 'U' ){ position++; }
       else { return false; }
     }

     if( position+1 == word.length() ) {
       return true; // match found!
     }
     else {
       location[i][j] = true;
       position++;
       if ( matchCell( i-1, j-1, word, position, location ) ) { return true; } 
       else if ( matchCell( i-1, j  , word, position, location ) ) { return true; }
       else if ( matchCell( i-1, j+1, word, position, location ) ) { return true; }
       else if ( matchCell( i  , j-1, word, position, location ) ) { return true; }
       else if ( matchCell( i  , j+1, word, position, location ) ) { return true; }
       else if ( matchCell( i+1, j-1, word, position, location ) ) { return true; }
       else if ( matchCell( i+1, j  , word, position, location ) ) { return true; }
       else if ( matchCell( i+1, j+1, word, position, location ) ) { return true; }
     }
   }
   location[i][j] = false;
   return false;
   
  }
  
}
