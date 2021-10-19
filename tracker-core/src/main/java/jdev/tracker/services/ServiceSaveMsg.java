package jdev.tracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceSaveMsg {
    private static  final Logger LOG_ERRORS = LoggerFactory.getLogger("allError.TrackerCore");
    
    // Создан объект блокирующей очереди по типу FIFO
    private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(100);

    // Запиcь в очередь
    public void putMsg(String msg) {
        try {
            blockingQueue.put(msg);
        }
        catch (InterruptedException interruptedEx){
            LOG_ERRORS.error("Неудачная попытка сделать запись в очередь: " + interruptedEx.getMessage());
        }
    }

    // Чтение из очереди
    public String takeMsg() throws InterruptedException {
        try {
            return blockingQueue.take();
        }
        catch (InterruptedException interruptedEx){
            LOG_ERRORS.error("Неудачная попытка получить элемент из очереди: " + interruptedEx.getMessage());
        }
        return  null;
    }

    // Получение очереди сообщений
    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }
}
