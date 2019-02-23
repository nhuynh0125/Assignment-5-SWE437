package quizretakes;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

public class quizschedTest {

	quizsched schedule;
	quizzes quizList;
	retakes retakesList;
	courseBean course;
	LocalDate current;
	courseReader readCourse;
	String courseID = "swe437";
	String courseTitle = "Software testing";
	String retakeDuration = "14";
	String dataLocation = "/var/www/CS/webapps/offutt/WEB-INF/data/";
	LocalDate endSkip = LocalDate.of(2019, 01, 25);
	LocalDate startSkip = LocalDate.of(2019, 01, 21);
	String Intro;
	String Date;
	String Until;
	String finalString;
	
	String nl = System.getProperty("line.separator");

	@Before
	public void declaration() {
		current = LocalDate.now();
		quizList = new quizzes();
		retakesList = new retakes();
		//course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		Intro =
				nl +
						nl +
						"******************************************************************************" + nl +
						"GMU quiz retake scheduler for class Software testing" + nl +
						"******************************************************************************" + nl +
						nl +
						nl +
						"You can sign up for quiz retakes within the next two weeks. " + nl +
						"Enter your name (as it appears on the class roster), " + nl +
						"then select which date, time, and quiz you wish to retake from the following list." + nl +
						nl;
		Date = "Today is " + current.getDayOfWeek() + ", " + current.getMonth() + " " + current.getDayOfMonth() + nl;
		Until = "Currently scheduling quizzes for the next two weeks, until " +
				current.plusWeeks(2).getDayOfWeek() + ", " + current.plusWeeks(2).getMonth() + " " + current.plusWeeks(2).getDayOfMonth() + nl;
		finalString = Intro + Date + Until + nl;
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testSuccessPrint(){
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizBean qb1 = new quizBean(1, 01, 29, 10, 30);
		quizBean qb2 = new quizBean(2, 02, 05, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		retakeBean qr1 = new retakeBean(1, "EB 4430", 01, 30, 15, 30);
		retakeBean qr2 = new retakeBean(2, "???", 02, 05, 16, 00);
		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		//schedule.toString();
		System.out.flush();
		System.setOut(streamOut);
		assertTrue(outStream.toString().equals(finalString));
	}

	/**
	 * This test will not be true anymore when the current date passed 02-27-2019
	 */
	@SuppressWarnings("static-access")
	@Test
	public void testDifferentRetake() {
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizBean qb3 = new quizBean(4, 02, 19, 10, 30);
		quizList.addQuiz(qb3);
		retakeBean qr3 = new retakeBean(4, "EB 4430", 02, 27, 15, 30);
		retakesList.addRetake(qr3);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		assertFalse(outStream.toString().equals(finalString));
	}
	@SuppressWarnings("static-access")
	@Test
	public void testWrongCourseTitle() {
		course = new courseBean(courseID, "Software", retakeDuration, startSkip, endSkip, dataLocation);
		//course = new courseBean(null,null,null,startSkip, endSkip,null);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		assertFalse(outStream.toString().equals(finalString));
	}
	@SuppressWarnings("static-access")
	@Test
	public void testNullCourseID_courseTitle_retakeDuration_dataLocation() {
		//course = new courseBean(courseID, "Software", retakeDuration, startSkip, endSkip, dataLocation);
		course = new courseBean(null,null,null,startSkip, endSkip,null);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		assertFalse(outStream.toString().equals(finalString));
	}
	@Test
	public void testCourseID(){
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		String courseID = course.getCourseID();
		assertTrue("Here is the test for course ID: ", courseID.equals("swe437"));
	}

}
