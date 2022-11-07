package com.corsarineri.portale.controllers.db;

import com.corsarineri.portale.dto.BlockDTO;
import com.corsarineri.portale.model.Block;
import com.corsarineri.portale.service.BlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class BlockController {


    @Autowired
    BlockService blockService;

    private Logger logger = LoggerFactory.getLogger(BlockController.class);

    @GetMapping("/block")
    public Integer getCurrentBlockValue(){
        logger.info("Getting current block value :");
        return blockService.getCurrentBlockValue();
    }


    @PutMapping("/block")
    public Block updateBlock(@RequestBody BlockDTO block){
        logger.info("in update Block ",block.isFlag());
        return blockService.IncrementAndDecrementBlock(block.isFlag());
    }
}
