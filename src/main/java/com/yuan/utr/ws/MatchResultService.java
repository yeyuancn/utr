package com.yuan.utr.ws;

import com.yuan.utr.dao.DivisionDAO;
import com.yuan.utr.dao.LeagueDAO;
import com.yuan.utr.dao.MatchResultDAO;
import com.yuan.utr.dao.PlayerResultDAO;
import com.yuan.utr.model.persistent.Division;
import com.yuan.utr.model.persistent.MatchResult;
import com.yuan.utr.model.persistent.MatchResultView;
import com.yuan.utr.model.persistent.PlayerResultView;
import com.yuan.utr.ws.exception.AppValidationException;
import com.yuan.utr.ws.util.MatchResultHelper;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


// @Path here defines class level path. Identifies the URI path that
// a resource class will serve requests for.
@Path("MatchResultService")
public class MatchResultService {
    private static final Logger logger = Logger.getLogger(MatchResultService.class.getName());


    private MatchResultDAO matchResultDao = new MatchResultDAO();
    private PlayerResultDAO playerResultDao = new PlayerResultDAO();
    private DivisionDAO divisionDAO = new DivisionDAO();

    @GET
    @Path("/matchResult/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public MatchResult getMatchResult(@PathParam("i") long id) {
        return matchResultDao.getMatchResult(id);
    }

    //----------------------

    @GET
    @Path("/matchResults/{divisionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MatchResultView[] getMatchResultViews(@PathParam("divisionId") long divisionId) {
        Division d = divisionDAO.getDivision(divisionId);
        if (d.getName().equals(LeagueService.CATCH_ALL_DIVISION_NAME)) {
            List<MatchResultView> matchResultViews = matchResultDao.getMatchResultViewsBySeason(d.getSeasonId());
            return matchResultViews.toArray(new MatchResultView[matchResultViews.size()]);
        } else {
            List<MatchResultView> matchResultViews = matchResultDao.getMatchResultViewsByDivision(divisionId);
            return matchResultViews.toArray(new MatchResultView[matchResultViews.size()]);
        }
    }


    @DELETE
    @Path("/deleteMatchResult/{i}")
    public void deleteMatchResult(@PathParam("i") long id) throws AppValidationException {
        MatchResult result = matchResultDao.getMatchResult(id);

        playerResultDao.deleteMatchResult(result);
        matchResultDao.deleteMatchResult(id);
    }

    @POST
    @Path("/addMatchResult")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addMatchResult(MatchResult result) throws AppValidationException {
        logger.info(result.getWinnerId() + " " + result.getLoserId() + " "
                + result.getSet1Score() + " " + result.getSet2Score() + " " + result.getSet3Score()
                + result.getMatchDate());

        result = MatchResultHelper.decorateMatchResult(result);
        logger.info("updating match result table");
        Division d = divisionDAO.getDivision(result.getDivisionId());
        result.setSeasonId(d.getSeasonId());
        matchResultDao.addMatchResult(result);
        logger.info("updating player result table");
        playerResultDao.addMatchResult(result);
        logger.info("done updating results");
    }

    @GET
    @Path("/matchResultsForPlayer/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MatchResultView[] getMatchResultViewsForPlayer(@PathParam("playerId") long playerId) {
        List<MatchResultView> matchResultViews = matchResultDao.getMatchResultViewsForPlayer(playerId);
        return matchResultViews.toArray(new MatchResultView[matchResultViews.size()]);
    }

    @GET
    @Path("/playerResults/{divisionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerResultView[] getPlayerResultViews(@PathParam("divisionId") long divisionId)
    {
        Division d = divisionDAO.getDivision(divisionId);
        if (d.getName().equals(LeagueService.CATCH_ALL_DIVISION_NAME)) {
            List<PlayerResultView> playerResultViews = playerResultDao.getPlayerResultViewsBySeason(d.getSeasonId());
            return playerResultViews.toArray(new PlayerResultView[playerResultViews.size()]);
        } else {
            List<PlayerResultView> playerResultViews = playerResultDao.getPlayerResultViewsByDivision(divisionId);
            return playerResultViews.toArray(new PlayerResultView[playerResultViews.size()]);
        }
    }
}