package com.corsarineri.portale.service;

import com.corsarineri.portale.exceptions.BlockNotFoundExcention;
import com.corsarineri.portale.exceptions.CanNotDecreaseException;
import com.corsarineri.portale.model.Block;
import com.corsarineri.portale.repos.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlockService {
    @Autowired
    BlockRepository blockRepository;

    public Integer getCurrentBlockValue(){
        Long id =1L;
        Optional<Block> block = blockRepository.findById(id);
        if (block.isPresent()){
            return block.get().getBlockvalue();
        }
        throw new BlockNotFoundExcention(id);
    }

    public Block IncrementAndDecrementBlock(boolean flag){
        Long id =1L;
        Optional<Block> block = blockRepository.findById(id);
        if(block.isPresent()){
            Block bl = block.get();
            if(flag){
                bl.setBlockvalue(bl.getBlockvalue()+1);
            }
            else if(bl.getBlockvalue() > 1 && !flag){
                bl.setBlockvalue(bl.getBlockvalue()-1);
            }
            else {
                throw new CanNotDecreaseException();
            }
           return blockRepository.save(bl);
        }
        else {
            throw new BlockNotFoundExcention(id);
        }
    }
}
