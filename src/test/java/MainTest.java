import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        CardTest.class,
        CardDeckTest.class,
        PlayerTest.class,
        PackReaderTest.class,
        CardGameTest.class
})
public class MainTest {
    // No main method needed; Surefire will run this suite.
}