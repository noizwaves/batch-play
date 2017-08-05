package io.noizwaves.batchplay.naivejdbc.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ETLApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(ETLApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ETLApplication.class, args);
    }

	private final ProcessingCoordinator processingCoordinator;

    @Autowired
    public ETLApplication(ProcessingCoordinator processingCoordinator) {
        this.processingCoordinator = processingCoordinator;
    }

    @Override
	public void run(String... args) throws Exception {
		int results = processingCoordinator.runGadgetEtl();

		log.info("Transformed Gadgets [" + results + " record(s)]");
	}
}
