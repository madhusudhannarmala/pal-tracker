package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;


public class TimeEntryController {
    private final TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }


    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        return created(null)
                .body(timeEntriesRepo.create(timeEntryToCreate));
    }


    public ResponseEntity<TimeEntry> read( long id) {
        var timeEntryFound = timeEntriesRepo.find(id);

        return timeEntryFound == null ?
                notFound().build() :
                ok(timeEntryFound);
    }


    public ResponseEntity<List<TimeEntry>> list() {
        return ok(timeEntriesRepo.list());
    }


    public ResponseEntity<TimeEntry> update(long id, TimeEntry timeEntry) {
        var timeEntryUpdated = timeEntriesRepo.update(id, timeEntry);

        return timeEntryUpdated == null ?
                notFound().build() :
                ok(timeEntryUpdated);
    }


    public ResponseEntity<Void> delete(long id) {
        timeEntriesRepo.delete(id);

        return noContent().build();
    }
}
