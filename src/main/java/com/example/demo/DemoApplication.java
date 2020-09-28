package com.example.demo;

import com.example.demo.domain.ArticleWriter;
import com.example.demo.repositories.ArticleWriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@SpringBootApplication
@EnableJpaRepositories
public class DemoApplication implements CommandLineRunner {
	List<ArticleWriter> articleWriters = Arrays.asList(
			new ArticleWriter(null, "Sambaly KOTE"),
			new ArticleWriter(null, "Issa NDIAYE"),
			new ArticleWriter(null, "Boubacar Mignane BASSE"),
			new ArticleWriter(null, "Cheikh Tidjane ZOUNGRANA"),
			new ArticleWriter(null, "Aziz"),
			new ArticleWriter(null, "Elimane LY"),
			new ArticleWriter(null, "Fodé SANE"),
			new ArticleWriter(null, "Seynabou NDIAYE")
	);

	@Autowired
	private ArticleWriterRepository articleWriterRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		articleWriterRepository.saveAll(articleWriters);
	}
}
