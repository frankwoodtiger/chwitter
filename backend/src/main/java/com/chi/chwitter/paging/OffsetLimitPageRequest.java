package com.chi.chwitter.paging;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/*
    See:
        https://stackoverflow.com/questions/30217552/spring-data-pageable-and-limit-offset/30219294#30219294
        https://stackoverflow.com/questions/25008472/pagination-in-spring-data-jpa-limit-and-offset
 */

public class OffsetLimitPageRequest implements Pageable {
    private int limit;
    private int offset;
    private Sort sort;

    public OffsetLimitPageRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    public OffsetLimitPageRequest(int offset, int limit) {
        this(offset, limit, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageRequest(offset + limit, limit, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? new OffsetLimitPageRequest(offset - limit, limit, sort) : this;
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageRequest(0, limit, sort);
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
