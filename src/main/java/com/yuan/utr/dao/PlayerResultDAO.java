package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.MatchResult;
import com.yuan.utr.model.persistent.PlayerResult;
import com.yuan.utr.model.persistent.PlayerResultView;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class PlayerResultDAO {
	
	private static final Logger logger = Logger.getLogger(PlayerResultDAO.class.getName());

    public Long getPlayerResultNumber()
    {
        logger.info("Getting player results number");

        EntityManager em = Startup.getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT count(pr) from PlayerResult pr");
            return (Long) q.getSingleResult();
        }
        finally
        {
            em.close();
        }
    }

    public PlayerResult addPlayerResult(PlayerResult playerResult)
    {
    	logger.info("Persisting playerResult, add player result");
    	EntityManager em = Startup.getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(playerResult);
            em.getTransaction().commit();
            return playerResult;
        }
        finally
        {
            em.close();
        }
    }

    public void updatePlayerResult(PlayerResult playerResult)
    {
        logger.info("Updating player result");
        EntityManager em = Startup.getEntityManager();
        try
        {
            PlayerResult pr = em.find(PlayerResult.class, playerResult.getId());

            em.getTransaction().begin();
            pr.setGameWon(playerResult.getGameWon());
            pr.setGameLost(playerResult.getGameLost());
            pr.setGameWonPercent(playerResult.getGameWonPercent());
            pr.setMatchWon(playerResult.getMatchWon());
            pr.setMatchLost(playerResult.getMatchLost());
            pr.setMatchWonPercent(playerResult.getMatchWonPercent());
            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    private PlayerResult buildPlayerResult(long playerId, long divisionId) {
        PlayerResult pr = new PlayerResult();
        pr.setPlayerId(playerId);
        pr.setDivisionId(divisionId);
        pr.setGameLost(0);
        pr.setGameWon(0);
        pr.setGameWonPercent(new BigDecimal(0));
        pr.setMatchLost(0);
        pr.setMatchWon(0);
        pr.setMatchWonPercent(new BigDecimal(0));
        return pr;
    }

    public void addMatchResult(MatchResult matchResult)
    {
        logger.info("Updating player match result when a new match result added");

        PlayerResult pr = getPlayerResult(matchResult.getWinnerId(), matchResult.getDivisionId());
        if (pr == null) {
            addPlayerResult(buildPlayerResult(matchResult.getWinnerId(), matchResult.getDivisionId()));
            pr = getPlayerResult(matchResult.getWinnerId(), matchResult.getDivisionId());
        }
        pr.setGameWon(pr.getGameWon() + matchResult.getGameWon());
        pr.setGameLost(pr.getGameLost() + matchResult.getGameLost());
        pr.setMatchWon(pr.getMatchWon() + 1);
        pr.setMatchWonPercent(new BigDecimal(pr.getMatchWon() * 100.0 / (pr.getMatchWon() + pr.getMatchLost())).setScale(1, BigDecimal.ROUND_UP));
        pr.setGameWonPercent(new BigDecimal(pr.getGameWon() * 100.0 / (pr.getGameWon() + pr.getGameLost())).setScale(1, BigDecimal.ROUND_UP));
        updatePlayerResult(pr);

        pr = getPlayerResult(matchResult.getLoserId(), matchResult.getDivisionId());
        if (pr == null) {
            addPlayerResult(buildPlayerResult(matchResult.getLoserId(), matchResult.getDivisionId()));
            pr = getPlayerResult(matchResult.getLoserId(), matchResult.getDivisionId());
        }
        pr.setGameWon(pr.getGameWon() + matchResult.getGameLost());
        pr.setGameLost(pr.getGameLost() + matchResult.getGameWon());
        pr.setMatchLost(pr.getMatchLost() + 1);
        pr.setMatchWonPercent(new BigDecimal(pr.getMatchWon() * 100.0 / (pr.getMatchWon() + pr.getMatchLost())).setScale(1, BigDecimal.ROUND_UP));
        pr.setGameWonPercent(new BigDecimal(pr.getGameWon() * 100.0 / (pr.getGameWon() + pr.getGameLost())).setScale(1, BigDecimal.ROUND_UP));
        updatePlayerResult(pr);
    }

    public void deleteMatchResult(MatchResult matchResult) {
        logger.info("Updating player match result when a match result deleted");

        PlayerResult pr = getPlayerResult(matchResult.getWinnerId(), matchResult.getDivisionId());

        pr.setGameWon(pr.getGameWon() - matchResult.getGameWon());
        pr.setGameLost(pr.getGameLost() - matchResult.getGameLost());
        pr.setMatchWon(pr.getMatchWon() - 1);
        if (pr.getMatchWon() == 0) {
            pr.setMatchWonPercent(new BigDecimal(0));
        } else {
            pr.setMatchWonPercent(new BigDecimal(pr.getMatchWon() * 100.0 / (pr.getMatchWon() + pr.getMatchLost())).setScale(1, BigDecimal.ROUND_UP));
        }

        if (pr.getGameWon() == 0) {
            pr.setGameWonPercent(new BigDecimal(0));
        } else {
            pr.setGameWonPercent(new BigDecimal(pr.getGameWon() * 100.0 / (pr.getGameWon() + pr.getGameLost())).setScale(1, BigDecimal.ROUND_UP));
        }
        updatePlayerResult(pr);


        pr = getPlayerResult(matchResult.getLoserId(), matchResult.getDivisionId());

        pr.setGameWon(pr.getGameWon() - matchResult.getGameLost());
        pr.setGameLost(pr.getGameLost() - matchResult.getGameWon());
        pr.setMatchLost(pr.getMatchLost() - 1);

        if (pr.getMatchWon() == 0) {
            pr.setMatchWonPercent(new BigDecimal(0));
        } else {
            pr.setMatchWonPercent(new BigDecimal(pr.getMatchWon() * 100.0 / (pr.getMatchWon() + pr.getMatchLost())).setScale(1, BigDecimal.ROUND_UP));
        }
        if (pr.getGameWon() == 0) {
            pr.setGameWonPercent(new BigDecimal(0));
        } else {
            pr.setGameWonPercent(new BigDecimal(pr.getGameWon() * 100.0 / (pr.getGameWon() + pr.getGameLost())).setScale(1, BigDecimal.ROUND_UP));
        }

        updatePlayerResult(pr);
    }

    public PlayerResult getPlayerResult(long playerId, long divisionId)
    {
        logger.info("Getting playerResult");
        EntityManager em = Startup.getEntityManager();
        try
        {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(PlayerResult.class);
            return (PlayerResult) criteria.add(Restrictions.eq("playerId", playerId))
                    .add(Restrictions.eq("divisionId", divisionId)).uniqueResult();
        }
        finally
        {
            em.close();
        }
    }

    public List<PlayerResultView> getPlayerResultViewsByDivision(long divisionId)
    {
        logger.info("Getting all playerResultView for the division");
        EntityManager em = Startup.getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT p FROM PlayerResultView p where p.divisionId = :divisionId " +
                    "order by match_won desc, match_won_percent desc, game_won_percent desc, match_lost desc")
                    .setParameter("divisionId", divisionId);
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

    public List<PlayerResultView> getPlayerResultViewsBySeason(long seasonId)
    {
        logger.info("Getting all playerResultView for the season");
        EntityManager em = Startup.getEntityManager();
        try
        {
            Query q = em.createQuery("SELECT p FROM PlayerResultView p where p.seasonId = :seasonId " +
                    "order by match_won desc, match_won_percent desc, game_won_percent desc, match_lost desc")
                    .setParameter("seasonId", seasonId);
            return q.getResultList();
        }
        finally
        {
            em.close();
        }
    }

}
