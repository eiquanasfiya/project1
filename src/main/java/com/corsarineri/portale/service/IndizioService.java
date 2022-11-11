package com.corsarineri.portale.service;

import com.corsarineri.portale.exceptions.IndizioNotFoundException;
import com.corsarineri.portale.model.Indizio;
import com.corsarineri.portale.model.Soluzione;
import com.corsarineri.portale.repos.IndizioRepository;
import com.corsarineri.portale.repos.SoluzioneRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndizioService {
    @Autowired
    SoluzioneRepository soluzioneRepository;

    @Autowired
    IndizioRepository indizioRepository;

    public void updateSemaforo(String solutionSemeforo,Long indizioId) {

        Optional<Indizio> indizio = indizioRepository.findById(indizioId);
        if (indizio.isPresent()) {
            Indizio foundIndizio = indizio.get();

            if (isNewSemeforoState(solutionSemeforo, foundIndizio.getSemaforo())) {
                indizio.get().setSemaforo(solutionSemeforo);
                indizioRepository.save(foundIndizio);
            }
        }else{
               throw new IndizioNotFoundException(indizioId);
           }


    }

    private boolean isNewSemeforoState(String solutionSemeforo,String indizioSemeforo){

        if(indizioSemeforo != null && StringUtils.isNotBlank(indizioSemeforo)) {
            if(indizioSemeforo.equalsIgnoreCase(solutionSemeforo)){
                return false;
            }else if(indizioSemeforo.equalsIgnoreCase("rosso") && (solutionSemeforo.equalsIgnoreCase("giollo") || solutionSemeforo.equalsIgnoreCase("verde") )){
                return true;
            }else if(indizioSemeforo.equalsIgnoreCase("giollo") && (solutionSemeforo.equalsIgnoreCase("verde") )){
                return true;
            }
            return false;
        }else {
            return true;
        }



    }
}
