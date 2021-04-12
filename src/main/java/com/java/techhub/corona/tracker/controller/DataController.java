/**
 * 
 */
package com.java.techhub.corona.tracker.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.techhub.corona.tracker.model.ConfirmedStats;
import com.java.techhub.corona.tracker.model.DeathStats;
import com.java.techhub.corona.tracker.model.RecoveredStats;
import com.java.techhub.corona.tracker.service.DataHelperService;

/**
 * @author mahes
 *
 */
@Controller
public class DataController {

	@Autowired
	DataHelperService dataHelperService;

    @GetMapping("/confirms")
    public String confirmed(Model model) throws IOException, InterruptedException {
        List<ConfirmedStats> allStats = dataHelperService.getAllConfirmedStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "confirmed";
    }
    
    @GetMapping("/recoveries")
    public String recoveries(Model model) throws IOException, InterruptedException {
        List<RecoveredStats> allStats = dataHelperService.getAllRecoveredStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalRecoveredCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "recovered";
    }
    
    @GetMapping("/deaths")
    public String deaths(Model model) throws IOException, InterruptedException {
        List<DeathStats> allStats = dataHelperService.getAllDeathStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalDeathCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "deaths";
    }
}
