package star.console;

import io.hawt.embedded.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import star.app.StarAppConfiguration;

import com.google.common.base.Throwables;

@Configuration
@EnableConfigurationProperties({ServerProperties.class, StarConsoleProperties.class})
@Import(StarAppConfiguration.class)
public class StarConsoleConfiguration {
	
	@Component
	public static class HawtioStarter implements CommandLineRunner {
		
		@Autowired
		ServerProperties serverProperties;
		
		@Autowired
		StarConsoleProperties starConsoleProperties;

		@Override
		public void run(String... args) throws Exception {
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
