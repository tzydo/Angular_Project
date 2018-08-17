package com.pl.skijumping.batch.datasynchronize;

import com.pl.skijumping.client.HtmlDownloader;
import com.pl.skijumping.common.exception.InternalServiceException;
import com.pl.skijumping.common.util.FileUtil;
import com.pl.skijumping.diagnosticmonitor.DiagnosticMonitor;
import com.pl.skijumping.dto.TournamentYearDTO;
import com.pl.skijumping.service.TournamentYearService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.ExitStatus;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BasicDataDownloaderTest {

    @Mock
    private DiagnosticMonitor diagnosticMonitor;
    @Mock
    private TournamentYearService tournamentYearService;
    @Mock
    private HtmlDownloader htmlDownloader;
    private String host = "host";
    private String directory = "testTmp";

    @Test
    public void downloadTest() throws InternalServiceException {
        Mockito.when(tournamentYearService.findAll()).thenReturn(
                Optional.of(Arrays.asList(new TournamentYearDTO(1L, "2018"))));
        FileUtil.createDirectory(directory);

        BasicDataDownloader basicDataDownloader = new BasicDataDownloader(tournamentYearService, host, directory, htmlDownloader, diagnosticMonitor);
        ExitStatus exitStatus = basicDataDownloader.download();

        Optional<File> actualFile = FileUtil.getFile(directory + File.separator + BasicDataDownloader.FILE_NAME + "2018.txt");
        Assertions.assertThat(actualFile.isPresent()).isTrue();
        Assertions.assertThat(exitStatus.getExitCode()).isEqualTo(ExitStatus.COMPLETED.getExitCode());
        FileUtil.deleteFile(actualFile.get().getPath());
    }

    @Test
    public void downloadWhenNotFoundTournamentYearTest() {
        Mockito.when(tournamentYearService.findAll()).thenReturn(Optional.empty());
        BasicDataDownloader basicDataDownloader = new BasicDataDownloader(tournamentYearService, host, directory, htmlDownloader, diagnosticMonitor);
        ExitStatus exitStatus = basicDataDownloader.download();
        Assertions.assertThat(exitStatus.getExitCode()).isEqualTo(ExitStatus.FAILED.getExitCode());
    }
}