package com.account.book.pair.service;

import com.account.book.pair.dto.PairDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PairService {
    List<PairDTO> selectPair();
    PairDTO selectPairByUserSn( Integer userSn );
    void addPair( PairDTO pair );
    void deletePair( Integer pairSn );
}
