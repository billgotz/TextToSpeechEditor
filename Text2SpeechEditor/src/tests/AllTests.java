package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EditDocumentTest.class, NewDocumentTest.class, OpenDocumentTest.class, SaveDocumentTest.class,
		TransformDocumentTests.class, TransformLineTests.class, TuneAudioTest.class, TuneEncodingStrategyTest.class })
public class AllTests {

}
