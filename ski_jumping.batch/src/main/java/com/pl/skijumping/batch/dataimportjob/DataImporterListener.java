package com.pl.skijumping.batch.dataimportjob;

import com.pl.skijumping.common.util.FileUtil;
import com.pl.skijumping.diagnosticmonitor.DiagnosticMonitor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataImporterListener implements StepExecutionListener {

    private final String directory;
    private final DiagnosticMonitor diagnosticMonitor;

    public DataImporterListener(String directory,
                                DiagnosticMonitor diagnosticMonitor) {
        this.directory = directory;
        this.diagnosticMonitor = diagnosticMonitor;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        //
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (!stepExecution.getFailureExceptions().isEmpty()) {
            removeFilesFromDirectory();
            stepExecution.upgradeStatus(BatchStatus.FAILED);
            String errorMessage = stepExecution.getFailureExceptions().stream()
                    .map(Throwable::getMessage)
                    .collect(Collectors.joining("\n\n"));
            return new ExitStatus(ExitStatus.FAILED.getExitCode(), errorMessage);
        }

        String exitStatus = stepExecution.getExitStatus().getExitCode();
        stepExecution.upgradeStatus(exitStatus.equals(ExitStatus.FAILED.getExitCode()) ? BatchStatus.FAILED : BatchStatus.COMPLETED);
        return stepExecution.getExitStatus();
    }

    private void removeFilesFromDirectory() {
        try {
            Path directoryPath = Paths.get(FileUtil.getResource(), directory);
            List<File> fileList = FileUtil.getFiles(directoryPath);
            fileList.forEach(File::delete);
            Files.delete(directoryPath);
        } catch (IOException e) {
            diagnosticMonitor.logError(String.format("Cannot delete file from directory%s", directory), getClass());
            diagnosticMonitor.logError(e.getMessage(), getClass());
        }
    }
}