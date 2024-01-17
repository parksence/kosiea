package com.codex.kosiea.common;

public class Pagination {
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getTotalListCnt() {
        return totalListCnt;
    }

    public void setTotalListCnt(int totalListCnt) {
        this.totalListCnt = totalListCnt;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public void setTotalPageCnt(int totalPageCnt) {
        this.totalPageCnt = totalPageCnt;
    }

    public int getTotalBlockCnt() {
        return totalBlockCnt;
    }

    public void setTotalBlockCnt(int totalBlockCnt) {
        this.totalBlockCnt = totalBlockCnt;
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

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPrevBlock() {
        return prevBlock;
    }

    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    private int pageSize = 10;  /** 페이지 당 보여지는 게시글의 최대 개수 **/
    private int blockSize = 5; /** 화면 하단에 출력할 페이지 사이즈 **/
    private int page = 1;       /** 현재 페이지 번호 **/
    private int block = 1;      /** 현재 블럭 **/
    private int totalListCnt;   /** 총 게시글 수 **/
    private int totalPageCnt;   /** 총 페이지 수 **/
    private int totalBlockCnt;  /** 총 블럭 수 **/
    private int startPage = 1;  /** 블럭 시작 페이지 **/
    private int endPage = 1;    /** 블럭 마지막 페이지 **/
    private int startIndex = 0; /** DB 접근 시작 index **/
    private int prevBlock;      /** 이전 블럭의 마지막 페이지 **/
    private int nextBlock;      /** 다음 블럭의 시작 페이지 **/

    public Pagination(int totalListCnt, int page) {

        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.
        // 총 게시물 수	- totalListCnt
        // 현재 페이지	- page

        setPage(page); /** 3. 현재 페이지 **/
        setTotalListCnt(totalListCnt); /** 5. 총 게시글 수 **/

        /** 6. 총 페이지 수 **/
        // 한 페이지의 최대 개수를 총 게시물 수 * 1.0로 나누어주고 올림 해준다.
        // 총 페이지 수 를 구할 수 있다.
        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));

        /** 7. 총 블럭 수 **/
        // 한 블럭의 최대 개수를 총  페이지의 수 * 1.0로 나누어주고 올림 해준다.
        // 총 블럭 수를 구할 수 있다.
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));

        /** 4. 현재 블럭 **/
        // 현재 페이지 * 1.0을 블록의 최대 개수로 나누어주고 올림한다.
        // 현재 블록을 구할 수 있다.
        setBlock((int) Math.ceil((page * 1.0)/blockSize));

        /** 8. 블럭 시작 페이지 **/
        setStartPage((block - 1) * blockSize + 1);

        /** 9. 블럭 마지막 페이지 **/
        setEndPage(startPage + blockSize - 1);

        /* === 블럭 마지막 페이지에 대한 validation ===*/
        if(endPage > totalPageCnt){this.endPage = totalPageCnt;}

        /** 11. 이전 블럭(클릭 시, 이전 블럭 마지막 페이지) **/
        setPrevBlock((block * blockSize) - blockSize);

        /* === 이전 블럭에 대한 validation === */
        if(prevBlock < 1) {this.prevBlock = 1;}

        /** 12. 다음 블럭(클릭 시, 다음 블럭 첫번째 페이지) **/
        setNextBlock((block * blockSize) + 1);

        /* === 다음 블럭에 대한 validation ===*/
        if(nextBlock > totalPageCnt) {nextBlock = totalPageCnt;}

        /** 10. DB 접근 시작 index **/
        setStartIndex((page-1) * pageSize);
    }

}
