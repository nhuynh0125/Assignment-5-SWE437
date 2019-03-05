
package quizretakes;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class InstructorUI {
	
	private static String dataLocation = "/Users/ryanvo1/Documents/workspace/swe437-Assignment4/"; /* CLI */
	private static String quizzesFileName = dataLocation + "quiz-orig-swe437.xml";
    private static String retakesFileName = dataLocation + "quiz-retakes-swe437.xml";
	private static Scanner sc;

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, Exception {
		// TODO Auto-generated method stub
		
		   System.out.println("");
		   System.out.println("");
		   System.out.println("******************************************************************************");
		   System.out.println("GMU quiz retake scheduler for class SWE 437 (Instructor Use)");
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
	public static boolean appendQuizNodeToXML() throws ParserConfigurationException, SAXException, IOException, Exception{
				
		System.out.println("Let's create a new quiz");
		
		quizBean newQuiz = createNewQuiz();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(quizzesFileName));
				
		Element root = document.getDocumentElement();
		Element rootElement = document.getDocumentElement();
		
		Collection<quizBean> quizzes = new ArrayList<quizBean>();
	    quizzes.add(newQuiz);
	    
	    for(quizBean i : quizzes){
	    	  Element quiz = document.createElement("quiz");
	          rootElement.appendChild(quiz);

	          Element id = document.createElement("id");
	          id.appendChild(document.createTextNode(Integer.toString(i.getID())));
	          quiz.appendChild(id);

	          Element dateGiven = document.createElement("dateGiven");
	          quiz.appendChild(dateGiven);
	          
	          Element month = document.createElement("month");
	          month.appendChild(document.createTextNode(Integer.toString(i.getMonthNum())));
	          dateGiven.appendChild(month);
	          
	          Element day = document.createElement("day");
	          day.appendChild(document.createTextNode(Integer.toString(i.getDate().getDayOfMonth())));
	          dateGiven.appendChild(day);
	          
	          Element hour = document.createElement("hour");
	          hour.appendChild(document.createTextNode(i.timeAsString().substring(0, 2)));
	          dateGiven.appendChild(hour);
	          
	          Element minute = document.createElement("minute");
	          minute.appendChild(document.createTextNode(i.timeAsString().substring(3)));
	          dateGiven.appendChild(minute);

	          root.appendChild(quiz);
	    }
	    
	    DOMSource source = new DOMSource(document);

	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    StreamResult result = new StreamResult(quizzesFileName);
	    transformer.transform(source, result);
	    
	    System.out.println("The quiz was added to the system successfully");
	    
	    return true;
	  }
	
	/**
	 * Create a new quiz by entering the information
	 * @return the new created quiz
	 */
	private static quizBean createNewQuiz(){
		
		System.out.println("Enter a quiz ID: ");
		int quizID = sc.nextInt();
		
		System.out.println("Enter the month: ");
		int month = sc.nextInt();
		
		System.out.println("Enter the day: ");
		int day = sc.nextInt();
		
		System.out.println("Enter the hour: ");
		int hour = sc.nextInt();
		
		System.out.println("Enter the minute: ");
		int minute = sc.nextInt();
		
		quizBean newQuiz = new quizBean(quizID, month, day, hour, minute );
		
	    return newQuiz;
	}
	
	/**
	 * Append a new retake to quiz-retakes-swe437.xml
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void appendRetakeNodeToXML() throws ParserConfigurationException, SAXException, IOException, Exception{
		
		System.out.println("Let's create a new retake");
		
		retakeBean newRetake = createNewRetake();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(retakesFileName));
				
		Element root = document.getDocumentElement();
		Element rootElement = document.getDocumentElement();
		
		Collection<retakeBean> retakes = new ArrayList<retakeBean>();
	    retakes.add(newRetake);
	    
	    for(retakeBean i : retakes){
	    	  Element retake = document.createElement("retake");
	          rootElement.appendChild(retake);

	          Element id = document.createElement("id");
	          id.appendChild(document.createTextNode(Integer.toString(i.getID())));
	          retake.appendChild(id);
	          
	          Element location = document.createElement("location");
	          location.appendChild(document.createTextNode(i.getLocation()));
	          retake.appendChild(location);

	          Element dateGiven = document.createElement("dateGiven");
	          retake.appendChild(dateGiven);
	          
	          Element month = document.createElement("month");
	          month.appendChild(document.createTextNode(Integer.toString(i.getMonthNum())));
	          dateGiven.appendChild(month);
	          
	          Element day = document.createElement("day");
	          day.appendChild(document.createTextNode(Integer.toString(i.getDate().getDayOfMonth())));
	          dateGiven.appendChild(day);
	          
	          Element hour = document.createElement("hour");
	          hour.appendChild(document.createTextNode(i.timeAsString().substring(0, 2)));
	          dateGiven.appendChild(hour);
	          
	          Element minute = document.createElement("minute");
	          minute.appendChild(document.createTextNode(i.timeAsString().substring(3)));
	          dateGiven.appendChild(minute);

	          root.appendChild(retake);
	    }
	    
	    DOMSource source = new DOMSource(document);

	    TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	    StreamResult result = new StreamResult(retakesFileName);
	    transformer.transform(source, result);
	    
	    System.out.println("The retake was added to the system successfully");		
		
	}
	
	/**
	 * Create a new retake by entering the information
	 * @return the new created retake
	 */
	private static retakeBean createNewRetake(){
		
		System.out.println("Enter a retake ID: ");
		int quizID = sc.nextInt();
		
		System.out.println("Enter the location: ");
		String location = sc.next();
		System.out.print("");
		
		System.out.println("Enter the month: ");
		int month = sc.nextInt();
		
		System.out.println("Enter the day: ");
		int day = sc.nextInt();
		
		System.out.println("Enter the hour: ");
		int hour = sc.nextInt();
		
		System.out.println("Enter the minute: ");
		int minute = sc.nextInt();
				
		retakeBean newRetake = new retakeBean(quizID, location, month, day, hour, minute );
		
		return newRetake;
		
	}

}
