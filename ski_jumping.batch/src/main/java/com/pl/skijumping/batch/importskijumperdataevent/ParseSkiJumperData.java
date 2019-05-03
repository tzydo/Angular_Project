package com.pl.skijumping.batch.importskijumperdataevent;

import com.pl.skijumping.batch.matchingword.MatchingWords;
import com.pl.skijumping.batch.reader.DataReader;
import com.pl.skijumping.diagnosticmonitor.DiagnosticMonitor;
import com.pl.skijumping.dto.JumpResultDTO;
import com.pl.skijumping.dto.SkiJumperDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class ParseSkiJumperData {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final DiagnosticMonitor diagnosticMonitor;

    ParseSkiJumperData(DiagnosticMonitor diagnosticMonitor) {
        this.diagnosticMonitor = diagnosticMonitor;
    }

    public SkiJumperDTO importData(JumpResultDTO jumpResultDTO, String filePath) {
        if (jumpResultDTO == null || filePath == null || filePath.isEmpty()) {
            return null;
        }

        DataReader dataReader = new DataReader(diagnosticMonitor);
        String readSource = dataReader.read(filePath);
        MatchingWords matchingWords = new MatchingWords(diagnosticMonitor);
        return new SkiJumperDTO()
                .fisCode(jumpResultDTO.getFisCodeId())
                .name(jumpResultDTO.getJumperName())
                .birthday(LocalDate.parse(matchingWords.getSkiJumperBirthDay(readSource), DATE_TIME_FORMATTER))
                .nationality(matchingWords.getSkiJumperCountry(readSource))
                .team(matchingWords.getSkiJumperTeam(readSource))
                .gender(matchingWords.getSkiJumperGender(readSource))
                .martialStatus(matchingWords.getSkiJumperMartialStatus(readSource));
    }
}
