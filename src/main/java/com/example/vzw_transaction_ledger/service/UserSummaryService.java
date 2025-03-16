package com.example.vzw_transaction_ledger.service;

import com.example.vzw_transaction_ledger.dto.UserTransactionSummaryDTO;
import com.example.vzw_transaction_ledger.mapper.UserSummaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserSummaryService {

    @Autowired
    private UserSummaryMapper userSummaryMapper;

    public List<UserTransactionSummaryDTO> getTopUsersPastWeek() {
        // Calculate the date range for the past week.
        // Here we assume that timestamps are stored in milliseconds (or adjust accordingly).
        long now = Instant.now().toEpochMilli();
        long oneWeekAgo = Instant.now().minus(7, ChronoUnit.DAYS).toEpochMilli();

        // Create a parameter map to pass to the MyBatis mapper.
        Map<String, Object> params = new HashMap<>();
        params.put("startDate", oneWeekAgo);
        params.put("endDate", now);

        // Call the mapper to get the top 3 users.
        return userSummaryMapper.getTopUsers(params);
    }
}
