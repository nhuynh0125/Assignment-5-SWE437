package quizretakes;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.Before;
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
	//final String courseID = "quizretakes/course-swe437.xml";
	//final String quizFile = "quizretakes/quiz-orig-swe437.xml";
	//final String retakeFile = "quizretakes/quiz-retakes-swe437.xml";
	
	@Before
	public void declaration() {
	current = LocalDate.now();
	quizList = new quizzes();
	retakesList = new retakes();
	//readCourse = new courseReader();
	course = new courseBean(courseID, courseTitle, retakeDuration, startSkip, endSkip, dataLocation);
	//try {
	//	course =  readCourse.read(courseID);
	//}
	}
	@SuppressWarnings("static-access")
	@Test
	public void TestquizschedTest(){
		quizBean qb1 = new quizBean(1, 01, 29, 10, 30);
		quizBean qb2 = new quizBean(2, 02, 05, 10, 30);
		quizList.addQuiz(qb1);
		quizList.addQuiz(qb2);
		retakeBean qr1 = new retakeBean(1, "EB 4430", 01, 30, 15, 30);
		retakeBean qr2 = new retakeBean(2, "???", 02, 05, 16, 00);
		retakesList.addRetake(qr1);
		retakesList.addRetake(qr2);
		//assertTrue(qb.getDate().toString().equals("01-29-2019"));
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	     PrintStream stream = new PrintStream(outStream);
	     PrintStream streamOut = System.out;
	     System.setOut(stream);
		
		String expectedIntro =
	            "\r\n" +
	            "\r\n" +
	            "******************************************************************************\r\n" +
	            "GMU quiz retake scheduler for class Software testing\r\n" +
	            "******************************************************************************\r\n" +
	            "\r\n" +
	            "\r\n" +
	            "You can sign up for quiz retakes within the next two weeks. \r\n" +
	            "Enter your name (as it appears on the class roster), \r\n" +
	            "then select which date, time, and quiz you wish to retake from the following list.\r\n" +
	            "\r\n";
	    String expectedDateLine = "Today is " + current.getDayOfWeek() + ", " + current.getMonth() + " " + current.getDayOfMonth() +"\r\n";
	    String schedulingUntil = "Currently scheduling quizzes for the next two weeks, until " +
	            current.plusWeeks(2).getDayOfWeek() + ", " + current.plusWeeks(2).getMonth() + " " + current.plusWeeks(2).getDayOfMonth() + "\r\n";
	    String finalString = expectedIntro + expectedDateLine + schedulingUntil + "\r\n";
		schedule.printQuizScheduleForm(quizList, retakesList, course);
		//schedule.toString();
		System.out.flush();
	    System.setOut(streamOut);
	   // String finalS = outStream.toString();
		//assertEquals(finalS,finalString);
		assertTrue(outStream.toString().equals(finalString));
		//finalString.toString();
	}
	
	@Test
	public void testCourseID(){
		//course.setCourseID("swe437");
		String courseID = course.getCourseID();
		assertTrue("Here is the test for course ID: ", courseID.equals("swe437"));
	}
}
