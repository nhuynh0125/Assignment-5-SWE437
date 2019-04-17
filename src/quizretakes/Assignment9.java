/**
 * Ngoc Huynh, Quang Vo, Keith Saldana
 * 
 * JUnit test for Assignment 9
 */
package quizretakes;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class Assignment9 {
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

	/*
	 * The declaration method is an example of controllability because it sets up the tests
	 */
	@Before
	public void declaration() {
		current = LocalDate.now();
		quizList = new quizzes();
		retakesList = new retakes();

		Intro = nl + nl + "******************************************************************************" + nl
				+ "GMU quiz retake scheduler for class Software testing" + nl
				+ "******************************************************************************" + nl + nl + nl
				+ "You can sign up for quiz retakes within the next two weeks. " + nl
				+ "Enter your name (as it appears on the class roster), " + nl
				+ "then select which date, time, and quiz you wish to retake from the following list." + nl + nl;
		Date = "Today is " + current.getDayOfWeek() + ", " + current.getMonth() + " " + current.getDayOfMonth() + nl;
		Until = "Currently scheduling quizzes for the next two weeks, until " + current.plusWeeks(2).getDayOfWeek()
				+ ", " + current.plusWeeks(2).getMonth() + " " + current.plusWeeks(2).getDayOfMonth() + nl;
		finalString = Intro + Date + Until;
	}

	@SuppressWarnings("static-access")
	@Test
	public void TestPath1() {
		// start controllability
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizBean qb1 = new quizBean(1, 04, 23, 10, 30);
		quizBean qb2 = new quizBean(2, 04, 30, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		retakeBean qr1 = new retakeBean(1, "EB 4430", 04, 23, 15, 30);
		retakeBean qr2 = new retakeBean(2, "???", 05, 07, 15, 30);
		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		// end controllability

		// start observability
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		String temp = outStream.toString();
		temp = temp.replaceAll("\\s+", " ");
		String goalOutput = finalString
				+ "RETAKE: TUESDAY, APRIL 23, at 15:30 in EB 4430\n    1) Quiz 1 from TUESDAY, APRIL 23" + nl + nl;
		goalOutput = goalOutput.replaceAll("\\s+", " ");
		assertTrue(temp.equals(goalOutput));
		// end observability
	}

	@SuppressWarnings("static-access")
	@Test
	public void TestPath2() {
		// start controllability
		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
		quizBean qb1 = new quizBean(1, 04, 23, 10, 30);
		quizBean qb2 = new quizBean(2, 04, 30, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		retakeBean qr1 = new retakeBean(1, "EB 4430", 03, 26, 10, 30);
		retakeBean qr2 = new retakeBean(2, "???", 05, 07, 15, 30);
		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(outStream);
		PrintStream streamOut = System.out;
		System.setOut(stream);
		// end controllability

		// start observability
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		System.out.flush();
		System.setOut(streamOut);
		String temp = outStream.toString();
		temp = temp.replaceAll("\\s+", " ");
		finalString = finalString.replaceAll("\\s+", " ");
		assertTrue(temp.equals(finalString));
		// end observability
	}

//	@SuppressWarnings("static-access")
//	@Test
//	public void TestPath3() {
//		// start controllability
//		startSkip = LocalDate.of(2019, 04, 27);
//		endSkip = LocalDate.of(2019, 05, 8);
//
//		course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
//		quizBean qb1 = new quizBean(1, 04, 23, 10, 30);
//		quizBean qb2 = new quizBean(2, 04, 30, 10, 30);
//		quizList.addQuiz(qb1);
//		quizList.addQuiz(qb2);
//		retakeBean qr1 = new retakeBean(1, "EB 4430", 04, 30, 15, 30);
//		retakeBean qr2 = new retakeBean(2, "???", 05, 07, 15, 30);
//		retakesList.addRetake(qr1);
//		retakesList.addRetake(qr2);
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		PrintStream stream = new PrintStream(outStream);
//		PrintStream streamOut = System.out;
//		System.setOut(stream);
//		// end controllability
//
//		// start observability
//		schedule.printQuizScheduleForm(quizList, retakesList, course);
//		System.out.flush();
//		System.setOut(streamOut);
//		String temp = outStream.toString();
//		temp = temp.replaceAll("\\s+", " ");
//		String goalString = finalString
//				+ "RETAKE: TUESDAY, APRIL 30, at 15:30 in EB 4430 1) Quiz 1 from TUESDAY, APRIL 23 2) Quiz 2 from TUESDAY, APRIL 30 Skipping a week, no quiz or retakes. RETAKE: TUESDAY, MAY 7, at 15:30 in ??? 3) Quiz 1 from TUESDAY, APRIL 23 4) Quiz 2 from TUESDAY, APRIL 30 ";
//		goalString = goalString.replaceAll("\\s+", " ");
//		assertTrue(temp.equals(goalString));
//		// end observability
//	}
}
