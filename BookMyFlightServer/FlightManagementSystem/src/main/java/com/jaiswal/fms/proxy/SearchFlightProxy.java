package com.jaiswal.fms.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="search-flights", url="localhost:8100")
public interface SearchFlightProxy {
	
	@GetMapping("/search-flights/all-airlines")
	public List<String> getAirlines();

}
