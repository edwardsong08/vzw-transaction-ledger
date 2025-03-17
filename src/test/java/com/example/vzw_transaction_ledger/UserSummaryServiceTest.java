package com.example.vzw_transaction_ledger;

import com.example.vzw_transaction_ledger.dto.UserTransactionSummaryDTO;
import com.example.vzw_transaction_ledger.mapper.UserSummaryMapper;
import com.example.vzw_transaction_ledger.service.UserSummaryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserSummaryServiceTest {

    @Mock
    private UserSummaryMapper userSummaryMapper;

    @InjectMocks
    private UserSummaryService userSummaryService;

    @BeforeEach
    void setUp() {
        // This method is optional if you need extra initialization.
        // @InjectMocks + @Mock automatically wires the mock into userSummaryService.
    }

    @Test
    void getTopUsersPastWeek_ShouldReturnListFromMapper() {
        // GIVEN: Some mock data for the mapper to return
        List<UserTransactionSummaryDTO> mockList = new ArrayList<>();
        UserTransactionSummaryDTO dto1 = new UserTransactionSummaryDTO();
        dto1.setUserId(1L);
        dto1.setUserName("Alice");
        dto1.setTotalAmount(500.0);
        mockList.add(dto1);

        // Mock the mapper call
        when(userSummaryMapper.getTopUsers(anyMap())).thenReturn(mockList);

        // WHEN: We call the service
        List<UserTransactionSummaryDTO> result = userSummaryService.getTopUsersPastWeek();

        // THEN: The result should match whatever the mapper returned
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getUserName());
        assertEquals(500.0, result.get(0).getTotalAmount());

        // Verify mapper was called once
        verify(userSummaryMapper, times(1)).getTopUsers(anyMap());
    }

    @Test
    @SuppressWarnings("unchecked")
    void getTopUsersPastWeek_ShouldPassCorrectStartAndEndDate() {
        // We'll capture the arguments passed into the mapper
        ArgumentCaptor<Map<String, Object>> captor = ArgumentCaptor.forClass(Map.class);

        // The mapper can return an empty list here; we just care about the parameters
        when(userSummaryMapper.getTopUsers(anyMap())).thenReturn(Collections.emptyList());

        // WHEN
        userSummaryService.getTopUsersPastWeek();

        // THEN
        // Capture the exact map passed to the mapper
        verify(userSummaryMapper, times(1)).getTopUsers(captor.capture());
        Map<String, Object> params = captor.getValue();

        assertTrue(params.containsKey("startDate"));
        assertTrue(params.containsKey("endDate"));

        long startDate = (long) params.get("startDate");
        long endDate = (long) params.get("endDate");

        // Check endDate ~ now
        long now = Instant.now().toEpochMilli();
        // We'll allow a small tolerance for test execution time
        long toleranceMs = 60_000L; // 1 minute
        assertTrue(Math.abs(now - endDate) < toleranceMs, 
                "endDate should be close to the current time");

        // Check startDate ~ one week before endDate
        long sevenDaysInMs = ChronoUnit.DAYS.getDuration().toMillis() * 7;
        long difference = endDate - startDate;
        long buffer = 10_000L; // 10 seconds
        assertTrue(Math.abs(difference - sevenDaysInMs) < buffer,
                "startDate should be about 7 days before endDate");
    }
}
