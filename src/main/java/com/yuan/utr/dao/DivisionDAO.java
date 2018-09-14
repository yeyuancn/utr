package com.yuan.utr.dao;

import com.yuan.utr.misc.Startup;
import com.yuan.utr.model.persistent.Division;
import com.yuan.utr.model.persistent.Season;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;

public class DivisionDAO
{

    private static final Logger logger = Logger.getLogger(DivisionDAO.class.getName());


    public Division getDivision(long id)
    {
        logger.info("Getting division");
        EntityManager em = Startup.getEntityManager();
        try
        {
            return em.find(Division.class, id);
        }
        finally
        {
            em.close();
        }
    }

    public List<Division> getDivisionsBySeason(Long seasonId)
    {
        logger.info("Getting divisions in the same season");
        EntityManager em = Startup.getEntityManager();
        try
        {
            String query = "SELECT d FROM Division d WHERE d.seasonId = :seasonId";

            return (List<Division>) em.createQuery(query)
                    .setParameter("seasonId", seasonId)
                    .getResultList();
        }
        finally
        {
            em.close();
        }
    }

}
