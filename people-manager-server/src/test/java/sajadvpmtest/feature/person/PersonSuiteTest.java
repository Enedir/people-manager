package sajadvpmtest.feature.person;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonTest.class,
        PersonServiceTest.class,
})
public class PersonSuiteTest {
}
