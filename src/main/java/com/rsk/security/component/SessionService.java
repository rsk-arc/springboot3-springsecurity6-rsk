package com.rsk.security.component;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rsk.security.dao.SessionBean;

@Component
public class SessionService {

	private final Map<String, SessionBean> sessionsTable = new Hashtable<String, SessionBean>();

	public synchronized void clearSession() {
		sessionsTable.clear();
	}

	public synchronized void addSession(SessionBean sessionBean) {
		deleteSession(sessionBean.getSessionToken());
		sessionsTable.put(sessionBean.getSessionToken(), sessionBean);
	}

	public synchronized void deleteSession(String sessionToken) {
		sessionsTable.remove(sessionToken);
	}

	public synchronized SessionBean getSession(String sessionToken) {
		if (sessionsTable != null) {
			return (SessionBean) sessionsTable.get(sessionToken);
		} else {
			return null;
		}
	}

	public synchronized SessionBean getSession(long employeeId) {
		SessionBean sessionBean = null;
		boolean loop = false;
		for (Map.Entry<String, SessionBean> entry : sessionsTable.entrySet()) {
			if (!loop) {
				sessionBean = (SessionBean) entry.getValue();
				if (sessionBean.getEmployeeId() == employeeId) {
					loop = true;
				}
			}
		}
		return sessionBean;
	}

}
