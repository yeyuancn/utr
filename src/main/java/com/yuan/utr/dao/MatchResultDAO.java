package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.MatchResult;
import com.yuan.utr.model.persistent.MatchResultView;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class MatchResultDAO {

    private static final Logger logger = Logger.getLogger(MatchResultDAO.class.getName());

    public MatchResult getMatchResult(long id) {
        logger.info("Getting matchResult");
        EntityManager em = Startup.getEntityManager();
        try {
            return em.find(MatchResult.class, id);
        } finally {
            em.close();
        }
    }

    public Long getMatchResultNumber() {
        logger.info("Getting matchresult number");

        EntityManager em = Startup.getEntityManager();
        try {
            Query q = em.createQuery("SELECT count(m) from MatchResult m");
            return (Long) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    //-----------------------------

    public List<MatchResultView> getMatchResultViewsForPlayer(long playerId) {
        logger.info("Getting all match Results for a player");
        EntityManager em = Startup.getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM MatchResultView u where (u.winnerId = :playerId " +
                    "or u.loserId = :playerId) order by record_time desc")
                    .setParameter("playerId", playerId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteMatchResult(long id) {
        logger.info("Deleting matchResult");
        EntityManager em = Startup.getEntityManager();
        try {
            MatchResult matchResult = em.find(MatchResult.class, id);

            em.getTransaction().begin();
            em.remove(matchResult);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<MatchResultView> getMatchResultViews(long divisionId) {
        logger.info("Getting all match results for the division");
        EntityManager em = Startup.getEntityManager();
        try {
            Query q = em.createQuery("SELECT u FROM MatchResultView u where u.divisionId = :divisionId " +
                    "order by record_time desc")
                    .setParameter("divisionId", divisionId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void addMatchResult(MatchResult matchResult) {
        logger.info("Adding match result");
        EntityManager em = Startup.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(matchResult);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
