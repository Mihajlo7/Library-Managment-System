package com.np.library;

import com.np.library.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * Klasa koja generise main metodu
 * @author Mihajlo
 */
@SpringBootApplication
public class LibraryApplication {
	/**
	 * Instanca servisa za ubacivanje podataka
	 */
	@Autowired
	private SeedService seedService;
	/**
	 * atribut za odobravanje seed-ova
	 */
	@Value("${seed.enabled}")
	private boolean enabled;

	/**
	 * Main metoda
	 * @param args string
	 */

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	/**
	 * Metoda pokrece seed-ovanje
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void setUp(){
		if(enabled)
			seedService.init();
	}

}
