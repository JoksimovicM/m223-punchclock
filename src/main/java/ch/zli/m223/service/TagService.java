package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Tag createTag(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    public List<Tag> findAll() {
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteTag(Long id) {
        entityManager.remove(entityManager.find(Tag.class, id));
    }

    @Transactional
    public void updateTag(Long id, Tag tag) {
        Tag tagToUpdate = entityManager.find(Tag.class, id);

        tagToUpdate.setTitle(tag.getTitle());
        entityManager.merge(tagToUpdate);
    }
}
