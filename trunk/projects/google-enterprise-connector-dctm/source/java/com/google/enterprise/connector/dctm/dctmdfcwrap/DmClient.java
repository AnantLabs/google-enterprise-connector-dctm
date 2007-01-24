package com.google.enterprise.connector.dctm.dctmdfcwrap;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.IDfLoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.IClient;
import com.google.enterprise.connector.dctm.dfcwrap.IId;
import com.google.enterprise.connector.dctm.dfcwrap.ILocalClient;
import com.google.enterprise.connector.dctm.dfcwrap.ILoginInfo;
import com.google.enterprise.connector.dctm.dfcwrap.ISessionManager;

import com.google.enterprise.connector.dctm.dfcwrap.ISession;

import com.google.enterprise.connector.dctm.dfcwrap.IQuery;
import com.google.enterprise.connector.spi.LoginException;
import com.google.enterprise.connector.spi.RepositoryException;

public class DmClient implements IClient{
	IDfClient idfClient;
	IDfClientX idfClientX;
	
	DmSessionManager idctmSessionManager = null;
	
	public DmClient() throws RepositoryException{
		try {
			idfClientX = new DfClientX();
			idfClient = idfClientX.getLocalClient();
		} catch (DfException de) {
			RepositoryException re = new LoginException(de);
			throw re;
		}
	}

	public DmClient(IDfClient idfClient, IDfClientX idfClientX) {		
		this.idfClient = idfClient;
		this.idfClientX = idfClientX;
	}
	
	public ILocalClient getLocalClientEx(){
//		System.out.println("--- DmClient getLocalClientEx ---");
		IDfClient idfClient=null;
		idfClient=DfClient.getLocalClientEx();
		return new DmLocalClient(idfClient);
	}
	
	public IQuery getQuery(){
		return new DmQuery(new DfQuery());
	}
	
	public ISession newSession(String docbase, ILoginInfo logInfo) throws RepositoryException {
		System.out.println("--- DmClient newSession ---");
		IDfSession sessionUser = null;
		IDfLoginInfo idfLogInfo= new DfLoginInfo();
		idfLogInfo.setUser(logInfo.getUser());
		idfLogInfo.setPassword(logInfo.getPassword());
		try {
			sessionUser = idfClient.newSession(docbase,idfLogInfo);
		} catch (DfException de) {
			LoginException re = new LoginException(de);
			throw re;
		}
		return new DmSession(sessionUser);
	}

	public boolean authenticate(String docbaseName, ILoginInfo loginInfo) {
		if (!(loginInfo instanceof DmLoginInfo)) {
			throw new IllegalArgumentException();
		}
		DmLoginInfo dctmLoginInfo = (DmLoginInfo) loginInfo;
		IDfLoginInfo idfLoginInfo=dctmLoginInfo.getIdfLoginInfo();
		try {
			this.idfClient.authenticate(docbaseName, idfLoginInfo);		
		} catch (DfException e) {
			return false;
		}
		return true;
		
	}

	public ILoginInfo getLoginInfo() {
		return new DmLoginInfo(idfClientX.getLoginInfo());
	}

	public IId getId(String value) {	
		
		return new DmId(this.idfClientX.getId(value));
	}

	public ISessionManager getSessionManager() {
		return idctmSessionManager;//new DmSessionManager(idfSessionManager);
	}

	public void setSessionManager(ISessionManager sessionManager) {		
		idctmSessionManager = (DmSessionManager)sessionManager;
	}

	public ISessionManager newSessionManager() {
		IDfSessionManager newSessionManager = idfClient.newSessionManager();
		DmSessionManager dctmSessionManager = new DmSessionManager(newSessionManager);
		return dctmSessionManager;
	}
}