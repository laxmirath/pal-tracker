package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository repo;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repo = timeEntryRepository;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry t = repo.create(timeEntryToCreate);
        ResponseEntity r =new ResponseEntity(t,HttpStatus.CREATED);
        return r;
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry T = repo.find(id);
        ResponseEntity r =null;
        if(T != null)
        r = new ResponseEntity(T,HttpStatus.OK);
        else
            r = new ResponseEntity(HttpStatus.NOT_FOUND);
        return r;
    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity r = new ResponseEntity(repo.list(),HttpStatus.OK);

        return r;
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry t = repo.update(id,expected);
        ResponseEntity r =null;
        if (t != null)
        r  = new ResponseEntity(t,HttpStatus.OK);
        else
            r = new ResponseEntity(t,HttpStatus.NOT_FOUND);
        return r;
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        repo.delete(id);
        ResponseEntity r = new ResponseEntity(HttpStatus.NO_CONTENT);
        return r;
    }
}
