package star.console;

import io.hawt.embedded.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.google.common.base.Throwables;

@Configuration
@EnableConfigurationProperties({ServerProperties.class, StarConsoleProperties.class})
public class StarConsoleConfiguration {
	
	
	@Component
	public static class HawtioStarter implements ApplicationListener<ContextRefreshedEvent> {
		
		@Autowired
		ServerProperties serverProperties;
		
		@Autowired
		StarConsoleProperties starConsoleProperties;
		
		@Override
		public void onApplicationEvent(ContextRefreshedEvent event) {
			try {
				System.setProperty("hawtio.offline", "true");
				System.setProperty("hawtio.dirname", "/tmp/hawtio");
				System.setProperty("hawtio.authenticationEnabled", Boolean.toString(starConsoleProperties.isAuthenticationEnabled()));
				Main main = new Main();
				if (serverProperties.getPort()!=null) {
					main.setPort(serverProperties.getPort());
				}
				main.setWar("res/hawtio-web.war");
				main.run(false);
			} catch (Exception e) {
				throw Throwables.propagate(e);
			}
		}
	}
	

}
