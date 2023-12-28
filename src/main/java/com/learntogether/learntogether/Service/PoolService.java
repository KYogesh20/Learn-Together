package com.learntogether.learntogether.Service;
import com.learntogether.learntogether.Dto.CreatePoolRequestDto;
import com.learntogether.learntogether.Dto.CreatePoolResponseDto;
import com.learntogether.learntogether.Dto.GetPoolsResponseDto;
import com.learntogether.learntogether.Dto.JoinPoolRequestDto;
import com.learntogether.learntogether.Entity.Pool;
import com.learntogether.learntogether.Entity.User;
import com.learntogether.learntogether.Exception.PoolAlreadyExistsException;
import com.learntogether.learntogether.Exception.PoolNotFoundException;
import com.learntogether.learntogether.Exception.UserAlreadyExistsException;
import com.learntogether.learntogether.Exception.UserNotFoundException;
import com.learntogether.learntogether.Repository.PoolRepository;
import com.learntogether.learntogether.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoolService {

    @Autowired
    PoolRepository poolRepository;

    @Autowired
    UserRepository userRepository;

    public void createPool(CreatePoolRequestDto createPoolRequestDto) throws UserNotFoundException, PoolAlreadyExistsException {

        User user = userRepository.findByEmail(createPoolRequestDto.getEmail());

        if(user==null) throw new UserNotFoundException("User not found!!!");

        // setting pool
        Pool newPool = new Pool();
        newPool.setOwnerId(user.getUserId());
        newPool.setPoolName(createPoolRequestDto.getPoolName());
        newPool.setPoolDescription(createPoolRequestDto.getPoolDescription());


        // adding owner to list
        List<Long> usersJoined = new ArrayList<>();
        usersJoined.add(user.getUserId());
        newPool.setUsersJoined(usersJoined);

        // saving pool to db
        newPool = poolRepository.save(newPool);

        // adding pool to list
        List<Long> poolsJoined = user.getPoolsJoined();
        poolsJoined.add(newPool.getPoolId());
        user.setPoolsJoined(poolsJoined);

        // saving data to database
        userRepository.save(user);

        // setting response dto
        CreatePoolResponseDto createPoolResponseDto = new CreatePoolResponseDto();
        createPoolResponseDto.setPoolOwner(user.getUsername());
        createPoolResponseDto.setPoolDescription(newPool.getPoolDescription());
        createPoolResponseDto.setPoolName(newPool.getPoolName());

    }


    public void joinPool(JoinPoolRequestDto joinPoolRequestDto) throws UserNotFoundException, PoolNotFoundException, UserAlreadyExistsException {

        Pool pool = poolRepository.findByPoolName(joinPoolRequestDto.getPoolName());

        if(pool==null) throw new PoolNotFoundException("Pool not found");

        User user = userRepository.findByEmail(joinPoolRequestDto.getEmail());

        if(user==null) throw new UserNotFoundException("User not found");

        List<Long> usersJoined = pool.getUsersJoined();
        for(Long id:usersJoined){
            if(id.equals(user.getUserId())) throw new UserAlreadyExistsException("User Already exists");
        }

        // updating list of users joined in a pool
        List<Long> userList = pool.getUsersJoined();

        userList.add(user.getUserId());
        pool.setUsersJoined(userList);

        // updating pools joined in user
        List<Long> poolList = user.getPoolsJoined();
        poolList.add(pool.getPoolId());
        user.setPoolsJoined(poolList);

        poolRepository.save(pool);
        userRepository.save(user);
    }

    public List<Pool> getPools(){
        return poolRepository.findAll();
    }

}
