package com.rhc.jdg.provider;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;

public class JavaCacheProvider {
	private CacheContainer cacheContainer;

	protected CacheContainer getCacheContainer() {
		if (cacheContainer == null) {
			Configuration config = new ConfigurationBuilder()
				.jmxStatistics()
					.enable()
				.persistence().addSingleFileStore().purgeOnStartup(true)
				.build();
			cacheContainer = new DefaultCacheManager(config);
		}
		return cacheContainer;
	}

	public Cache<String, String> getCache(String identifier) {
		return getCacheContainer().getCache(identifier);
	}

	public Cache<String, String> getCache() {
		return getCacheContainer().getCache();
	}
}
