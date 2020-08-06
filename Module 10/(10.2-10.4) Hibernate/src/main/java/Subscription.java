import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Date getSubscriptionDate() { return subscriptionDate; }
    public void setSubscriptionDate(Date subscriptionDate) { this.subscriptionDate = subscriptionDate; }
}
