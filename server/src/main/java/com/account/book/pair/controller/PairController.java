package com.account.book.pair.controller;

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
    public PairDTO getPair( @RequestParam("userSn") Integer userSn ){
        return pairService.selectPairByUserSn( userSn );
    }

    @PostMapping
    public void addPair( @RequestBody PairDTO pairDTO ){
        pairService.addPair( pairDTO );
    }

    @DeleteMapping
    public void deletePair( @RequestParam("pairSn") Integer pairSn ){
        pairService.deletePair( pairSn );
    }
}
