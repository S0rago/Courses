import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "linkedpurchaselist")
@IdClass(LinkedPurchase.LinkedPurchaseKey.class)
public class LinkedPurchase {
    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;
    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public LinkedPurchase() {

    }

    public LinkedPurchase(Object[] row) {
        setStudentId((Integer) row[0]);
        setStudentName((String) row[1]);
        setCourseId((Integer) row[2]);
        setCourseName((String) row[3]);
        setPrice((Integer) row[4]);
        setSubscriptionDate((Date) row[5]);
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public Date getSubscriptionDate() { return subscriptionDate; }
    public void setSubscriptionDate(Date subscriptionDate) { this.subscriptionDate = subscriptionDate; }

    public static class LinkedPurchaseKey implements Serializable {

        private int studentId;
        private int courseId;

        public LinkedPurchaseKey() {
        }

        public LinkedPurchaseKey(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public int getCourseId() { return courseId; }
        public void setCourseId(int courseId) { this.courseId = courseId; }
    }
}
