package com.tolfin.web.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="BankCard对象", description="")
@Component
public class BankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行卡卡号")
    @TableId(value = "cid", type = IdType.ID_WORKER)
    private String cid;

    @ApiModelProperty(value = "银行卡密码")
    private String cpassword;

    @ApiModelProperty(value = "银行卡余额")
    private Integer balance;

    @ApiModelProperty(value = "银行卡描述")
    private String description;


}
