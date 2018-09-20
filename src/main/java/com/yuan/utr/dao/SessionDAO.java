package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.Session;
import com.yuan.utr.ws.exception.AppValidationException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.util.Date;
import java.util.UUID;

public class SessionDAO {

    private static final Logger logger = Logger.getLogger(SessionDAO.class.getName());

    private static final long SESSION_LENGTH = 14l * 24 * 3600 * 1000;

    //------------------------------

    public Session getActiveSessionByUUID(String uuid) throws AppValidationException {
        logger.info("Getting Session with UUID: " + uuid);
        EntityManager em = Startup.getEntityManager();
        try {
            return (Session) em.createQuery("SELECT s FROM Session s WHERE s.uuid = :uuid " +
                    "and s.expireDate is not null and s.expireDate > :current")
                    .setParameter("uuid", uuid)
                    .setParameter("current", new Date())
                    .getSingleResult();
        } catch (NoResultException nre) {
            logger.error("Failed to get session by UUID");
            throw new AppValidationException("Failed to get session by UUID");
        } finally {
            em.close();
        }
    }

    public Session createSessionForPlayer(long playerId) {
        logger.info("Creating Session");
        EntityManager em = Startup.getEntityManager();
        try {
            Session s = new Session();
            s.setPlayerId(playerId);
            s.setUuid(UUID.randomUUID().toString());
            s.setExpireDate(new Date(new Date().getTime() + SESSION_LENGTH));
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }
}
