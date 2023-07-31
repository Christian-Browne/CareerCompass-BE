package app.tracker.jobapplicatontracker.Demo.Entity;

import app.tracker.jobapplicatontracker.Entity.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.awt.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "job_demo")
public class JobDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "must enter a title")
    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "logo")
    private String logo;

    @NotBlank(message = "please enter a company")
    @NotNull
    @Column(name = "company")
    private String company;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private String date;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "post_url")
    private String postUrl;


    // Doesn't make a separate column that stores the notes
    // JPA handles retrieving the notes from a different table
    // just use getNotes() to retrieve the notes
    @JsonIgnore
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    List<NotesDemo> notes;

    public enum Color {
        RED("FFC1C1"),
        YELLOW("FFFFB3"),
        PURPLE("B2A2C7"),
        ORANGE("FFDAB9"),
        PINK("FFB6C1"),
        TEAL("99CCCC"),
        BLUE("A2D5F2");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;
}
