package com.example.bookmanagementsystem.support.aspect;
import com.example.bookmanagementsystem.dto.BookDto;
import com.example.bookmanagementsystem.service.HistoryService;
import com.example.bookmanagementsystem.service.UserService;
import com.example.bookmanagementsystem.support.domain.HistoryType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class HistoryAspect {
    private HistoryService historyService;

    private UserService userService;

    @Autowired
    public HistoryAspect(HistoryService historyService, UserService userService) {
        this.historyService = historyService;
        this.userService = userService;
    }

    @Pointcut("within(com.durin93.bookmanagement.service.BookService)")
    public void bookServiceMethod() {
    }

    @AfterReturning(pointcut = "bookServiceMethod()", returning = "bookDto")
    public void saveHistory(JoinPoint joinPoint, BookDto bookDto) {
        Optional<HistoryType> historyType = checkHistoryType(joinPoint);
        historyType.ifPresent(saveHistory -> historyService.save(bookDto.getId(), userService.loginUser().getId(), historyType.get()));
    }

    private Optional<HistoryType> checkHistoryType(JoinPoint joinPoint) {
        if (checkHistoryType(joinPoint, "regist")) {
            return Optional.of(HistoryType.REGIST);
        }
        if (checkHistoryType(joinPoint, "update")) {
            return Optional.of(HistoryType.UPDATE);
        }
        if (checkHistoryType(joinPoint, "delete")) {
            return Optional.of(HistoryType.DELETE);
        }
        if (checkHistoryType(joinPoint, "rent")) {
            return Optional.of(HistoryType.RENT);
        }
        if (checkHistoryType(joinPoint, "giveBack")) {
            return Optional.of(HistoryType.GIVEBACK);
        }
        return Optional.empty();
    }

    private boolean checkHistoryType(JoinPoint joinPoint, String methodName) {
        return joinPoint.getSignature().toShortString().contains(methodName);
    }


}