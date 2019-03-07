package com.zhu.bobi.commonutil;

import java.util.List;

public abstract interface IGenericPage<T> {

  public abstract boolean isFirstPage();

  public abstract boolean isLastPage();

  public abstract boolean hasNextPage();

  public abstract boolean hasPreviousPage();

  public abstract Integer getLastPageNo();

  public abstract List<T> getThisPageElements();

  public abstract Long getCount();

  public abstract Integer getThisPageFirstElementNumber();

  public abstract Integer getThisPageLastElementNumber();

  public abstract Integer getNextPageNo();

  public abstract Integer getPreviousPageNo();

  public abstract Integer getLimit();

  public abstract Integer getPage();
}
