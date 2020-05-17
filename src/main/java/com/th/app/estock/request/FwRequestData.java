package com.th.app.estock.request;

import com.th.app.estock.response.Paginator;

public class FwRequestData<T> {
	
	public T formData;
	public Paginator paginator;
	
	public T getFormData() {
		return formData;
	}
	public void setFormData(T formData) {
		this.formData = formData;
	}
	public Paginator getPaginator() {
		return paginator;
	}
	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
}
