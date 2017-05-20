import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class WordListTest {

  @Test public void testWordListretrieval(){
     List<String> wl = WordList.retrieve(2);
     assert( wl.size() > 0 );
  }
}
