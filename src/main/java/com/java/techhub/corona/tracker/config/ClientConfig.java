/**
 * 
 */
package com.java.techhub.corona.tracker.config;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mahes
 *
 */
@Configuration
public class ClientConfig {

	@Value("${client.connection.timeout:30000}")
	private Integer connectionTimeout;

	@Bean
	public HttpClient httpClient() {
		return HttpClient.newBuilder().connectTimeout(Duration.ofMillis(connectionTimeout)).executor(executor())
				.build();
	}

	@Bean
	public Executor executor() {
		final ThreadFactory threadFactory = runnable -> {
			final Thread thread = new Thread(runnable, "coronaTrackerThread");
			thread.setDaemon(true);
			return thread;
		};
		return Executors.newCachedThreadPool(threadFactory);
	}
}
