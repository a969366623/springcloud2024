package com.atguigu.cloud.service.impl;

import com.atguigu.cloud.apis.AccountFeignApi;
import com.atguigu.cloud.apis.StorageFeignApi;
import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.mapper.OrderMapper;
import com.atguigu.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author jiaronghao
 * @date 2024/4/19 10:37
 * @Description 需求说明：下订单->减库存->扣余额->改(订单)状态
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    // 订单微服务通过openfeign调用库存的微服务
    @Resource
    private StorageFeignApi storageFeignApi;

    // 订单微服务通过openfeign去调用账户微服务
    @Override
    @GlobalTransactional(name = "jiaronghao-create-order",rollbackFor = Exception.class)   //AT模式
    public void create(Order order) {

        // 全局事务 id的检查
        String xid = RootContext.getXID();
        // 1.新建订单
        log.info("-------------开始新建订单：" + "\t" + "xid:" + xid);
        // 订单新建时默认初始订单状态为0
        order.setStatus(0);
        Order orderFromDB = null;
        int rows = orderMapper.insertSelective(order);
        // 插入订单成功后获得插入mysql的实体对象
        if (rows > 0) {
            orderFromDB = orderMapper.selectOne(order);
            log.info("-------> 新建订单成功, orderFromDB info:" + orderFromDB);
            System.out.println();
            // 2. 扣减积分
            log.info("-------> 订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(),orderFromDB.getCount());
            log.info("-------> 订单微服务开始调用Storage库存，做扣减完成");
            System.out.println();
            log.info("-------> 订单微服务开始调用Account账户，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(),orderFromDB.getMoney());
            log.info("-------> 订单微服务开始调用Account账户，做扣减money");
            orderFromDB.setStatus(1);
            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId",orderFromDB.getUserId());
            criteria.andEqualTo("status",0);

            int updateRows = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);
            log.info("-------> 修改订单状态完成"+"\t"+updateRows);
            log.info("-------> orderFromDB info: "+orderFromDB);
        }
        System.out.println();
        log.info("-------------结束新建订单：" + "\t" + "xid:" + xid);

    }

    @Resource
    private AccountFeignApi accountFeignApi;
}
