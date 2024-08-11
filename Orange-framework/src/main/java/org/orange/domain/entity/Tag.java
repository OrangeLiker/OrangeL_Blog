package org.orange.domain.entity;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 标签(Tag)表实体类
 *
 * @author makejava
 * @since 2024-08-08 15:17:41
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_tag")
public class Tag {

         
    @TableId
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long id;

    /**
     * 标签名
     */     
 
    private String name;

         
 
    private Long createBy;

         
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

         
 
    private Long updateBy;

         
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */     
 
    private Integer delFlag;

    /**
     * 备注
     */     
 
    private String remark;

}

