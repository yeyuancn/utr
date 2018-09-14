package com.yuan.utr.ws;

import com.yuan.utr.dao.AccountDAO;
import com.yuan.utr.dao.PlayerDAO;
import com.yuan.utr.model.persistent.Account;
import com.yuan.utr.model.persistent.Player;
import com.yuan.utr.ws.exception.AppValidationException;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


// @Path here defines class level path. Identifies the URI path that
// a resource class will serve requests for.
@Path("PlayerService")
public class PlayerService {
    private static final Logger logger = Logger.getLogger(PlayerService.class.getName());

    private PlayerDAO playerDao = new PlayerDAO();

    private AccountDAO accountDao = new AccountDAO();


    @POST
    @Path("/updatePlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePlayer(Player u) throws AppValidationException {
        playerDao.updatePlayer(u);
    }

    @DELETE
    @Path("/deletePlayer/{i}")
    public void deletePlayer(@PathParam("i") long id) {
        playerDao.deletePlayer(id);
    }


    @POST
    @Path("/loginSimple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account loginSimple(Account a) throws AppValidationException {
        return accountDao.login(a.getEmail(), a.getPassword());
    }

    // ------------------------------------------

    @POST
    @Path("/allOtherPlayers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Player[] getAllOtherPlayers(Player p) {
        List<Player> players = playerDao.getPlayersByDivision(p.getDivisionId());
        List<Player> result = new ArrayList<Player>();
        for (Player u : players) {
            if (u.getId() != p.getId()) {
                result.add(u);
            }
        }
        return result.toArray(new Player[result.size()]);
    }

    @GET
    @Path("/getPlayer/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("i") long id) {
        System.out.println("$$$$$$$$$$$$$$$");
        return playerDao.getPlayer(id);
    }
}