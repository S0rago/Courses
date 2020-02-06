import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
public class Purchase implements Serializable {

    @Id
    @Column(name = "student_name")
    private String studentName;

    @Id
    @Column(name = "course_name")
    private String courseName;

    @Id
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    private int price;

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Date getSubscriptionDate() { return subscriptionDate; }
    public void setSubscriptionDate(Date subscriptionDate) { this.subscriptionDate = subscriptionDate; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}
