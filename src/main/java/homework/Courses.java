package homework;

import javax.persistence.*;

@Entity
@Table(name = "Courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private Integer duration;

    public static Courses create(String title, Integer duration) {
        return new Courses(title,duration);
    }

    public Courses(String title, Integer duration) {
        this.title = title;
        this.duration = duration;
    }

    public Courses() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}