package quizretakes;
import static org.junit.Assert.*;
import org.junit.*;

import java.time.LocalDate;
import java.util.*;

public class printQuizScheduleFormTest {

	@Test //(expected = NullPointerException.class)
	public void testForNullData() {
		
		try{
			quizzes quizList = new quizzes();
			retakes retakesList = new retakes();
			courseBean course = null;
			quizsched.printQuizScheduleForm(quizList, retakesList, course);
			
		}catch(Exception e){
			return;
		}
		
		fail("Exception expected");
	}
	
	@Test
	public void testExample() throws Exception{
		
		
		//courseBean(String courseID, String courseTitle, String retakeDuration, LocalDate startSkip,
		//LocalDate endSkip, String dataLocation)
		courseReader cr = new courseReader();
		courseBean course = cr.read("swe437");
		
		//quizBean(int quizID, int month, int day, int hour, int minute)
		quizBean quiz = new quizBean(5, 2, 12, 10, 30);
		quizzes quizList = new quizzes(quiz);
		
		//retakeBean(int ID, String location, int month, int day, int hour, int minute)
		retakeBean retake = new retakeBean(4, "EB 114", 2, 15, 10, 0);
		retakes retakesList = new retakes(retake);
		
		quizsched.printQuizScheduleForm(quizList, retakesList, course);
		
		
		
		
//		String output = "******************************************************************************\n"
//				+ "GMU quiz retake scheduler for class Software testing\n"
//				+ "******************************************************************************\n\n\n"
//				+ "You can sign up for quiz retakes within the next two weeks."
//				+ "Enter your name (as it appears on the class roster), "
//				+ "then select which date, time, and quiz you wish to retake from the following list.\n"
//				+ "Today is" + LocalDate.now()
//				+ "\nCurrently scheduling quizzes for the next two weeks, until " + LocalDate.now().plusDays(new Long(14))
//				+ "\nRETAKE: " + ;
//				
//						
//						
//
//
//						 
//						 
//						RETAKE: TUESDAY, FEBRUARY 19, at 15:00 in EB 5321
//						    1) Quiz 2 from TUESDAY, FEBRUARY 5
//						    2) Quiz 3 from TUESDAY, FEBRUARY 12
//						    3) Quiz 4 from TUESDAY, FEBRUARY 19
//						RETAKE: WEDNESDAY, FEBRUARY 20, at 15:30 in EB 4430
//						    4) Quiz 3 from TUESDAY, FEBRUARY 12
//						    5) Quiz 4 from TUESDAY, FEBRUARY 19
//						RETAKE: THURSDAY, FEBRUARY 21, at 10:00 in Buchanan Hall D002
//						    6) Quiz 3 from TUESDAY, FEBRUARY 12
//						    7) Quiz 4 from TUESDAY, FEBRUARY 19
//						"	);
		
	}
	
	
	

}