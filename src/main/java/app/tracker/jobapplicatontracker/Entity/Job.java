package app.tracker.jobapplicatontracker.Entity;

import app.tracker.jobapplicatontracker.Security.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "job")
public class Job {
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
    List<Notes> notes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public enum Color {
        RED("E3371E"),
        BLACK("151F30"),
        ORANGE("FF7A48"),
        TEAL("0593A2"),
        BLUE("103778");

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
