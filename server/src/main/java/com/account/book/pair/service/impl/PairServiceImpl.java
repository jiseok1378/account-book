package com.account.book.pair.service.impl;

import com.account.book.pair.dao.PairDAO;
import com.account.book.pair.dto.AcceptStatus;
import com.account.book.pair.dto.PairDTO;
import com.account.book.pair.service.PairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PairServiceImpl implements PairService {
    private final PairDAO pairDAO;

    @Override
    public List<PairDTO> selectPair() {
        return pairDAO.selectPair();
    }

    @Override
    public PairDTO selectPairByPairSn (Integer pairSn ) {
        return pairDAO.selectPairByPairSn( pairSn ).orElseThrow();
    }

    @Override
    public int addPair( PairDTO pair ) {
        pair.setAccept( AcceptStatus.PENDING );
        pairDAO.addPair( pair );
        return pair.getPairSn();
    }

    @Override
    public void deletePair( Integer pairSn ) {
        pairDAO.deletePair( pairSn );
    }

    @Override
    public PairDTO updatePairAccept( Integer pairSn, AcceptStatus accept ) {
        PairDTO pairDTO = new PairDTO();
        pairDTO.setPairSn( pairSn );
        pairDTO.setAccept( accept );
        pairDAO.updatePairAccept( pairDTO );
        return selectPairByPairSn( pairSn );
    }
}
