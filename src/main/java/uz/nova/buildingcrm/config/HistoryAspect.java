package uz.nova.buildingcrm.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import uz.nova.buildingcrm.model.base.BaseEntity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@Slf4j
public class HistoryAspect {

    private static final Path LOG_FILE = Paths.get("history.log");
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            if (Files.notExists(LOG_FILE)) {
                Files.createFile(LOG_FILE);
                appendToFile("=== HISTORY LOG STARTED ===\n");
            }
        } catch (IOException e) {
            log.error("Log faylini yaratib bo'lmadi: {}", LOG_FILE, e);
        }
    }

    @AfterReturning(
            pointcut = "execution(* uz.nova.buildingcrm.repository..*.save(..))",
            returning = "savedEntity"
    )
    public void logSave(JoinPoint joinPoint, Object savedEntity) {
        logEntityChange(savedEntity, "CREATE");
    }

    @AfterReturning(
            pointcut = "execution(* uz.nova.buildingcrm.repository..*.saveAll(..))",
            returning = "savedEntities"
    )
    public void logSaveAll(JoinPoint joinPoint, Object savedEntities) {
        if (savedEntities instanceof Iterable<?> entities) {
            entities.forEach(entity -> logEntityChange(entity, "CREATE"));
        }
    }

    @AfterReturning(
            pointcut = "execution(* uz.nova.buildingcrm.repository..*.delete*(..))"
    )
    public void logDelete(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof BaseEntity entity) {
            logEntityChange(entity, "DELETE");
        }
    }

    private void logEntityChange(Object entityObj, String action) {
        if (!(entityObj instanceof BaseEntity entity)) {
            return;
        }

        String timestamp = LocalDateTime.now().format(DTF);
        String tableName = entity.getClass().getSimpleName();
        String  entityId = entity.getId();
        String value = action.equals("DELETE") ? entity.toString() : entity.toString();

        String logLine = String.format(
                "[%s] %s | Table: %s | ID: %s | Action: %s | Value: %s%n",
                timestamp, "LOG", tableName, entityId, action, value
        );
        appendToFile("\n ");
        appendToFile(logLine);
    }

    private static synchronized void appendToFile(String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(LOG_FILE, StandardOpenOption.APPEND)) {
            writer.write(content);
        } catch (IOException e) {
            log.error("Log yozishda xato: {}", content, e);
        }
    }
}


























//package uz.nova.buildingcrm.config;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import uz.nova.buildingcrm.model.base.BaseEntity;
//import uz.nova.buildingcrm.model.entity.History;
//import uz.nova.buildingcrm.repository.HistoryRepository;
//
//@Aspect
//@Component
//public class HistoryAspect {
//
//    private final HistoryRepository historyRepository;
//    public HistoryAspect(HistoryRepository historyRepository) {
//        this.historyRepository = historyRepository;
//    }
//
//    @AfterReturning(pointcut = "execution(* uz.nova.buildingcrm.service.*.*(..))", returning = "result")
//    public void saveHistory(JoinPoint joinPoint, Object result) {
//        if (result instanceof BaseEntity entity) {
//            String methodName = joinPoint.getSignature().getName();
//            String action = methodName.startsWith("add") ? "CREATE" :
//                            methodName.startsWith("update") ? "UPDATE" :
//                            methodName.startsWith("delete") ? "DELETE" :
//                            methodName.startsWith("save") ? "CREATE" :
//                            methodName.startsWith("create") ? "CREATE" :
//                            methodName.startsWith("register") ? "CREATE" : null;
//
//            if (action != null) {
//                History h = new History();
//                h.setTableName(entity.getClass().getSimpleName());
//                h.setActionType(action);
//                h.setEntityId(entity.getId());
//                h.setNewValue(entity.toString());
//                historyRepository.save(h);
//            }
//        }
//    }
//}