package jdev.servercore.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@ActiveProfiles({"Test"})
@RunWith(MockitoJUnitRunner.class)
// !!! Для корректного выполнения теста "takeQueueMsgIntegration()"
// должен быть запущен сервис "TrackerMain", т.к. он формирует координаты,
// которые забираются в тесте методом POST
public class ServiceTakeMsgTest {

    @Mock
    Appender mockAppender;

    @Test
    public void takeQueueMsgIntegration() throws IOException, ProcessingException {
        // Организация апендера для работы логгера (static final)
        Logger logger = Logger.getLogger(ServiceTakeMsg.class);
        logger.addAppender(mockAppender);

        // Читаем первую запись из списка координат
        ServiceTakeMsg serviceTakeMsg = new ServiceTakeMsg(new RestTemplate());
        String actual = serviceTakeMsg.takeQueueMsg().get(0);

        // Валидатор схемы JSON
          // Получение файла Json-схемы
        final File jsonSchemaFile = new File("C:\\MY\\TUSUR\\TUSUR_Exercises\\toll-github\\server-core\\src\\test\\resources\\SchemaValidatorJSON_coord.json");
        final URI uri = jsonSchemaFile.toURI();
          // Подготовка схемы для отчета
        final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();
        final JsonSchema schema = jsonSchemaFactory.getJsonSchema(uri.toString());
        ProcessingReport report;
          // Парсим поля Json-координат и валидируем их
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonCoord = objectMapper.readTree("{\"\":" + actual + "}");
        report = schema.validate(jsonCoord);


        System.out.println("\n\n\t*+*+*+*+*+*---> is JSON-schema valid? = " + report.isSuccess());
        System.out.println("\t*+*+*+*+*+*---> Value for analysis = " + actual + "\n\n");

        Assert.assertEquals(true, report.isSuccess());

    }
}