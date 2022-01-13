package com.tolfin.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tolfin
 * @since 2021-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Account对象", description="")
@Component
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "卡号")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "账户余额")
    private Integer accountBalance;

    @ApiModelProperty(value = "今日消费")
    private Integer consumptionToday;

    @ApiModelProperty(value = "末次充值金额")
    private Integer lastRechargeAmount;

    @ApiModelProperty(value = "末次充值时间")
    private Date lastRechargeTime;


}
