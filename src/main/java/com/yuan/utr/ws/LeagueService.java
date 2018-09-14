package com.yuan.utr.ws;

import com.yuan.utr.dao.DivisionDAO;
import com.yuan.utr.model.persistent.Division;
import com.yuan.utr.model.persistent.Player;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
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


	@POST
	@Path("/allDivisions")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Division[] getAllDivisions(Player player) {
		Division d = divisionDAO.getDivision(player.getDivisionId());
		List<Division> result = divisionDAO.getDivisionsBySeason(d.getSeasonId());
		return result.toArray(new Division[result.size()]);
	}
}