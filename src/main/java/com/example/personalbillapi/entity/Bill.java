package com.example.personalbillapi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shironekoa3
 * @since 2023-10-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    @Getter
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账单订单号
     */
    @Getter
    private String orderId;

    /**
     * 账单类型(0支出1收入)
     */
    @Getter
    private Integer type;

    /**
     * 账单支付方式
     */
    @Getter
    private String method;

    /**
     * 账单金额
     */
    @Getter
    private Object amount;

    /**
     * 账单时间
     */
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime date;

    /**
     * 账单分类
     */
    @Getter
    private Long categoryId;

    /**
     * 账单备注
     */
    @Getter
    private String description;

    /**
     * 创建时间
     */
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Getter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除(0正常1删除)
     */
    @TableLogic
    private Boolean isDeleted;

    /**
     * 分类
     */
    @Getter
    @TableField(exist = false)
    private Category category;

}
