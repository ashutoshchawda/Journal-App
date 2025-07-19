package app.myproject.journalApp.controller;

import app.myproject.journalApp.entity.JournalEntry;
import app.myproject.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create")
    public JournalEntry addData(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.addData(journalEntry);
        return journalEntry;
    }

    @GetMapping("/all")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id) {
        return journalEntryService.getJournalById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public boolean deleteJournalById(@PathVariable ObjectId id) {
        journalEntryService.deleteJournalById(id);
        return true;
    }

    @PutMapping("/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newJournalEntry) {
        JournalEntry oldJournalEntry = journalEntryService.getJournalById(id).orElse(null);
        if (oldJournalEntry != null) {
            oldJournalEntry.setContent(newJournalEntry.getContent() != null && !newJournalEntry.getContent().isEmpty() ? newJournalEntry.getContent() : oldJournalEntry.getContent());
            oldJournalEntry.setTitle(newJournalEntry.getTitle() != null && !newJournalEntry.getTitle().isEmpty() ? newJournalEntry.getTitle() : oldJournalEntry.getTitle());
        }
        journalEntryService.addData(oldJournalEntry);
        return oldJournalEntry;
    }

}
