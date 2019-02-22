package quizretakes;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TestingFile {
	quizsched schedule;
	static quizzes quizList;
	static retakes retakesList;
	static courseBean course;
	static LocalDate current;
	static courseReader readCourse;
	static String expectedIntro;
	static String expectedDateLine;
	static String schedulingUntil;
	final String courseID = "quizretakes/course-swe437.xml";
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		declaration();
		testPrintQuizScheduleForm();
	}
	public static void declaration() throws IOException, ParserConfigurationException, SAXException {
	quizList = new quizzes();
	retakesList = new retakes();
	readCourse = new courseReader();
	//course = new courseBean(courseID, courseID, courseID, current, current, courseID);
	//course = new courseBean("swe437", "Software testing", 14, 01-24, 21, null);
	//course = readCourse.read(courseID);
	expectedIntro =
            "\n" +
            "\n" +
            "******************************************************************************\n" +
            "GMU quiz retake scheduler for class Software testing\n" +
            "******************************************************************************\n" +
            "\n" +
            "\n" +
            "You can sign up for quiz retakes within the next two weeks. \n" +
            "Enter your name (as it appears on the class roster), \n" +
            "then select which date, time, and quiz you wish to retake from the following list.\n" +
            "\n";
    expectedDateLine = "Today is " + current.getDayOfWeek() + ", " + current.getMonth() + " " + current.getDayOfMonth() +"\n";
    schedulingUntil = "Currently scheduling quizzes for the next two weeks, until " +
            current.plusWeeks(2).getDayOfWeek() + ", " + current.plusWeeks(2).getMonth() + " " + current.plusWeeks(2).getDayOfMonth();
	
}
	public static void testPrintQuizScheduleForm() {
		quizBean qb = new quizBean(1, 01, 29, 10, 30);
		quizList.addQuiz(qb);
		retakeBean rb = new retakeBean(1, "EB 4430", 01, 30, 15, 30);
		retakesList.addRetake(rb);
		//final expected string
	    String finalString = expectedIntro + expectedDateLine + schedulingUntil + "\n\n";
		
		//assertTrue(qb.getDate().toString().equals("01-29-2019"));
		quizsched.printQuizScheduleForm(quizList,retakesList,course);
		//assertEquals(schedule.toString(),finalString);
	}
	

}
