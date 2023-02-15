package com.microservice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.business.TourPackageService;
import com.microservice.business.TourService;
import com.microservice.data.Difficulty;
import com.microservice.data.Region;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class MicroserviceApplication implements CommandLineRunner {
	
	@Autowired
	private TourPackageService tourPackageService;
	
	@Autowired
	private TourService tourService;

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		createTourPackages();
		
		long numofPackages = tourPackageService.total();
		
		createTours("ExploreCalifornia.json");
		long numofTours = tourService.total();
	}
	
	private void createTourPackages() {
		tourPackageService.CreateNewTourPackage("BC", "Backpack Cal");
		tourPackageService.CreateNewTourPackage("CC", "California Calm");
		tourPackageService.CreateNewTourPackage("CH", "California Hot springs");
		tourPackageService.CreateNewTourPackage("CY", "Cycle California");
		tourPackageService.CreateNewTourPackage("DS", "From Desert to Sea");
		tourPackageService.CreateNewTourPackage("KC", "Kids California");
		tourPackageService.CreateNewTourPackage("NW", "Nature Watch");
		tourPackageService.CreateNewTourPackage("SC", "Snowboard Cali");
		tourPackageService.CreateNewTourPackage("TC", "Taste of California");
	}
	
	private void createTours(String filetoimport) throws IOException{
		TourFromFile.read(filetoimport).forEach(importedTour->
				tourService.CreateTour(importedTour.getTitle(),
                importedTour.getDescription(),
                importedTour.getBlurb(),
                importedTour.getPrice(),
                importedTour.getLength(),
                importedTour.getBullets(),
                importedTour.getKeywords(),
                importedTour.getPackageType(),
                importedTour.getDifficulty(),
                importedTour.getRegion()));
		
	}
	
	private static class TourFromFile {
        //fields
        private String packageType, title, description, blurb, price, length,
                bullets, keywords, difficulty, region;
        //reader
        
        static List<TourFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {});
        }
        protected TourFromFile(){}

        String getPackageType() { return packageType; }

        String getTitle() { return title; }

        String getDescription() { return description; }

        String getBlurb() { return blurb; }

        Integer getPrice() { return Integer.parseInt(price); }

        String getLength() { return length; }

        String getBullets() { return bullets; }

        String getKeywords() { return keywords; }

        Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

        Region getRegion() { return Region.findByLabel(region); }
    }
}
