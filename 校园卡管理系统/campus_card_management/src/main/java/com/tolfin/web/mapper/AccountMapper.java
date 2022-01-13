package com.tolfin.web.mapper;

import com.tolfin.web.pojo.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-03
 */
@Repository
public interface AccountMapper extends BaseMapper<Account> {

}
