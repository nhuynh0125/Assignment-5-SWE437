
package quizretakes;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InstructorUI {
	
	private static String quizzesFileName = "quiz-orig-swe437.xml";
    private static String retakesFileName = "quiz-retakes-swe437.xml";
	public static String dataLocation = "src/"; /* CLI */
	private static Scanner sc;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		   System.out.println("");
		   System.out.println("");
		   System.out.println("******************************************************************************");
		   System.out.println("GMU quiz retake scheduler for class SWE437 (Instructor Use)");
		   System.out.println("******************************************************************************");
		   System.out.println("");
		   System.out.println("");
		   
		   sc = new Scanner(System.in);
		   
		   int input = 0;
		   
		   do{
			   System.out.println("Please enter 1 or 2 for the following options: ");
			   System.out.println("(1) Add a new quiz to the system");
			   System.out.println("(2) Add a new retake to the system");
			   			   
			   input = sc.nextInt();
			   
			   switch(input){
		   
			   case 1:
				   appendQuizNodeToXML();
				   break;
			   case 2:
				   appendRetakeNodeToXML();
				   break;
			   default:
				   System.out.println("Invalid input! Try again.\n");
			   }
		   } while(input != 1 && input != 2);
			
		 }
	
	/**
	 * Append a new quiz to quiz-orig-swe437.xml
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void appendQuizNodeToXML() throws ParserConfigurationException, SAXException, IOException{
				
		System.out.println("Let's create a new quiz");
		
		quizBean newQuiz = createNewQuiz();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(quizzesFileName));
		
		// Get all the nodes
		NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		
		
	}
	
	private static quizBean createNewQuiz(){
		
		//public quizBean(int quizID, int month, int day, int hour, int minute) 
		System.out.println("Enter a quiz ID: ");
		int quizID = sc.nextInt();
		
		System.out.println("Enter the month: ");
		int month = sc.nextInt();
		
		System.out.println("Enter the day: ");
		int day = sc.nextInt();
		
		System.out.println("Enter the hour: ");
		int hour = sc.nextInt();
		
		System.out.println("Enter the minute minute: ");
		int minute = sc.nextInt();
		
		//checking if data is validated, check later
//		LocalDate today  = LocalDate.now();
//		
//		if(today.)
		
		quizBean newQuiz = new quizBean(quizID, month, day, hour, minute );
		
		return newQuiz;
	}
	
	/**
	 * Append a new retake to quiz-retakes-swe437.xml
	 */
	public static void appendRetakeNodeToXML(){
		
	}

}
