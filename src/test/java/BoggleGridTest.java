import org.junit.Test;
import static org.junit.Assert.*;

public class BoggleGridTest {
  
  @Test public void testFindCell() {
     char[][] grid = {{'A','B','C','D','E'}, 
      {'F','G','H','I','J'},
      {'K','L','M','N','O'},
      {'P','Q','R','S','T'},
      {'V','W','X','Y','Z'}};

     BoggleGrid bg = new  BoggleGrid( grid );
     assert( true == bg.find( "ZYXWVPQUMRNSTOJIHGFABCDE" ) );
     assert( false == bg.find( "ZYXWVPQMRNSTOJIHGFABCDE" ) );
  }
}
