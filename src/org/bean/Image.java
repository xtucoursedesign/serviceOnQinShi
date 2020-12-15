package org.bean;

public class Image {
	private String uid;
	private String name;
	private String status;
	private String url;
	
	public Image() {
		super();
	}

	public Image(String uid, String name, String status, String url) {
		super();
		this.uid = uid;
		this.name = name;
		this.status = status;
		this.url = url;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Image [uid=" + uid + ", name=" + name + ", status=" + status + ", url=" + url + "]";
	}
}
