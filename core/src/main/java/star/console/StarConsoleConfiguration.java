package star.console;

import io.hawt.embedded.Main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import star.app.StarAppConfiguration;

import com.google.common.base.Throwables;

@Configuration
@EnableConfigurationProperties({ServerProperties.class, StarConsoleProperties.class})
@Import(StarAppConfiguration.class)
public class StarConsoleConfiguration {
	
	@Bean
	public CommandLineRunner hawtioStarter(final ServerProperties serverProperties, final StarConsoleProperties starConsoleProperties) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				try {
					System.setProperty("hawtio.offline", "true");
					System.setProperty("hawtio.dirname", "/tmp/hawtio");
					System.setProperty("hawtio.authenticationEnabled", Boolean.toString(starConsoleProperties.isAuthenticationEnabled()));
					Main main = new Main();
					main.setPort(serverProperties.getPort());
					main.setWar("res/hawtio-web.war");
					main.run(false);
				} catch (Exception e) {
					throw Throwables.propagate(e);
				}
			}
		};
	}

}
