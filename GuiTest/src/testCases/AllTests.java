package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ShowSelectionTests.class,
	ShowItemsTests.class,
	SlideEditorTests.class,
	SlideTextTests.class,
	MovieEditorTests.class
})
public class AllTests {

}
