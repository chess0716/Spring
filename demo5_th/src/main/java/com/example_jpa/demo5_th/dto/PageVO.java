package com.example_jpa.demo5_th.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {
	private int totPage;
	private int blockPage;
	private int startPage;
	private int endPage;
	private int currentPage;
	
	public PageVO(int count,int pageSize, int currentPage) {
		totPage = count/pageSize + (count%pageSize==0 ? 0: 1); //37   한화면 5  ==>총페이수  37/5+1 ==>총페이지수 8
		blockPage = 3 ;  //[이전]  456 [다음]    
		startPage = ((currentPage-1)/blockPage) * blockPage + 1;
		endPage = startPage+blockPage-1 ;  //계산상 마지막페이지
		if(endPage  > totPage) endPage = totPage; // totPage 실제 마지막 페이지
		
		setBlockPage(blockPage);
		setStartPage(startPage);
		setEndPage(endPage);
		setCurrentPage(currentPage);
		setTotPage(totPage);
	}

}
