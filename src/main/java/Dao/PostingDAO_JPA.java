/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import Model.Posting;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Vito Corleone
 */
@Stateless
public class PostingDAO_JPA implements PostingDAO {

    @PersistenceContext(unitName = "KwetterPU_Enterprise")
    private EntityManager em;

    public PostingDAO_JPA() {
    }

    @Override
    public void create(Posting posting) {
        em.persist(posting);
    }

    @Override
    public void edit(Posting posting) {
        em.merge(posting);
    }

    @Override
    public Posting find(Long postingId) {
        Query q = em.createNamedQuery("Posting.findBypostingId", Posting.class);
        q.setMaxResults(1);
        q.setParameter("postingId", postingId);
        Posting foundPost = (Posting) q.getSingleResult();
        if (foundPost == null) {
            return null;
        }
        return foundPost;
    }

    @Override
    public void remove(Long postingId) {
        Posting u = em.find(Posting.class, postingId);
        if (u != null) {
            em.remove(u);
        }
    }

    @Override
    public List<Posting> findPostings(String author) {
        Query q = em.createNamedQuery("Posting.findByAuthor", Posting.class);
        q.setParameter("author", author);
        List<Posting> foundPostings = (List<Posting>) q.getResultList();
        return foundPostings;
    }
}
