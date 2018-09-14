package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.Player;
import com.yuan.utr.ws.exception.AppValidationException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.util.List;

public class PlayerDAO
{

    private static final Logger logger = Logger.getLogger(PlayerDAO.class.getName());

    public Player getPlayer(long id)
    {
        logger.info("Getting player");
        EntityManager em = Startup.getEntityManager();
        try
        {
            return em.find(Player.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public void addPlayer(Player player) throws AppValidationException
    {
        logger.info("Adding player");
        EntityManager em = Startup.getEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(player);
            em.getTransaction().commit();
        }catch (RollbackException e)
        {
            logger.info("Caughter exception, " + e.getCause());
            throw new AppValidationException("The player is already in the league");
        }
        finally
        {
            em.close();
        }

    }


    public void deletePlayer(long id)
    {
        logger.info("Deleting player");
        EntityManager em = Startup.getEntityManager();
        try
        {
            Player player = em.find(Player.class, id);
            em.getTransaction().begin();
            em.remove(player);
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public Long getPlayerNumber()
    {
        logger.info("Getting players number");

        EntityManager em = Startup.getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT count(p) from Player p");
            return (Long) q.getSingleResult();
        }
        finally
        {
            em.close();
        }
    }

    //----------------------------------------
    public Player getPlayerByAcctId(long acctId) throws AppValidationException {
        logger.info("Getting the playe associated with the account id " + acctId);
        EntityManager em = Startup.getEntityManager();
        try {
            String query = "SELECT p FROM Player p WHERE p.acctId = :acctId";
            return (Player) em.createQuery(query)
                    .setParameter("acctId", acctId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            logger.error("Cannot find player associated with the account" );
            throw new AppValidationException("Cannot find player associated with the account");
        } finally {
            em.close();
        }
    }

    public Player getPlayerByName(String firstName, String lastName) throws  AppValidationException{
        logger.info("Getting player by Name");
        EntityManager em = Startup.getEntityManager();
        try
        {
            String query = "SELECT p FROM Player p WHERE upper(p.firstName) = :firstName and " +
                    "upper(p.lastName) = :lastName and p.acctId is null";

            List<Player> list = (List<Player>) em.createQuery(query)
                    .setParameter("firstName", firstName.trim().toUpperCase())
                    .setParameter("lastName", lastName.trim().toUpperCase())
                    .getResultList();

            if (list.size() > 1) {
                logger.error("Duplicate player found for firstName: " + firstName + ", lastName: " + lastName);
                throw new AppValidationException("We found duplicate players with the same first and last name, " +
                        "please contact league director to complete account registration");
            } else if (list.size() == 0) {
                logger.error("No avialable player found for firstName: " + firstName + ", lastName: " + lastName);
                throw new AppValidationException("Cannot find player with the name, please double check the name" +
                        " and contact league director if problem persists");
            } else {
                return list.get(0);
            }
        }
        finally
        {
            em.close();
        }
    }

    public void updatePlayer(Player player) throws AppValidationException
    {
        logger.info("Updating player");
        EntityManager em = Startup.getEntityManager();
        Player p = em.find(Player.class, player.getId());
        try
        {
            em.getTransaction().begin();
            p.setAcctId(player.getAcctId());
            p.setDivisionId(player.getDivisionId());
            p.setFirstName(player.getFirstName());
            p.setLastName(player.getLastName());
            p.setEmail(player.getEmail());
            p.setPhone(player.getPhone());
            p.setUtrScore(player.getUtrScore());
            p.setUtrId(player.getUtrId());
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    public List<Player> getPlayersByDivision(long divisionId)
    {
        logger.info("Getting all players in the division");
        EntityManager em = Startup.getEntityManager();
        try
        {
            String query = "SELECT p FROM Player p WHERE p.divisionId = :divisionId";
            return em.createQuery(query)
                    .setParameter("divisionId", divisionId)
                    .getResultList();
        }
        finally
        {
            em.close();
        }
    }
}
