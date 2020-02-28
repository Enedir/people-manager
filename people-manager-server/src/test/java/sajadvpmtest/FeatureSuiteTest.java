package sajadvpmtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sajadvpmtest.feature.person.PersonSuiteTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonSuiteTest.class,
})
public class FeatureSuiteTest {
}
