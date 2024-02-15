package listeners;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import utils.DBUtils;

/**
 * Application Lifecycle Listener implementation class DBConnectionMgr
 *
 */
@WebListener
public class DBConnectionMgr implements ServletContextListener {

   

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	 try {
    		 System.out.println("int ctx close : destroy db cn");
 			DBUtils.closeConnection();
 		} catch (SQLException e) {
 			System.out.println("err in listener "+getClass()+" "+e);
 		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        try {
        	System.out.println("int ctx init : opening db cn");
			DBUtils.openConnection();
		} catch (SQLException e) {
			System.out.println("err in listener "+getClass()+" "+e);
		}
    }
	
}
