/**
 * 
 */
package com.java.techhub.corona.tracker.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.techhub.corona.tracker.model.ConfirmedStats;
import com.java.techhub.corona.tracker.model.DeathStats;
import com.java.techhub.corona.tracker.model.RecoveredStats;
import com.java.techhub.corona.tracker.service.DataHelperService;
import com.java.techhub.corona.tracker.util.DataScheduler;

/**
 * @author mahes
 *
 */
@Service
public class DataHelperServiceImpl implements DataHelperService {

	@Autowired
	private DataScheduler dataScheduler;

	@Override
	public List<ConfirmedStats> getAllConfirmedStats() throws IOException, InterruptedException {
		return dataScheduler.getConfirmedCasesStats();
	}

	@Override
	public List<RecoveredStats> getAllRecoveredStats() throws IOException, InterruptedException {
		return dataScheduler.getRecoveredCasesStats();
	}

	@Override
	public List<DeathStats> getAllDeathStats() throws IOException, InterruptedException {
		return dataScheduler.getDeathCasesStats();
	}

}
