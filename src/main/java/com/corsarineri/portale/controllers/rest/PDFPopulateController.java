package com.corsarineri.portale.controllers.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsarineri.portale.model.Biblioteca;
import com.corsarineri.portale.repos.BibliotecaRepository;
import com.corsarineri.portale.utils.PDFReader;
@CrossOrigin
@RestController
public class PDFPopulateController {
	private Logger logger = LoggerFactory.getLogger(PDFPopulateController.class);
	private final BibliotecaRepository repository;
	PDFPopulateController(BibliotecaRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/pdfpopulate/{path}")
	public String pdfsearch(@PathVariable String path) {
		path = path.replaceAll("__", "/");
		String result ="";
		logger.info("Path: "+path);


		try (Stream<Path> walk = Files.walk(Paths.get(path))) {

			List<String> list = walk.map(x -> x.toString())
					.filter(f -> f.endsWith(".pdf"))
					.collect(Collectors.toList());

			list.forEach(file -> {
				logger.info("processing file " + file);

				try {
					File f = new File(file);
					String content = PDFReader.getText(f);
					content = bonifica(content.toLowerCase());
					logger.info("Content length: "+content.length());
					//logger.info("Content: " + content);
					if (content.length()>100) {
						Biblioteca b = new Biblioteca();
						b.setTag("");
						b.setTesto(content);
						b.setTitolo(f.getName());
						b.setUrl("");
						repository.save(b);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			});

		} catch (IOException e) {
			e.printStackTrace();
		}


		return result;
	}


	private String bonifica(String testo) {

		
//		Pattern pt = Pattern.compile("[^a-zA-Z0-9 ]");
//        Matcher match= pt.matcher(testo);
//        while(match.find())
//        {
//            String s= match.group();
//            testo=testo.replaceAll("\\"+s, "");
//        }
	     testo=testo.replace(",", " ");
		 testo=testo.replace(".", " ");
		 testo=testo.replace(";", " ");
		 testo=testo.replace(":", " ");
		 testo=testo.replace("-", " ");
		 testo=testo.replace("_", " ");
		 testo=testo.replace("(", " ");
		 testo=testo.replace(")", " ");
		 testo=testo.replace("[", " ");
		 testo=testo.replace("]", " ");
		 testo=testo.replace("{", " ");
		 testo=testo.replace("}", " ");
		 testo=testo.replace("#", " ");
		 testo=testo.replace("$", " ");
		 testo=testo.replace("§", " ");
		 testo=testo.replace("\"", " ");
		 testo=testo.replace("\'", " ");
		 testo=testo.replace("=", " ");
		 
        

		return testo;
	}
}
