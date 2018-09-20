package com.yuan.utr.ws;

import com.yuan.utr.dao.AccountDAO;
import com.yuan.utr.dao.PlayerDAO;
import com.yuan.utr.dao.SessionDAO;
import com.yuan.utr.model.persistent.Account;
import com.yuan.utr.model.persistent.Player;
import com.yuan.utr.model.persistent.Session;
import com.yuan.utr.ws.exception.AppValidationException;
import com.yuan.utr.ws.util.email.template.JoinLeagueEmail;
import com.yuan.utr.ws.util.email.template.PasswordResetEmail;
import org.apache.log4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;


// @Path here defines class level path. Identifies the URI path that
// a resource class will serve requests for.
@Path("AccountService")
public class AccountService {
    private static final Logger logger = Logger.getLogger(AccountService.class.getName());

    private AccountDAO accountDao = new AccountDAO();

    private PlayerDAO playerDao = new PlayerDAO();

    private SessionDAO sessionDAO = new SessionDAO();

    private static Random random = new Random();

    @GET
    @Path("/account/{i}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("i") long id) {
        return accountDao.getAccount(id);
    }

    @POST
    @Path("/updateAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateAccount(Account a) throws AppValidationException {
        accountDao.updateAccount(a);
    }

    @GET
    @Path("/accountByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountByEmail(@PathParam("email") String email) {
        return accountDao.getAccountByEmail(email);
    }


    //--------------------------------------------------
    @POST
    @Path("/createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Session createAccount(Account a) throws AppValidationException {
        // First check if firstName/lastName is valid for the new account to pick up
        Player player = playerDao.getPlayerByName(a.getFirstName(), a.getLastName());
        if (player.getDivisionId() == null || player.getDivisionId() == 0) {
            throw new AppValidationException("This player is not registered in any league, cannot create an account.");
        }

        // Try to create the account
        accountDao.createAccount(a);
        // Grab the new account created
        Account account = accountDao.getAccountByEmail(a.getEmail());
        if (account == null) {
            throw new AppValidationException("Unable to create account, contact league director for help.");
        }
        // Associate the new account with the Player
        player.setAcctId(account.getId());
        playerDao.updatePlayer(player);

        new JoinLeagueEmail(a.getEmail(), player.getFirstName()).sendEmail();

        return sessionDAO.createSessionForPlayer(player.getId());
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Session login(Account a) throws AppValidationException {
        Account account = accountDao.login(a.getEmail(), a.getPassword());

        if (account == null) {
            throw new AppValidationException("Unable to login, contact league director for help.");
        }
        Player p = playerDao.getPlayerByAcctId(account.getId());
        if (p.getDivisionId() == null || p.getDivisionId() == 0) {
            throw new AppValidationException("This player is not registered in any league, cannot login.");
        }

        return sessionDAO.createSessionForPlayer(p.getId());
    }

    @POST
    @Path("/resetPasswordRequest")
    @Consumes(MediaType.APPLICATION_JSON)
    public void resetPasswordRequest(String email) throws AppValidationException {
        int resetKey = random.nextInt(10000);
        Account account = accountDao.resetPasswordRequest(email, resetKey);
        new PasswordResetEmail(account.getEmail(), account.getId(), resetKey).sendEmail();
    }

    @GET
    @Path("/verifyResetPassword/{accountId}/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountByIdAndKey(@PathParam("accountId") long accountId, @PathParam("key") int key) throws AppValidationException {
        return accountDao.getAccountByIdAndKey(accountId, key);
    }

    @POST
    @Path("/updatePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePassword(Account account) throws AppValidationException {
        accountDao.updatePassword(account);
    }

    @POST
    @Path("/validateUUID")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Player validateUuid(Session session) throws AppValidationException {
        Session s = sessionDAO.getActiveSessionByUUID(session.getUuid());
        return playerDao.getPlayer(s.getPlayerId());
    }
}