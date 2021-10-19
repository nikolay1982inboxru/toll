package jdev.tracker.services;


import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles({"Test"})
@RunWith(MockitoJUnitRunner.class)
public class ServiceSaveMsgTest {
    @Mock
    Appender mockAppender;

    @Test
    // Проверка установки/получения в/из очередь(и)
    public void putTakeMsg() throws Exception {
        Logger logger = Logger.getLogger(ServiceSaveMsg.class);
        logger.addAppender(mockAppender);


        String expected = "Coordinates";
        ServiceSaveMsg serviceSaveMsg = new ServiceSaveMsg();
        serviceSaveMsg.putMsg(expected);
        Assert.assertEquals(expected, serviceSaveMsg.takeMsg());
    }

    @Test
    // Проверка. Инициализирована ли очередь?
    public void getBlockingQueue() {
        ServiceSaveMsg serviceSaveMsg = new ServiceSaveMsg();
        Assert.assertNotNull(serviceSaveMsg.getBlockingQueue());
    }
}