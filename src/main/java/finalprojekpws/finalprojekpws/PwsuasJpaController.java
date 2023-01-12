/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalprojekpws.finalprojekpws;

import finalprojekpws.finalprojekpws.exceptions.NonexistentEntityException;
import finalprojekpws.finalprojekpws.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DELL
 */
public class PwsuasJpaController implements Serializable {

    public PwsuasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    PwsuasJpaController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pwsuas pwsuas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pwsuas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPwsuas(pwsuas.getId()) != null) {
                throw new PreexistingEntityException("Pwsuas " + pwsuas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pwsuas pwsuas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pwsuas = em.merge(pwsuas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pwsuas.getId();
                if (findPwsuas(id) == null) {
                    throw new NonexistentEntityException("The pwsuas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pwsuas pwsuas;
            try {
                pwsuas = em.getReference(Pwsuas.class, id);
                pwsuas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pwsuas with id " + id + " no longer exists.", enfe);
            }
            em.remove(pwsuas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pwsuas> findPwsuasEntities() {
        return findPwsuasEntities(true, -1, -1);
    }

    public List<Pwsuas> findPwsuasEntities(int maxResults, int firstResult) {
        return findPwsuasEntities(false, maxResults, firstResult);
    }

    private List<Pwsuas> findPwsuasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pwsuas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pwsuas findPwsuas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pwsuas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPwsuasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pwsuas> rt = cq.from(Pwsuas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
