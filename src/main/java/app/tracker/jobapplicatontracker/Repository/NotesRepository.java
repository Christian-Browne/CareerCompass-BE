package app.tracker.jobapplicatontracker.Repository;

import app.tracker.jobapplicatontracker.Entity.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> {
}
