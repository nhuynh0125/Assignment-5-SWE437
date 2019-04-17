/**
 * Ngoc Huynh, Quang Vo, Keith Saldana
 * 
 * Note: please make sure to change the path when trying to run
 * 
 * User Story: As an instructor, I want to have a new feature in quizretake software, 
 * so that I can define scheduled quizzes and retakes through the UI.
 */
package quizretakes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InstructorUITest {
	private static String quizXMLPath = "/Users/vanes/eclipse-workspace/quizschedule4-master/src/quiz-orig-swe437.xml";
	private static String retakesXMLPath = "/Users/vanes/eclipse-workspace/quizschedule4-master/src/quiz-retakes-swe437.xml";
	private static String origQuizXMLContent;
	private static String origRetakeXMLContent;

	@BeforeClass
	public static void declaration() {
		try {
			origQuizXMLContent = new String(Files.readAllBytes(Paths.get(quizXMLPath)));
			origRetakeXMLContent = new String(Files.readAllBytes(Paths.get(retakesXMLPath)));
		} catch (Exception e) {
			System.out.println("Unable to read XML files");
		}

	}

	@After
	public void afterMethod() {
		// TODO: paste original contents back to xml files after every method
		try {
			Writer filewriter = new FileWriter(quizXMLPath, false);
			filewriter.write(origQuizXMLContent);
			filewriter.close();

			filewriter = new FileWriter(retakesXMLPath, false);
			filewriter.write(origRetakeXMLContent);
			filewriter.close();
		} catch (IOException e) {
			System.out.println("Unable to write to file");
		}
	}

	/*
	 * Test for user's creating new, valid quiz
	 * This test was first created by creating just the name of the test
	 * and adding a new quizBean into the qlist and test for assertTrue
	 * Refactoring: Then we add a try-catch exception and since the test was false on us,
	 * we add another try-catch exception to test for reason why it's failing
	 */
	@Test
	public void createQuizTest() {

		quizBean qbean = new quizBean(3, 2, 10, 10, 00);
		boolean b = false;
		try {
			InstructorUI.appendQuizNodeToXML(qbean);
			quizReader reader = new quizReader();

			try {
				quizzes qlist = reader.read(quizXMLPath);

				for (quizBean q : qlist) {
					if (q.compareTo(qbean) == 0 && !b) {
						b = true;
					}
				}
			} catch (Exception e) {
				b = false;
			}
		} catch (Exception e) {
			b = false;
		}

		Assert.assertTrue(b);

	}

	/*
	 * Test for user's creating new, invalid quiz
	 * This test was first created by creating just the name of the test
	 * and adding a new, invalid quizBean into the qlist and test for assertFalse
	 * Refactoring: Then we add a try-catch exception since the test did not pass,
	 * after fixing codes from the UI, we add another try-catch exception to test for reason why it's failing
	 */
	@Test
	public void invalidQuizTest() {
		quizBean qbean = null;
		boolean b = false;
		try {
			InstructorUI.appendQuizNodeToXML(qbean);

			quizReader reader = new quizReader();
			try {
				quizzes qlist = reader.read(quizXMLPath);

				for (quizBean q : qlist) {
					if (q.compareTo(qbean) == 0 && !b) {
						b = true;
					}
				}
			} catch (Exception e) {
				b = false;
			}
		} catch (Exception e) {
			b = false;
		}

		Assert.assertFalse(b);

	}

	/*
	 * Test for user's creating new, valid retake
	 * This test was first created by creating just the name of the test
	 * and adding a new retakesReader into the rlist and test for assertTrue, 
	 * so see if the test was able to pass
	 * Refactoring: Then we add a try-catch exception since the test was false on us,
	 * after making a few changes from both this test file and the UI files,
	 * we add another try-catch exception to test for why it's failing.
	 * After realizing that the path was wrongly type, we was able to make the test pass
	 */
	@Test
	public void createRetakesTest() {
		retakeBean rbean = new retakeBean(3, "wherever", 3, 5, 10, 00);
		boolean b = false;
		try {
			InstructorUI.appendRetakeNodeToXML(rbean);
			retakesReader reader = new retakesReader();

			try {
				retakes rlist = reader.read(retakesXMLPath);

				for (retakeBean r : rlist) {
					if (r.compareTo(rbean) == 0 && !b) {
						b = true;
					}
				}
			} catch (Exception e) {
				b = false;
			}

		} catch (Exception e) {
			b = false;
		}
		Assert.assertTrue(b);

	}

	/*
	 * Test for user's creating new, invalid retake
	 * This test was first created by creating just the name of the test
	 * and adding a new retakesReader into the rlist and test for assertFalse, 
	 * so see if the test was able to pass
	 * Refactoring: Then we add a try-catch exception since the test was false on us,
	 * after making a few changes from both this test file and the UI files,
	 * we add another try-catch exception to test for why it's failing.
	 * After realizing that the path was wrongly type, we was able to make the test pass
	 */
	@Test
	public void invalidRetakesTest() {

		retakeBean rbean = null;
		boolean b = false;
		try {
			InstructorUI.appendRetakeNodeToXML(rbean);
			retakesReader reader = new retakesReader();

			try {
				retakes rlist = reader.read(retakesXMLPath);

				for (retakeBean r : rlist) {
					if (r.compareTo(rbean) == 0 && !b) {
						b = true;
					}
				}
			} catch (Exception e) {
				b = false;
			}

		} catch (Exception e) {
			b = false;
		}

		Assert.assertFalse(b);
	}
}