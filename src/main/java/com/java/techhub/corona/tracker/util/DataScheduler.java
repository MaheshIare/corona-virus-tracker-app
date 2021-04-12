/**
 * 
 */
package com.java.techhub.corona.tracker.util;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.java.techhub.corona.tracker.model.ConfirmedStats;
import com.java.techhub.corona.tracker.model.DeathStats;
import com.java.techhub.corona.tracker.model.RecoveredStats;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahes
 *
 */
@Slf4j
@Service
public class DataScheduler {

	@Value("${corona.global.confirmed.data.path}")
	private String confirmedDataUrl;

	@Value("${corona.global.deaths.data.path}")
	private String deathDataUrl;

	@Value("${corona.global.recovery.data.path}")
	private String recoveryDataUrl;

	@Autowired
	private HttpClient httpClient;

	private static final String CRON_EXPRESSION = "0 0 0 * * ?";

	@Cacheable(value = "confirms")
	@EventListener(value = { ApplicationReadyEvent.class })
	@Scheduled(cron = CRON_EXPRESSION)
	public List<ConfirmedStats> getConfirmedCasesStats() throws IOException, InterruptedException {
		log.info("Fetching confirmed cases data from global data services");
		List<ConfirmedStats> newStats = new ArrayList<>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(confirmedDataUrl)).build();
		HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		records.forEach(record -> {
			ConfirmedStats locationStat = new ConfirmedStats();
			String state = StringUtils.isAllBlank(record.get("Province/State")) ? "<State info Not available>"
					: record.get("Province/State");
			String country = StringUtils.isAllBlank(record.get("Country/Region")) ? "<Country info Not available>"
					: record.get("Country/Region");
			locationStat.setState(state);
			locationStat.setCountry(country);
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLatestTotalCases(latestCases);
			locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
			newStats.add(locationStat);
		});
		return newStats;
	}

	@Cacheable(value = "recoveries")
	@EventListener(value = { ApplicationReadyEvent.class })
	@Scheduled(cron = CRON_EXPRESSION)
	public List<RecoveredStats> getRecoveredCasesStats() throws IOException, InterruptedException {
		log.info("Fetching recovery cases data from global data services");
		List<RecoveredStats> newStats = new ArrayList<>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(recoveryDataUrl)).build();
		HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		records.forEach(record -> {
			RecoveredStats locationStat = new RecoveredStats();
			String state = StringUtils.isAllBlank(record.get("Province/State")) ? "<State info Not available>"
					: record.get("Province/State");
			String country = StringUtils.isAllBlank(record.get("Country/Region")) ? "<Country info Not available>"
					: record.get("Country/Region");
			locationStat.setState(state);
			locationStat.setCountry(country);
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLatestTotalCases(latestCases);
			locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
			newStats.add(locationStat);
		});
		return newStats;
	}

	@Cacheable(value = "deaths")
	@EventListener(value = { ApplicationReadyEvent.class })
	@Scheduled(cron = CRON_EXPRESSION)
	public List<DeathStats> getDeathCasesStats() throws IOException, InterruptedException {
		log.info("Fetching death cases data from global data services");
		List<DeathStats> newStats = new ArrayList<>();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(deathDataUrl)).build();
		HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		records.forEach(record -> {
			DeathStats locationStat = new DeathStats();
			String state = StringUtils.isAllBlank(record.get("Province/State")) ? "<State info Not available>"
					: record.get("Province/State");
			String country = StringUtils.isAllBlank(record.get("Country/Region")) ? "<Country info Not available>"
					: record.get("Country/Region");
			locationStat.setState(state);
			locationStat.setCountry(country);
			int latestCases = Integer.parseInt(record.get(record.size() - 1));
			int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
			locationStat.setLatestTotalCases(latestCases);
			locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
			newStats.add(locationStat);
		});
		return newStats;
	}
}
