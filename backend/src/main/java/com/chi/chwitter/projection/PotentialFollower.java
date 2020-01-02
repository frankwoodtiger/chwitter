package com.chi.chwitter.projection;
/*
    This class is for the use of projection. A Spring Data technique for constructing a DTO with
    only the fields that we need. This also improves the performance of query.

    See more: https://www.baeldung.com/spring-data-jpa-projections
 */
public interface PotentialFollower {
    public long getId();
    public String getUsername();
}
