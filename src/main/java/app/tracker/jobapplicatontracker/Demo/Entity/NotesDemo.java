package app.tracker.jobapplicatontracker.Demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NotesDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "body")
    private String body;

    @ManyToOne
    // makes a foreign key that references id column in Job entity
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private JobDemo job;
}