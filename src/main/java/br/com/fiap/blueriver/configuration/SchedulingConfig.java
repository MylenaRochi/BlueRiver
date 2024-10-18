package br.com.fiap.blueriver.configuration;

import br.com.fiap.blueriver.service.VerifyHazardousZonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Autowired
    private VerifyHazardousZonesService service;

    @Autowired
    private static final long FIXED_DELAY = 3 * 10000; // 5 segundos

    @Scheduled(fixedDelay = FIXED_DELAY)
    public void verifyHazardousZonesScheduler() {
        System.out.println("Verificando zonas de risco");
        service.verifyHazardousZones();
    }
}
