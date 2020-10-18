package com.baizhi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 创建者：xw
 * 类的作用：
 * 创建时间：2020/10/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String sex;
    private List<CityDTO> city;
}
