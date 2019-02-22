package quizretakes;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Random;

public class QuizSchedTests {

    quizsched qsched;
    retakes r;
    quizzes q;
    courseReader cr;
    courseBean cb;
    final String courseID = "quizretakes/course-swe437.xml";
    final String quizFile = "quizretakes/quiz-orig-swe437.xml";
    final String retakeFile = "quizretakes/quiz-retakes-swe437.xml";
    Method quizschedPrintQuizScheduleForm;
    PrintStream out;
    LocalDate cur;
    String expectedIntro;
    String expectedDateLine;
    String schedulingUntil;
    String name;

    @Before
    public void init(){
        qsched = new quizsched();
        r = new retakes();
        q = new quizzes();
        cr = new courseReader();
        quizsched mquizsched;
        try {
            cb = cr.read(courseID);
            quizschedPrintQuizScheduleForm = qsched
                    .getClass()
                    .getDeclaredMethod("printQuizScheduleForm",
                            quizzes.class,
                            retakes.class,
                            courseBean.class);
            quizschedPrintQuizScheduleForm.setAccessible(true);
        }
        catch(Exception e){
            System.out.println("Something went wrong when it should not have!");
        }
        System.setOut(out);
        expectedIntro = "Enter courseID: swe437\n" +
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
        expectedDateLine = "Today is " + cur.getDayOfWeek() + ", " + cur.getMonth() + " " + cur.getDayOfMonth();
        schedulingUntil = "Currently scheduling quizzes for the next two weeks, until" +
                cur.plusWeeks(2).getDayOfWeek() + ", " + cur.plusWeeks(2).getMonth() + " " + cur.plusWeeks(2).getDayOfMonth();
    }

    @Test
    public void quizRetakePassed(){
        Random rand = new Random();
        quizBean qb;
        retakeBean rb;
        qb = new quizBean(1,1,29,10,30);
        rb = new retakeBean(1,"EB 4430",1,30,15,30);
        q.addQuiz(qb);
        r.addRetake(rb);
        try {
            quizschedPrintQuizScheduleForm.invoke(qsched, q, r, cb);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void nullQuizzListTest(){

    }

    @Test
    public void nullRetakeListTest(){

    }

    @Test
    public void nullCourseTest(){

    }

}