package com.myapp.dto;


import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class PageVO {
	private int totPage ; //총페이수
	private int blockPage; 		 
	private int startPage;
	private int endPage;
	private int currentPage;
	private String field;
	private String word;
	
	
	public PageVO(int count, int currentPage,int pageSize ) { //count = 37
		totPage = count/pageSize + (count % pageSize ==0 ? 0 : 1 );  // 37/5 ==>7 +1 ==> 8
		blockPage = 3; // 이전과 다음 사이 페이지 수  [이전] 4 5 6 [다음]   //currentPage = 7
		startPage = ((currentPage-1)/blockPage)*blockPage+1;  //startPage = ((7-1)/3)*3 +1 =7
		endPage = 	startPage +	blockPage -1;   //endPage = 7+3-1 = 9  [이전] 7 8 9
		
		if (totPage <  endPage)  endPage=totPage;
		setStartPage(startPage);
		setEndPage(endPage);
		setBlockPage(blockPage);
		setTotPage(totPage);
		setCurrentPage(currentPage);
				
	}

}
