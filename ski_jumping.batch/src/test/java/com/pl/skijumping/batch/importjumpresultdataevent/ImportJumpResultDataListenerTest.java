package com.pl.skijumping.batch.importjumpresultdataevent;

import com.pl.skijumping.batch.SetupUtilTests;
import com.pl.skijumping.diagnosticmonitor.DiagnosticMonitor;
import com.pl.skijumping.dto.MessageDTO;
import com.pl.skijumping.dto.MessagePropertiesConst;
import com.pl.skijumping.rabbitmq.producer.RabbitmqProducer;
import com.pl.skijumping.service.JumpResultService;
import com.pl.skijumping.service.JumpResultToDataRaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ImportJumpResultDataListenerTest {

    private static final String FILE_NAME = "jump_result_obersdorf_5255_2019.txt";
    private static final String SOURCE_IMPORT_EVENT_LISTENER = "sourceImportEventListener";
    private static final String EXCHANGE = "exchange";
    @Mock
    private RabbitmqProducer rabbitmqProducer;
    @Mock
    private JumpResultService jumpResultService;
    @Mock
    private JumpResultToDataRaceService jumpResultToDataRaceService;

    @Test
    public void importDataTeamResultTest() throws IOException {
        String filePath = Paths.get(new ClassPathResource(FILE_NAME).getURI()).toString();
        DiagnosticMonitor mockDiagnosticMonitorMock = SetupUtilTests.getDiagnosticMonitorMock();
        Mockito.when(jumpResultService.save(Mockito.any())).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(jumpResultService.findByJumpResult(Mockito.any())).thenReturn(Optional.empty());
        ImportJumpResultDataListener importJumpResultDataListener = new ImportJumpResultDataListener(jumpResultService, mockDiagnosticMonitorMock, jumpResultToDataRaceService, rabbitmqProducer, SOURCE_IMPORT_EVENT_LISTENER, EXCHANGE);

        MessageDTO messageDTO = new MessageDTO()
                .filePath(filePath)
                .properties(new HashMap<String, Object>(){{
                    put(MessagePropertiesConst.DARA_RACE_ID.getValue(), 1L);
                }});
        importJumpResultDataListener.importData(messageDTO);
        Mockito.verify(jumpResultService, Mockito.times(40)).save(Mockito.any());
    }
}