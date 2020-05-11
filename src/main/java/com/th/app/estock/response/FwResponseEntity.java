package com.th.app.estock.response;

import java.util.List;

public class FwResponseEntity<T> extends MessageResponse {
	
	public T result;
	public List<T> results;
	public Paginator paginator;
	
	public T getResult() {
		return result;
	}
	
	public void setResult(T result) {
		this.result = result;
	}
	
	public List<T> getResults() {
		return results;
	}
	
	public void setResults(List<T> results) {
		this.results = results;
	}
	
	public Paginator getPaginator() {
		return paginator;
	}
	
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
}
