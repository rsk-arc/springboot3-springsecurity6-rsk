package com.rsk.security.repository;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

@Repository
public class HikariDataSourcePoolDetail {

	private HikariDataSource dataSource;
	
//	private HikariPool dataPool;
	
	public void setDataSource(HikariDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public HikariDataSource getDataSource() {
		return dataSource;
	}

	public HikariPool getHikariPool() {
//		if(dataPool == null) {
//			dataPool = (HikariPool) new DirectFieldAccessor(dataSource).getPropertyValue("pool");			
//		}
		return (HikariPool) new DirectFieldAccessor(dataSource).getPropertyValue("pool");
	}

	public int getActiveConnections() {
		try {
			return getHikariPool().getActiveConnections();
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int getIdleConnections() {
		try {
			return getHikariPool().getIdleConnections();
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int getTotalConnections() {
		try {
			return getHikariPool().getTotalConnections();
		} catch (Exception ex) {
			return -1;
		}
	}
	
	public int getWaitingConnection() {
		try {
			return getHikariPool().getThreadsAwaitingConnection();
		} catch (Exception ex) {
			return -1;
		}
	}

	public int getMax() {
		return dataSource.getMaximumPoolSize();
	}
	
	
}