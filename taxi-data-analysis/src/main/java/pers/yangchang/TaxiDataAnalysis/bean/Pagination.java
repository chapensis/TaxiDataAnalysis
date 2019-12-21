package pers.yangchang.TaxiDataAnalysis.bean;

import lombok.Data;

@Data
public class Pagination {
    private int pageNum;
    private int pageSize;
    private Long total;
}
