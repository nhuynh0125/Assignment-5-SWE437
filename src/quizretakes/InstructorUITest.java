package quizretakes;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InstructorUITest {

	private static String quizXMLPath = "/Users/keith/Documents/Spring2019/swe437/quizschedule4/resources/quiz-orig-swe437.xml";
	private static String retakesXMLPath = "/Users/keith/Documents/Spring2019/swe437/quizschedule4/resources/quiz-retakes-swe437.xml";
	private static String origQuizXMLContent;
	private static String origRetakeXMLContent;

	//
	@BeforeClass
	public void declaration() {
		// TODO: create string with all data from xml files in before method
		// origQuizXMLContent = read file
		// origRetakeXMLContent = read file

	}

	@After
	public void afterMethod() {
		// TODO: paste original contents back to xml files after every method
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
