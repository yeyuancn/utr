package com.yuan.utr.ws.util;


import com.yuan.utr.model.persistent.MatchResult;
import com.yuan.utr.ws.exception.AppValidationException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by V644593 on 2/17/2016.
 */
public class MatchResultHelperTest
{
    @Test
    public void happyPath() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1);
        origResult.setLoserId(2);
        origResult.setSet1Score("1:6");
        origResult.setSet2Score("7:6");
        origResult.setSet3Score("7:6");

        MatchResult result = MatchResultHelper.decorateMatchResult(origResult);

        assertEquals(result.getWinnerId(), 1);
        assertEquals(result.getLoserId(), 2);
        assertEquals(result.getSet1Score(), "1:6");
        assertEquals(result.getSet2Score(), "7:6");
        assertEquals(result.getSet3Score(), "7:6");
        assertEquals(result.getGameWon(), 15);
        assertEquals(result.getGameLost(), 18);
    }

    @Test(expected = AppValidationException.class)
    public void loserEnterResult() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("1:6");
        origResult.setSet2Score("0:6");
        origResult.setSet3Score(":");
        MatchResultHelper.decorateMatchResult(origResult);
    }

    @Test(expected = AppValidationException.class)
    public void cannotDetermineResult() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("1:6");
        origResult.setSet2Score("6:1");
        origResult.setSet3Score(":");
        MatchResultHelper.decorateMatchResult(origResult);
    }

    @Test(expected = AppValidationException.class)
    public void cannotDetermineResult2() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("0:0");
        origResult.setSet2Score("4:4");
        origResult.setSet3Score(":");
        MatchResultHelper.decorateMatchResult(origResult);
    }

    @Test
    public void loserDefaultLosing() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("1:6");
        origResult.setSet2Score("0:6");
        origResult.setSet3Score(":");
        origResult.setLoserDefault(true);
        MatchResult result = MatchResultHelper.decorateMatchResult(origResult);

        assertEquals(result.getWinnerId(), 1000);
        assertEquals(result.getLoserId(), 2000);
        assertEquals(result.getSet1Score(), "1:6");
        assertEquals(result.getSet2Score(), "0:6");
        assertEquals(result.getSet3Score(), "");
        assertEquals(result.getGameWon(), 1);
        assertEquals(result.getGameLost(), 12);
    }

    @Test
    public void loserDefaultWinning() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("6:1");
        origResult.setSet2Score("0:2");
        origResult.setSet3Score(":");
        origResult.setLoserDefault(true);
        MatchResult result = MatchResultHelper.decorateMatchResult(origResult);

        assertEquals(result.getWinnerId(), 1000);
        assertEquals(result.getLoserId(), 2000);
        assertEquals(result.getSet1Score(), "6:1");
        assertEquals(result.getSet2Score(), "0:2");
        assertEquals(result.getSet3Score(), "");
        assertEquals(result.getGameWon(), 6);
        assertEquals(result.getGameLost(), 3);
    }

    @Test
    public void loserDefaultDraw() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("7:6");
        origResult.setSet2Score("6:7");
        origResult.setSet3Score("2:2");
        origResult.setLoserDefault(true);
        MatchResult result = MatchResultHelper.decorateMatchResult(origResult);

        assertEquals(result.getWinnerId(), 1000);
        assertEquals(result.getLoserId(), 2000);
        assertEquals(result.getSet1Score(), "7:6");
        assertEquals(result.getSet2Score(), "6:7");
        assertEquals(result.getSet3Score(), "2:2");
        assertEquals(result.getGameWon(), 15);
        assertEquals(result.getGameLost(), 15);
    }


    @Test
    public void happyPathEmptyScore() throws AppValidationException
    {
        MatchResult origResult = new MatchResult();
        origResult.setWinnerId(1000);
        origResult.setLoserId(2000);
        origResult.setSet1Score("6:1");
        origResult.setSet2Score(":");
        origResult.setSet3Score("");

        MatchResult result = MatchResultHelper.decorateMatchResult(origResult);


        assertEquals(result.getWinnerId(), 1000);
        assertEquals(result.getLoserId(), 2000);
        assertEquals(result.getSet1Score(), "6:1");
        assertEquals(result.getSet2Score(), "");
        assertEquals(result.getSet3Score(), "");
        assertEquals(result.getGameWon(), 6);
        assertEquals(result.getGameLost(), 1);
    }
}
