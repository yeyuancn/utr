package com.yuan.utr.misc;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Startup implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(Startup.class.getName());
	
	private static EntityManagerFactory factory = null;
	
    private static final String PERSISTENCE_UNIT_NAME = "utr";
    
    @Override
    public synchronized void contextInitialized(ServletContextEvent event) {
        logger.info("************Running startup for UTR League************");
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);


		TimerTask timerTask = new LeagueTimerTask();
		Timer timer = new Timer(true);
		long fourHourMill = 3600l * 1000 * 4;
		timer.scheduleAtFixedRate(timerTask, 3000, fourHourMill);
		logger.info("Scheduled monitor service for every four hour");
	}
    
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public static EntityManager getEntityManager()
	{
		return factory.createEntityManager();
	}
}