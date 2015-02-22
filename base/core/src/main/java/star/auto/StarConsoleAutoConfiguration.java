package star.auto;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import star.console.StarConsoleConfiguration;

@Configuration
@Import(StarConsoleConfiguration.class)
public class StarConsoleAutoConfiguration {

}
