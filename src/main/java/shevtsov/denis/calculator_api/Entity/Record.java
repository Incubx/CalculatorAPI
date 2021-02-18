package shevtsov.denis.calculator_api.Entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private String result;

    @Column(name = "timestamp")
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    public Record() {
    }

    public Record(int id, String expression, String result, Date timestamp, UserInfo userInfo) {
        this.id = id;
        this.expression = expression;
        this.result = result;
        this.timestamp = timestamp;
        this.userInfo = userInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserInfo getUser() {
        return userInfo;
    }

    public void setUser(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
