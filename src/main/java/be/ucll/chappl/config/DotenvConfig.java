package be.ucll.chappl.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./") // optional: specify the directory
                .ignoreIfMalformed() // optional
                .ignoreIfMissing()   // optional
                .load();
    }
}
