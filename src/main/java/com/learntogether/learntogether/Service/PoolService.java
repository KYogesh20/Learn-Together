package com.learntogether.learntogether.Service;
import com.learntogether.learntogether.Dto.CreatePoolRequestDto;
import com.learntogether.learntogether.Dto.CreatePoolResponseDto;
import com.learntogether.learntogether.Dto.JoinPoolRequestDto;
import com.learntogether.learntogether.Entity.Pool;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Exception.PoolNotFoundException;
import com.learntogether.learntogether.Exception.UserNotFoundException;
import com.learntogether.learntogether.Repository.PoolRepository;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class PoolService {

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    UserRepository userRepository;

    // TODO: implement exception handling
    public void createPool(CreatePoolRequestDto createPoolRequestDto) throws UserNotFoundException {

        User owner;
        try{
            owner = userRepository.findByEmail(createPoolRequestDto.getEmail());
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found!!");
        }

        Pool newPool = new Pool();
        newPool.setPoolOwner(owner);
        newPool.setPoolName(createPoolRequestDto.getPoolName());
        newPool.setPoolDescription(createPoolRequestDto.getPoolDescription());

        poolRepository.save(newPool);

        CreatePoolResponseDto createPoolResponseDto = new CreatePoolResponseDto();
        createPoolResponseDto.setPoolOwner(owner.getUsername());
        createPoolResponseDto.setPoolDescription(newPool.getPoolDescription());
        createPoolResponseDto.setPoolName(newPool.getPoolName());

    }


    public void joinPool(JoinPoolRequestDto joinPoolRequestDto) throws UserNotFoundException, PoolNotFoundException {
        Pool poolToJoin;
        User newUser;

        try{
            poolToJoin = poolRepository.findByPoolName(joinPoolRequestDto.getPoolName());
        }
        catch (Exception e){
            throw new PoolNotFoundException("Pool not found!!");
        }

        try{
            newUser = userRepository.findByEmail(joinPoolRequestDto.getEmail());
        }
        catch (Exception e){
            throw new UserNotFoundException("User not found!!");
        }

        // updating list of users joined in a pool
        List<User> userList = poolToJoin.getUsersJoined();

        userList.add(newUser);
        poolToJoin.setUsersJoined(userList);

        // updating pools joined in user
        List<Pool> poolList = newUser.getPoolsJoined();
        poolList.add(poolToJoin);
        newUser.setPoolsJoined(poolList);

        poolRepository.save(poolToJoin);
        userRepository.save(newUser);
    }


}
