package com.account.book.pair.dao;

import com.account.book.pair.dto.PairDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PairDAO {
    List<PairDTO> selectPair();
    PairDTO selectPairByUserSn( Integer userSn );
    void addPair( PairDTO pair );
    void deletePair( Integer pairSn );
}
