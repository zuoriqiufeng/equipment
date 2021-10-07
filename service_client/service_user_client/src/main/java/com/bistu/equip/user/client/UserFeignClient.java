package com.bistu.equip.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/22 - 9:03
 */
@Repository
@FeignClient("service-user")
public interface UserFeignClient {

}
