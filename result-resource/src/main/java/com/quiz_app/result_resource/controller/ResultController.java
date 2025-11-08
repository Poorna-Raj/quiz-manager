package com.quiz_app.result_resource.controller;

import com.quiz_app.result_resource.data.Result;
import com.quiz_app.result_resource.data.ResultRequestDto;
import com.quiz_app.result_resource.data.ResultResponseDto;
import com.quiz_app.result_resource.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {
    @Autowired
    private ResultService resultService;

    @PostMapping("results/")
    public ResponseEntity<ResultResponseDto> createResult(@RequestBody ResultRequestDto dto){
        Result result = resultService.saveResult(resultService.mapToResultModel(dto));
        return new ResponseEntity<>(resultService.mapToResultResponseDto(result), HttpStatus.CREATED);
    }

    @DeleteMapping("results/{id}")
    public ResponseEntity<HttpStatus> deleteResult(@PathVariable long id){
        if(resultService.deleteResultById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("results/{id}")
    public ResponseEntity<ResultResponseDto> getResultById(@PathVariable long id){
        Result result = resultService.getResultById(id);
        return new ResponseEntity<>(resultService.mapToResultResponseDto(result),HttpStatus.OK);
    }

    @GetMapping("results")
    public ResponseEntity<List<ResultResponseDto>> getAllResults(){
        List<ResultResponseDto> responseDto = resultService.getResults().stream()
                .map(resultService::mapToResultResponseDto)
                .toList();

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}
