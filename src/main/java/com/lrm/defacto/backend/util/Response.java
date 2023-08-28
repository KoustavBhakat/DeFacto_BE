package com.lrm.defacto.backend.util;

public class Response<T> {

	/** The status. */
	private String status;

	/** The message */
	private String message;

	/** The data. */
	private T data;

	/**
	 * Instantiates a new response.
	 */
	public Response() {
		super();
	}

	/**
	 * Instantiates a new response.
	 *
	 * @param status     the status
	 * @param statusCode the status code
	 * @param message    the message
	 * @param data       the data
	 */
	public Response(String status, T data) {
		super();
		this.status = status;
		this.data = data;
	}

	/**
	 * Instantiates a new response.
	 *
	 * @param status  the status
	 * @param message the message
	 * @param data    the data
	 */
	public Response(String status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}
}
