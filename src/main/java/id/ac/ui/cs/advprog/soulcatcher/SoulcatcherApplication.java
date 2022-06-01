package id.ac.ui.cs.advprog.soulcatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SoulcatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoulcatcherApplication.class, args);
    }

}
