package sam.struts.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import sam.struts.model.MessageStore;

public class HelloWorldAction extends ActionSupport implements SessionAware, ParameterNameAware {
	private static final long serialVersionUID = -5668222061666442088L;
	
	private MessageStore messageStore;
	private Map<String, Object> session;
	private static final String HELLO_COUNT = "helloCount";
	
	private String userName;
	
	public MessageStore getMessageStore() {
		return messageStore;
	}
	
	@Override
	public String execute() throws Exception {
		this.messageStore = new MessageStore();
		if(userName != null)
			messageStore.setUserName(userName);
		
		increaseHelloCount();
		return SUCCESS;
	}

	private void increaseHelloCount() {
		session.compute(HELLO_COUNT, (s,i) -> i == null ? 1 : (Integer)i + 1);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public boolean acceptableParameterName(String parameterName) {
		return !parameterName.contains("session") && !parameterName.contains("request");
	}
}
