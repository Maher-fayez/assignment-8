package edu.mum.cs.cs425.studentmgmt;
import edu.mum.cs.cs425.studentmgmt.model.Classroom;
import edu.mum.cs.cs425.studentmgmt.model.Student;
import edu.mum.cs.cs425.studentmgmt.model.Transcript;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
public class StudentMgmtApp
{
    public static void main( String[] args )
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyStudentMgmtApp");
        EntityManager em = emf.createEntityManager();

        Student student = createSampleStudent();

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(student);

        transaction.commit();

        em.close();
        emf.close();
    }


    public static Connection getConnection() throws SQLException {
        String url = "jdbc:h2:<database_url>";
        String username = "<username>";
        String password = "<password>";

        return DriverManager.getConnection(url, username, password);
    }
    private static Student createSampleStudent() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        Student student = new Student("000-61-0001",
                "Anna", "Lynn",
                "Smith",3.45, calendar.getTime()
        );

        Transcript transcript = new Transcript();
        transcript.setDegreeTitle("BS Computer Science");

        Classroom classroom = new Classroom();
        classroom.setBuildingName("McLaughlin building");
        classroom.setRoomNumber("M105");

        student.setTranscript(transcript);
        student.setClassroom(classroom);

        return student;
    }
}
