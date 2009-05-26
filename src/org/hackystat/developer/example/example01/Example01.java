package org.hackystat.developer.example.example01;

import javax.xml.datatype.XMLGregorianCalendar;

import org.hackystat.sensorbase.client.SensorBaseClient;
import org.hackystat.sensorbase.resource.sensordata.jaxb.SensorDataIndex;
import org.hackystat.utilities.tstamp.Tstamp;

/**
 * A simple program illustrating basic use of the SensorBaseClient.
 * Uses credentials from http://code.google.com/p/hackystat/wiki/HackystatDemo.  
 * Similar to: http://csdl.ics.hawaii.edu/~johnson/screencasts/Hackystat.Programming.01.mov. 
 * @author Philip Johnson
 *
 */
public class Example01 {
  /** The public sensorbase host used for this example. */
  private static final String host = "http://dasha.ics.hawaii.edu:9876/sensorbase";
  /** The user (from HackystatDemo). */
  private static final String user = "joe.simpleportfolio@hackystat.org";
  /** For password (same as the user for test users-those with a hackystat.org extension). */
  private static final String password = user;
  
  /**
   * Returns true if the public sensorbase is available. 
   * @return True if the public sensorbase can be contacted. 
   */
  public boolean checkHost() {
    return SensorBaseClient.isHost(host);
  }
  
  /**
   * Returns true if our user and password are valid credentials for this host. 
   * @return True if the user and password are valid.
   */
  public boolean checkCredentials() {
    return SensorBaseClient.isRegistered(host, user, password);
  }
  
  /**
   * Returns the number of SensorData instances for our example user during a two week period.
   * @return The number of sensor data instances. 
   * @throws Exception If problems occur retrieving the data. 
   */
  public int getSensorDataCount() throws Exception {
    SensorBaseClient client = new SensorBaseClient(host, user, password);
    client.authenticate();
    String project = "Default";
    XMLGregorianCalendar startTime = Tstamp.makeTimestamp("2008-07-01");
    XMLGregorianCalendar endTime = Tstamp.makeTimestamp("2008-07-15");
    // Illustrate one way to get this info (ProjectSummary is another).
    SensorDataIndex index = client.getProjectSensorData(user, project, startTime, endTime);
    return index.getSensorDataRef().size();
  }

}
