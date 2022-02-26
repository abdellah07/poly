package com.polyfrontiere.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.polyfrontiere.service.refresh.DataRefreshService;

@Component
public class DataRefreshScheduledTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataRefreshScheduledTask.class);
	
	private static final int DELAY_HOURS = 24;
	
	@Autowired
	private DataRefreshService dataRefreshService;
	
	// note for cron: second (0-59) | minute (0 - 59) | hour (0 - 23) | day of the month (1 - 31) | month (1 - 12) (or JAN-DEC) | day of the week (0 - 7)
	@Scheduled(cron = "*/30 * * * * *")
	public void execute() {
		LOGGER.info("DataRefreshScheduledTask - Starting");
		
		dataRefreshService.refreshData(DELAY_HOURS);
	}
}
