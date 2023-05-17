package com.account.book.pair.controller;

import com.account.book.cmmn.util.response.CommonResult;
import com.account.book.cmmn.util.response.ResponseService;
import com.account.book.cmmn.util.response.SingleResult;
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
    private final ResponseService responseService;

    @GetMapping
    public SingleResult<PairDTO> getPair(@RequestParam("pairSn") Integer pairSn ){
        return responseService.getSingleResult(pairService.selectPairByPairSn( pairSn ));
    }

    @PostMapping
    public SingleResult<Integer> addPair( @RequestBody PairDTO pairDTO ){
        return responseService.getSingleResult(pairService.addPair( pairDTO ));
    }

    @PutMapping("/accept")
    public SingleResult<PairDTO> updatePairAccept( @RequestParam("pairSn") Integer pairSn, @RequestParam("accept") Integer accept ){
        return responseService.getSingleResult(pairService.updatePairAccept( pairSn, AcceptStatus.getStatus(accept) ));
    }

    @DeleteMapping
    public CommonResult deletePair(@RequestParam("pairSn") Integer pairSn ){
        pairService.deletePair( pairSn );
        return responseService.getSuccessResult();
    }
}
