package com.example.vzw_transaction_ledger.mapper;

import com.example.vzw_transaction_ledger.dto.UserTransactionSummaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserSummaryMapper {
    // The parameter map allows dynamic filtering (if needed)
    List<UserTransactionSummaryDTO> getTopUsers(Map<String, Object> params);
}
