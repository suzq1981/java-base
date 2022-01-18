package com.djt.mapper.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserinfoDto extends BaseDto {

	private String userName;

}
