package star.console;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "star-console")
public class StarConsoleProperties {
	
	private boolean authenticationEnabled = false;

	public boolean isAuthenticationEnabled() {
		return authenticationEnabled;
	}

	public void setAuthenticationEnabled(boolean authenticationEnabled) {
		this.authenticationEnabled = authenticationEnabled;
	}

}
