package com.yuan.utr.ws;

import com.yuan.utr.dao.DivisionDAO;
import com.yuan.utr.dao.LeagueDAO;
import com.yuan.utr.model.persistent.Division;
import com.yuan.utr.model.persistent.League;
import com.yuan.utr.model.persistent.Player;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


// @Path here defines class level path. Identifies the URI path that
// a resource class will serve requests for.
@Path("LeagueService")
public class LeagueService
{
	private static final Logger logger = Logger.getLogger(LeagueService.class.getName());

	private DivisionDAO divisionDAO = new DivisionDAO();
	private LeagueDAO leagueDAO = new LeagueDAO();
	private final static Integer UTR_LEAGUE_ID = 1;
	public final static String CATCH_ALL_DIVISION_NAME = "All";


	@GET
	@Path("/getCurrentDivisions")
	@Produces(MediaType.APPLICATION_JSON)
	public Division[] getCurrentDivisions() {
		League league = leagueDAO.getLeague(UTR_LEAGUE_ID);
		List<Division> result = divisionDAO.getDivisionsBySeason(league.getCurrentSeasonId());
		return result.toArray(new Division[result.size()]);
	}

	@GET
	@Path("/getCurrentCatchAllDivision")
	@Produces(MediaType.APPLICATION_JSON)
	public Division getCurrentCatchAllDivision() {
		League league = leagueDAO.getLeague(UTR_LEAGUE_ID);
		return divisionDAO.getCatchAllDivisionBySeason(league.getCurrentSeasonId(), CATCH_ALL_DIVISION_NAME);
	}
}