package com.zhu.bobi.commonutil;

import org.dom4j.tree.AbstractEntity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GeneralDefaultPage<T extends AbstractEntity> implements IGenericPage<T>, Serializable {
  private static final long serialVersionUID = 8268962248016115755L;
  private List<T> data;
  private Integer limit;
  private Integer page;
  private Long count = 0l;
  @SuppressWarnings({"unchecked", "rawtypes"})
  public static final GeneralDefaultPage<?> EMPTY_PAGE = new GeneralDefaultPage(0, 0, Collections.emptyList(), 0l);

  public GeneralDefaultPage(Integer page, Integer limit, List<T> data, Long count) {
    this.page = page;
    this.limit = limit;
    this.data = data;
    this.count = count;
  }

  public static <E> GeneralDefaultPage<?> emptyPage() {
    return (GeneralDefaultPage<?>) EMPTY_PAGE;
  }

  public boolean isFirstPage() {
    return (getPage() == 0);
  }

  public boolean isLastPage() {
    return (getPage() >= getLastPageNo());
  }

  public boolean hasNextPage() {
    return (this.data != null);
  }

  public boolean hasPreviousPage() {
    return (getPage() > 0);
  }

  public Integer getLastPageNo() {
    Long totalResults = getCount();
    return (int) ((totalResults % getLimit() == 0) ? Math.floor(totalResults / getLimit())
        : Math.floor(totalResults / getLimit()) + 1);
  }

  public List<T> getThisPageElements() {
    return this.data;
  }

  public Long getCount() {
    return this.count;
  }

  public Integer getThisPageFirstElementNumber() {
    return (getPage() * getLimit() + 1);
  }

  public Integer getThisPageLastElementNumber() {
    Integer fullPage = getThisPageFirstElementNumber() + getLimit() - 1;
    return (getCount() < fullPage) ? getCount().intValue() : fullPage;
  }

  public Integer getNextPageNo() {
    return (getPage() + 1);
  }

  public Integer getPreviousPageNo() {
    return (getPage() - 1);
  }

  public Integer getLimit() {
    return this.limit;
  }

  public Integer getPage() {
    return this.page;
  }

  public static int getStartOfPage(int pageNo, int pageSize) {
    int startIndex = (pageNo - 1) * pageSize + 1;
    if (startIndex < 1) {
      startIndex = 1;
    }
    return startIndex;
  }

}