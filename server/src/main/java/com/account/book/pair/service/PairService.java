package com.account.book.pair.service;

import com.account.book.pair.dto.AcceptStatus;
import com.account.book.pair.dto.PairDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface PairService {
    List<PairDTO> selectPair();
    PairDTO selectPairByPairSn(Integer pairSn );
    int addPair( PairDTO pair );
    void deletePair( Integer pairSn );
    PairDTO updatePairAccept(Integer pairSn, AcceptStatus accept );
}
