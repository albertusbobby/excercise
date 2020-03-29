package com.people.component;

import com.opencsv.CSVReader;
import com.people.entities.History;
import com.people.entities.People;
import com.people.repositories.HistoryRepository;
import com.people.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

//@Component
public class DataInitializer implements CommandLineRunner {

	private static final String ROOT_FILE = "/home/bobby/shared/csv/";
    private static Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	private static final String PEOPLE_PATH = "master_people.csv";
    private static final String HISTORY_PATH = "master_history.csv";

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void run(String... args) throws IOException {
        logger.info("Start import data");
        importPeople();
        importHistory();
        logger.info("Import data finished");
    }

    private void importHistory() throws IOException {
        Path historyCsv = Paths.get(new File(ROOT_FILE + HISTORY_PATH).getPath());
        CSVReader csvReaderHistory = new CSVReader(Files.newBufferedReader(historyCsv));
        List<String[]> list = csvReaderHistory.readAll();
        for (String[] l:list) {
            History history = historyRepository.findById(Long.valueOf(l[0])).orElse(new History());
            history.setId(Long.valueOf(l[0]));
            history.setName(l[1]);
            history.setDescription(l[2]);
            historyRepository.save(history);
			logger.info("### HISTORY : import id "+history.getId());
        }
        csvReaderHistory.close();
    }

    private void importPeople() throws IOException {
        Path peopleCsv = Paths.get(new File(ROOT_FILE + PEOPLE_PATH).getPath());
        CSVReader csvReaderPeople = new CSVReader(Files.newBufferedReader(peopleCsv));
        List<String[]> list = csvReaderPeople.readAll();
        for (String[] l:list) {
            People people = peopleRepository.findById(Long.valueOf(l[0])).orElse(new People());
            people.setId(Long.valueOf(l[0]));
            people.setName(l[4]);
            people.setDateOfBirth(Date.valueOf(l[1]));
            people.setPlaceOfBirth(l[5]);
            people.setHeight(Double.parseDouble(l[2]));
            people.setWeight(Double.parseDouble(l[6]));
            people.setHistory(l[3]);
            peopleRepository.save(people);
			logger.info("### PEOPLE : import id "+people.getId());
        }
        csvReaderPeople.close();
    }
}
