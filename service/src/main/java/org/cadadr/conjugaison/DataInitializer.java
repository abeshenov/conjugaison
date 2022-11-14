package org.cadadr.conjugaison;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cadadr.conjugaison.domain.VerbConjugation;
import org.cadadr.conjugaison.service.VerbConjugationService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class DataInitializer {

    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private static final File DATA_FILE = new File("verbs.json");

    private final VerbConjugationService verbConjugationService;

    @Autowired
    public DataInitializer(@NotNull VerbConjugationService verbConjugationService) {
        this.verbConjugationService = verbConjugationService;
        init();
    }

    private void init() {
        logger.info("Saving data to MongoDB");
        verbConjugationService.saveAll(readData());
    }

    private @NotNull List<VerbConjugation> readData() {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            final List<VerbConjugation> result = mapper.readValue(DATA_FILE, new TypeReference<>() {});
            logger.info("Read {} entries from '{}'", result.size(), DATA_FILE.getName());
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
