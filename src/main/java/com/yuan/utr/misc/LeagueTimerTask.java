package com.yuan.utr.misc;

import com.yuan.utr.dao.AccountDAO;
import com.yuan.utr.dao.DivisionDAO;
import com.yuan.utr.dao.MatchResultDAO;
import com.yuan.utr.dao.PlayerDAO;
import com.yuan.utr.dao.PlayerResultDAO;
import com.yuan.utr.ws.util.email.MailUtil;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Created by v644593 on 5/20/2016.
 */
public class LeagueTimerTask extends TimerTask {
    private static final Logger logger = Logger.getLogger(LeagueTimerTask.class.getName());

    private PlayerDAO playerDao = new PlayerDAO();
    private DivisionDAO divisionDao = new DivisionDAO();
    private PlayerResultDAO playerResultDAO = new PlayerResultDAO();
    private AccountDAO accountDAO = new AccountDAO();
    private MatchResultDAO matchResultDao = new MatchResultDAO();

    @Override
    public void run() {
        logger.info("League timer task kicking off");

        long numberPlayer = playerDao.getPlayerNumber();
        long numberMatch = matchResultDao.getMatchResultNumber();
        long numberAccount = accountDAO.getAccountNumber();
        long numberDivision = divisionDao.getDivisionNumber();
        long numberPlayerResult = playerResultDAO.getPlayerResultNumber();
        MailUtil.sendSystemEmail("Current  # of players:  " + numberPlayer
                + "\n # of accounts:  " + numberAccount
                + "\n # of matches:  " + numberMatch
                + "\n # of playerresults:  " + numberPlayerResult
                + "\n # of divisions:  " + numberDivision);
    }
}
