package com.yuan.utr.ws.util;

import com.yuan.utr.model.persistent.MatchResult;
import com.yuan.utr.ws.exception.AppValidationException;

import java.util.Date;

public class MatchResultHelper {

	/**
	 * Decorate the match result when receiving it from the Portal.
	 * This process should be run only once!
	 *
	 * @param result
	 * @return
	 */
	public static MatchResult decorateMatchResult(MatchResult result) throws AppValidationException
	{
		validateMatchResult(result);

		int setWon = 0, setLost = 0;
		if (isWinnerScore(result.getSet1Score()) == 1)
		{
			setWon ++;
		}
		else if (isWinnerScore(result.getSet1Score()) == -1)
		{
			setLost ++;
		}
		if (isWinnerScore(result.getSet2Score()) == 1)
		{
			setWon ++;
		}
		else if (isWinnerScore(result.getSet2Score()) == -1)
		{
			setLost ++;
		}
		if (isWinnerScore(result.getSet3Score()) == 1)
		{
			setWon ++;
		}
		else if (isWinnerScore(result.getSet3Score()) == -1)
		{
			setLost ++;
		}
		result.setMatchScore(setWon + ":" + setLost);
		result.setRecordTime(new Date());
		processSetResult(result, result.getSet1Score());
		processSetResult(result, result.getSet2Score());
		processSetResult(result, result.getSet3Score());
		return result;
	}

	private static void validateMatchResult(MatchResult result) throws AppValidationException
	{

		if (result.getSet1Score()!= null && result.getSet1Score().trim().equals(":"))
		{
			result.setSet1Score("");
		}
		if (result.getSet2Score()!= null && result.getSet2Score().trim().equals(":"))
		{
			result.setSet2Score("");
		}
		if (result.getSet3Score()!= null && result.getSet3Score().trim().equals(":"))
		{
			result.setSet3Score("");
		}
		String score1 = result.getSet1Score();
		validateSetResult(score1);
		String score2 = result.getSet2Score();
		validateSetResult(score2);
		String score3 = result.getSet3Score();
		validateSetResult(score3);

		int count = isWinnerScore(score1) + isWinnerScore(score2) + isWinnerScore(score3);

		if (count == 0 && result.getLoserDefault() == false)
		{
			throw new AppValidationException("Invalid Score, Cannot determine the winner!");
		}
		if (count < 0 && result.getLoserDefault() == false)
		{
			throw new AppValidationException("Invalid Score, Only winner can submit score!");
		}
	}

	private static void validateSetResult(String score) throws AppValidationException
	{
		if (score == null)
		{
			throw new AppValidationException("Invalid Set Score: null ");
		}
		score = score.trim();
		if(score.split(":").length != 2 && !score.equals(""))
		{
			throw new AppValidationException("Invalid Set Score : " + score);
		}
		if (score.split(":").length == 2)
		{
			try
			{
				int gameWon = Integer.valueOf(score.split(":")[0]);
				int gameLost = Integer.valueOf(score.split(":")[1]);

				if (gameWon < 0 || gameWon > 10 || gameLost < 0 || gameLost > 10)
				{
					throw new AppValidationException("Invalid Set Score : " + score);
				}
			} catch (NumberFormatException nfe)
			{
				throw new AppValidationException("Invalid Set Score : " + score);
			}
		}
	}

	
	private static void processSetResult(MatchResult result, String setResult)
	{
		if (setResult.trim().length() > 0 && setResult.split(":").length == 2)
		{
			int gameWon = Integer.valueOf(setResult.split(":")[0]);
			int gameLost = Integer.valueOf(setResult.split(":")[1]);
			result.setGameWon(result.getGameWon() + gameWon);
			result.setGameLost(result.getGameLost() + gameLost);
		}
	}
	
	private static int isWinnerScore(String score)
	{
		String[] parts = score.split(":");
		if (parts.length != 2)
		{
			return 0;
		}
		else
		{
			if (Integer.valueOf(parts[0]) > Integer.valueOf(parts[1]))
			{
				return 1;
			}
			else if (Integer.valueOf(parts[0]) == Integer.valueOf(parts[1]))
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
	}
}
