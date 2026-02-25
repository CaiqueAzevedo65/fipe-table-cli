package br.com.myproject.fipe;

import br.com.myproject.fipe.main.FipeConsole;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApplication implements CommandLineRunner {

   private final FipeConsole fipeConsole;

    public FipeApplication(FipeConsole fipeConsole) {
        this.fipeConsole = fipeConsole;
    }

    public static void main(String[] args) {
        SpringApplication.run(FipeApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) {
        fipeConsole.menu();
    }
}
