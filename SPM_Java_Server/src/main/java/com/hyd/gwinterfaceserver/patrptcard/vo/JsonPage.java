package com.hyd.gwinterfaceserver.patrptcard.vo;

import lombok.Data;

@Data
public class JsonPage {
    public int totalCount;
    public int totalPages;
    public int numberOfPages;
    public int currentPage;
}