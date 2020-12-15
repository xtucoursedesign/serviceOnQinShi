package org.bean;

import java.io.Serializable;

public class Part implements Serializable{

	private static final long serialVersionUID = 1L;
	private String pid;
	private String name;
	private String material;
	private String specifi;
	private String texture;
	private String weight;
	private String img;
	private String unit;
	
	public Part() {
		super();
	}

	public Part(String pid, String name, String material, String specifi, String texture, String weight, String img,
			String unit) {
		super();
		this.pid = pid;
		this.name = name;
		this.material = material;
		this.specifi = specifi;
		this.texture = texture;
		this.weight = weight;
		this.img = img;
		this.unit = unit;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSpecifi() {
		return specifi;
	}

	public void setSpecifi(String specifi) {
		this.specifi = specifi;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Part [pid=" + pid + ", name=" + name + ", material=" + material + ", specifi=" + specifi + ", texture="
				+ texture + ", weight=" + weight + ", img=" + img + ", unit=" + unit + "]";
	}
}
