package com.myspring.app07.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class GuestListVO {
	private List<GuestVO> arr;
	private int count;
	
//public GuestListVO(List<GuestVO>arr,int count) {
//	this.arr = arr;
//	this.count = count;
//}
}
