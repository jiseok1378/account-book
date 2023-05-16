package com.account.book.pair.dao;

import com.account.book.pair.dto.PairDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PairDAO {
    List<PairDTO> selectPair();

    List<PairDTO> selectPairByUserSnFrom( Integer userSn );
    List<PairDTO> selectPairByUserSnTo( Integer userSn );
    Optional<PairDTO> selectPairByPairSn(Integer pairSn );

    int addPair( PairDTO pair );
    void deletePair( Integer pairSn );
    void updatePairAccept( PairDTO pair );
}
