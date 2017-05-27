import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WordListTest {

  @Test public void testWordListretrieval(){
     List<String> wl = WordList.retrieve(2);
     assert( wl.size() > 0 );
  }

  /* Somewhat more fragile of a test */ 
  @Test public void testSomeWords() {
    int minWordLength = 3;
    List<String> list = WordList.retrieve( minWordLength );

     assert( "AACHEN".equals(list.get(0)) );
     assert( "AGATHA".equals(list.get(100)) );
     assert( "BERNSTEIN".equals(list.get(1000)) );
  }
}
