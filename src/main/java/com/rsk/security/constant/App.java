package com.rsk.security.constant;
import java.util.HashMap;
import java.util.Map;

public class App {

	public static final String SERVER_URI = "http://localhost:8080";
	public static final String CROSS_ORIGIN = "http://localhost";

	public static final String DATE_DISPLAY_FORMAT = "dd-MMM-yyyy";
	public static final String DATE_TIME_DISPLAY_FORMAT = "dd-MMM-yyyy hh:mm a";
	public static final String DATE_TIME_DISPLAY_FORMAT_API = "yyyy-MM-dd hh:mm a";
	public static final String DATE_SAVE_FORMAT = "d-MMM-yyyy";
	public static final String DATE_TIME_SAVE_FORMAT = "dd-MMM-yyyy hh:mm a";
	public static final String DATE_FROM_UI_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String DATE_TIME_SEC_DISPLAY_FORMAT = "dd-MMM-yyyy hh:mm:ss a";

	private static Map<Integer, String> userType = new HashMap<Integer, String>();
	static {
		userType.put(EmployeeType.OWNER, "Owner");
		userType.put(EmployeeType.MANAGER, "Manager");
		userType.put(EmployeeType.EMPLOYEE, "Employee");
		userType.put(EmployeeType.ACCOUNTS, "Accounts");
		userType.put(EmployeeType.CONTACTS, "Contacts");
	}
	
	public static class Config {
		public static final String COMPANY_LOGO = "company_logo";
		public static final String PRODUCT_VERSION = "product_version";
		public static final String APPLICATION_WEB_URL = "application_web_url";
		public static final String APPLICATION_WEB_NAME = "application_web_name";

		/* folder */
		public static final String ROOT_FOLDER_NAME = "root_folder_name";
		public static final String WEB_FOLDER_NAME = "web_folder_name";
		public static final String TEMP_FOLDER_NAME = "temp_folder_name";
		public static final String TEMPLATE_FOLDER_NAME = "template_folder_name";
		public static final String IMAGES_FOLDER_NAME = "images_folder_name";
		public static final String ATTACHMENT_FOLDER_NAME = "attachment_folder_name";
		
		/* start time */
		public static final String SERVER_START_DATE_TIME = "server_start_date_time";
	}

	public static class Return {
		public static final int NONE = 0;
		public static final int FAILURE = -1;
		public static final int SUCCESS = 1;
		public static final int SENDMESSAGE = 2;
		public static final int EXCEPTION = 3;
		public static final int MANDATORYERROR = 4;
		public static final int DONTFORWORD = 50;
		public static final int DEADLOCK_CONCUR_UPDATE = -100;
		public static final int MAINTENANCE_INPROGRESS = -1000;
	}

	public static class ClientType {
		public static final int BUYER = 1;
		public static final int MSP = 2;
		public static final int SUPPLIER = 3;
	}

	public static class EmployeeType {
		public static final int OWNER = 1;
		public static final int MANAGER = 2;
		public static final int EMPLOYEE = 3;
		public static final int ACCOUNTS = 4;
		public static final int CONTACTS = 5;
	}
	
	public static String getEmployeeType(int type) {
		return userType.get(type);
	}
	
	public static class FormType {
		public static final int HEADER = 0;
		public static final int NEW = 1;
		public static final int VIEW = 2;
		public static final int EDIT = 3;
		public static final int GRID = 4;
	}

	public static class Session {
		public static final int ACTIVE = 0;
		public static final int EXPIRED = 1;
		public static final int LOGIN_MULTIPLE_ACCESS = 2;
		public static final int SESSION_LOST_ON_SERVE_RESTART = 3;
	}
	
}
