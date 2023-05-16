package com.account.book.pair.service.impl;

import com.account.book.pair.dao.PairDAO;
import com.account.book.pair.dto.AcceptStatus;
import com.account.book.pair.dto.PairDTO;
import com.account.book.pair.service.PairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PairServiceImpl implements PairService {
    private final PairDAO pairDAO;

    @Override
    public List<PairDTO> selectPair() {
        return pairDAO.selectPair();
    }

    @Override
    public PairDTO selectPairByUserSn( Integer userSn ) {
        return pairDAO.selectPairByUserSn( userSn );
    }

    @Override
    public void addPair( PairDTO pair ) {
        pair.setAccept( AcceptStatus.PENDING );
        pairDAO.addPair( pair );

    }

    @Override
    public void deletePair( Integer pairSn ) {
        pairDAO.deletePair( pairSn );
    }
}
