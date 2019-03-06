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

	private static String quizXMLPath = "/Users/keith/Documents/Spring2019/swe437/quizschedule4/resources/quiz-orig-swe437.xml";
	private static String retakesXMLPath = "/Users/keith/Documents/Spring2019/swe437/quizschedule4/resources/quiz-retakes-swe437.xml";
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
	 * TODO: Find way to only read last quiz from iterator
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
