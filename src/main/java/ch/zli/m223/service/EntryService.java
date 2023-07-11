package ch.zli.m223.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry", Entry.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(entityManager.find(Entry.class, id));
    }

    @Transactional
    public void updateEntry(Long id, Entry entry) {
        Entry entryToUpdate = entityManager.find(Entry.class, id);

        entryToUpdate.setCheckIn(entry.getCheckIn());
        entryToUpdate.setCheckOut(entry.getCheckOut());
        entityManager.merge(entryToUpdate);
    }
}
