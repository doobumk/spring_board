package document.sort;

import java.util.Date;
import java.util.List;
import document.Document;

/**
 * Created by User on 2017-05-22.
 */
public class DocumentListCommand  {
    private int pageNo;
    private int total;
    private int size;
    private int currentPage;
    private int totalPage;
    private int startPage;
    private int endPage;
    private String searchCondition;
    private String searchKeyword;

    public void Paging(){
        if(total == 0){
            totalPage =0;
            startPage=0;
            endPage=0;
        }else{
            totalPage = total/size;
            if(total%size > 0){
                totalPage++;
            }
            int modVal = currentPage % 5;
            startPage = currentPage / 5 % 5 + 1;
            if(modVal == 0){
                startPage -= 5;
            }
            endPage = startPage + 4;
            if(endPage > totalPage){
                endPage = totalPage;
            }
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean hasNoDocument(){
        return total ==0;
    }

    public  boolean hasDocument(){
        return total>0;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }




    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "DocumentListCommand{" +
                "pageNo=" + pageNo +
                ", total=" + total +
                ", size=" + size +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", searchCondition='" + searchCondition + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                '}';
    }
}
