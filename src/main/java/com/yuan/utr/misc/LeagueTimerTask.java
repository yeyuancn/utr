package com.yuan.utr.misc;

import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Created by v644593 on 5/20/2016.
 */
public class LeagueTimerTask extends TimerTask
{
    private static final Logger logger = Logger.getLogger(LeagueTimerTask.class.getName());

    @Override
    public void run()
    {
        logger.info("League timer task kicking off");
        /*
        long numberLeague = leagueDao.getLeagueNumber();
        long numberPlayer = playerDao.getPlayerNumber();
        long numberMatch = matchResultDao.getMatchResultNumber();
        MailUtil.sendCommentEmail("internal_league", "Current # of leagues:  " + numberLeague
                + "\n # of players:  " + numberPlayer
                + "\n # of matches:  " + numberMatch);
                */
    }
}
