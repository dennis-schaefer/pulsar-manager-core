package de.schaeferd.pulsar.manager;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PulsarContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration
{
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("../")
            .ignoreIfMissing()
            .load();

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer()
    {
        var timescaleVersion = dotenv.get("TIMESCALEDB_VERSION");
        var image = DockerImageName.parse("timescale/timescaledb:" + timescaleVersion)
                .asCompatibleSubstituteFor("postgres");
        return new PostgreSQLContainer<>(image);
    }

    @Bean
    @ServiceConnection
    PulsarContainer pulsarContainer()
    {
        var pulsarVersion = dotenv.get("PULSAR_VERSION");
        return new PulsarContainer(DockerImageName.parse("apachepulsar/pulsar:" + pulsarVersion));
    }
}
