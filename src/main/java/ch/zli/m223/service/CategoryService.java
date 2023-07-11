package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Category;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteCategory(Long id) {
        entityManager.remove(entityManager.find(Category.class, id));
    }

    @Transactional
    public void updateCategory(Long id, Category category) {
        Category categoryToUpdate = entityManager.find(Category.class, id);

        categoryToUpdate.setTitle(category.getTitle());
        entityManager.merge(categoryToUpdate);
    }
}
