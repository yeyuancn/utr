package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.League;
import com.yuan.utr.ws.exception.AppValidationException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

public class LeagueDAO {

    private static final Logger logger = Logger.getLogger(LeagueDAO.class.getName());

    public League getLeague(long id) {
        logger.info("Getting league");
        EntityManager em = Startup.getEntityManager();
        try {
            return em.find(League.class, id);
        } finally {
            em.close();
        }
    }


    public League getLeagueByName(String name) {
        logger.info("Getting league by name");
        EntityManager em = Startup.getEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(League.class);
            return (League) criteria.add(Restrictions.eq("name", name)).uniqueResult();
        } finally {
            em.close();
        }
    }


    public void addLeague(League league) throws AppValidationException {
        logger.info("Adding league");

        EntityManager em = Startup.getEntityManager();


        try {
            em.getTransaction().begin();
            em.persist(league);
            em.getTransaction().commit();
        } catch (RollbackException e) {
            logger.info("Caught exception, " + e.getCause());
            throw new AppValidationException("Duplicate league error, please choose a different league name");
        } finally {
            em.close();
        }

    }


    public void deleteLeague(long id) {
        logger.info("Deleting league");
        EntityManager em = Startup.getEntityManager();
        try {
            League league = em.find(League.class, id);

            em.getTransaction().begin();
            em.remove(league);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<League> getLeagues() {
        logger.info("Getting all leagues");
        EntityManager em = Startup.getEntityManager();
        try {
            Query q = em.createQuery("SELECT p FROM League p");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Long getLeagueNumber() {
        logger.info("Getting league number");

        EntityManager em = Startup.getEntityManager();
        try {
            Query q = em.createQuery("SELECT count(l) from League l");
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
