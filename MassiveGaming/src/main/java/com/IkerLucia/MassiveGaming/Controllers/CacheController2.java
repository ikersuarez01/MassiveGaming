package com.IkerLucia.MassiveGaming.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController2 {
	
	@Autowired
	private CacheManager cacheManager2;
	
	@GetMapping(value="/cache2")
	public Map<Object, Object> getCacheContent() {
		ConcurrentMapCacheManager cacheMgr = (ConcurrentMapCacheManager) cacheManager2;
		ConcurrentMapCache cache = (ConcurrentMapCache) cacheMgr.getCache("consolas");
		return cache.getNativeCache();
	}
}