package com.polyfrontiere.service.forward;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class PostForwardService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostForwardService.class);
	private static final String URL = "https://customs-post-app.herokuapp.com/set_id_cache";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void forwardIDCache(List<String> ids) throws RestClientException, URISyntaxException {
		LOGGER.info("Forwarding (to " + URL + ") passport IDs: " + ids);
		restTemplate.postForObject(new URI(URL), ids.toArray(new String[ids.size()]), String.class);
	}
}
