package de.schaeferd.pulsar.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@Theme("pulsar-manager")
@SpringBootApplication
public class PulsarManager implements AppShellConfigurator
{
    static void main(String[] args)
    {
        SpringApplication.run(PulsarManager.class, args);
    }
}
