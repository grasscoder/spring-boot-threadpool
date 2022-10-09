package org.test.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.entity.UserEntity;
import org.test.service.UserService;

import java.util.List;

public class UserRunnable implements Runnable {

    private static final Logger logger= LoggerFactory.getLogger(UserRunnable.class);

    private int page;
    private int pageSize;

    private UserService userService;

    public UserRunnable(int page, int pageSize ,UserService userService) {
        this.page = page;
        this.pageSize = pageSize;
        this.userService = userService;
    }

    @Override
    public void run() {
       List<UserEntity> userEntityList =  userService.select(page, pageSize);
       userEntityList.forEach(userEntity -> {
           String name = Thread.currentThread().getName();
           System.out.println(name + " -->\t\t" + userEntity.toString());
       });
        logger.info("-----------------------------------------------");
    }
}
