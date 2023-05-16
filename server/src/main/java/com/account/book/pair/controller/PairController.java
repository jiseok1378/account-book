package com.account.book.pair.controller;

import com.account.book.pair.dto.AcceptStatus;
import com.account.book.pair.dto.PairDTO;
import com.account.book.pair.service.PairService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pair")
@RequiredArgsConstructor
public class PairController {
    private final PairService pairService;

    @GetMapping
    public PairDTO getPair( @RequestParam("pairSn") Integer pairSn ){
        return pairService.selectPairByPairSn( pairSn );
    }

    @PostMapping
    public int addPair( @RequestBody PairDTO pairDTO ){
        return pairService.addPair( pairDTO );
    }

    @PutMapping("/accept")
    public PairDTO updatePairAccept( @RequestParam("pairSn") Integer pairSn, @RequestParam("accept") Integer accept ){
        return pairService.updatePairAccept( pairSn, AcceptStatus.getStatus(accept) );
    }

    @DeleteMapping
    public void deletePair( @RequestParam("pairSn") Integer pairSn ){
        pairService.deletePair( pairSn );
    }
}
