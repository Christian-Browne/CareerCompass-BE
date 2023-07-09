package app.tracker.jobapplicatontracker.Demo.Repository;

import app.tracker.jobapplicatontracker.Demo.Entity.NotesDemo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesDemoRepository extends CrudRepository<NotesDemo, Long> {
    void deleteByJobId(Long id);
}
