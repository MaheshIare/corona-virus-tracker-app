/**
 * 
 */
package com.java.techhub.corona.tracker.service;

import java.io.IOException;
import java.util.List;

import com.java.techhub.corona.tracker.model.ConfirmedStats;
import com.java.techhub.corona.tracker.model.DeathStats;
import com.java.techhub.corona.tracker.model.RecoveredStats;

/**
 * @author mahes
 *
 */
public interface DataHelperService {

	List<ConfirmedStats> getAllConfirmedStats() throws IOException, InterruptedException;

	List<RecoveredStats> getAllRecoveredStats() throws IOException, InterruptedException;

	List<DeathStats> getAllDeathStats() throws IOException, InterruptedException;
}
