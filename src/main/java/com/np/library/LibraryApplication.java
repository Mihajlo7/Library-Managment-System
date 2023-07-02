package com.np.library;

import com.np.library.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class LibraryApplication {
	@Autowired
	private SeedService seedService;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void setUp(){
		seedService.init();
	}

}
