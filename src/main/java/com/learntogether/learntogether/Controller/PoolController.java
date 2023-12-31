package com.learntogether.learntogether.Controller;
import com.learntogether.learntogether.Dto.CreatePoolRequestDto;
import com.learntogether.learntogether.Dto.CreatePoolResponseDto;
import com.learntogether.learntogether.Dto.GetPoolsResponseDto;
import com.learntogether.learntogether.Dto.JoinPoolRequestDto;
import com.learntogether.learntogether.Entity.Pool;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Exception.UserNotFoundException;
import com.learntogether.learntogether.Service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pool")
public class PoolController {

    @Autowired
    PoolService poolService;

    @PostMapping("/create-pool")
    public ResponseEntity createPool(@RequestBody CreatePoolRequestDto createPoolRequestDto) {
       try{
           poolService.createPool(createPoolRequestDto);
       }
       catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }

       return new ResponseEntity<>("Pool created successfully!!", HttpStatus.CREATED);
    }

    @PostMapping("/join-pool")
    public ResponseEntity<String> joinPool(@RequestBody JoinPoolRequestDto joinPoolRequestDto){
        try{
            poolService.joinPool(joinPoolRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Pool joined Successfully!!",HttpStatus.CREATED);
    }

    @GetMapping("/get-pools")
    public ResponseEntity<List<Pool>> getPools(){
        List<Pool> pools = poolService.getPools();
        return new ResponseEntity<>(pools,HttpStatus.CREATED);
    }

}
