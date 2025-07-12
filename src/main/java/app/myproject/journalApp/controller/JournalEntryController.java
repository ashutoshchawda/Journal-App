package app.myproject.journalApp.controller;

import app.myproject.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping("/all")
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntryMap.values());
    }

    @GetMapping("/{id}")
    public JournalEntry getJournalById(@PathVariable Long id) {
        return journalEntryMap.get(id);
    }

    @PostMapping("/create")
    public String addData(@RequestBody JournalEntry journalEntry) {
        journalEntryMap.put(journalEntry.getId(), journalEntry);
        return "added entry with id " + journalEntry.getId();
    }

    @DeleteMapping("/{id}")
    public JournalEntry deleteJournalById(@PathVariable Long id) {
        return journalEntryMap.remove(id);
    }

    @PutMapping("/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry JournalEntry) {
        return journalEntryMap.put(id, JournalEntry);
    }

}
