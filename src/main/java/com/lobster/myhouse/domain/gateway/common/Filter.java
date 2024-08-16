package com.lobster.myhouse.domain.gateway.common;

public class Filter {
    private int pageNumber;
    private int pageSize;
    private String sortBy;
    private boolean ascending;

    private Filter(Builder builder) {
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.sortBy = builder.sortBy;
        this.ascending = builder.ascending;
    }

    public static class Builder {
        private int pageNumber = 0;
        private int pageSize = 10;
        private String sortBy;
        private boolean ascending;

        public Builder pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder sortBy(String sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public Builder ascending(boolean ascending) {
            this.ascending = ascending;
            return this;
        }

        public Filter build() {
            return new Filter(this);
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public boolean isAscending() {
        return ascending;
    }
}
