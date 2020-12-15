package org.bean;

public class Main {
	private String mid;
	private String name;
	private String material;
	private String specifi;
	private String texture;
	private String weight;
	private String img;
	private String unit;
	
	public Main() {
		super();
	}

	public Main(String mid, String name, String material, String specifi, String texture, String weight, String img,
			String unit) {
		super();
		this.mid = mid;
		this.name = name;
		this.material = material;
		this.specifi = specifi;
		this.texture = texture;
		this.weight = weight;
		this.img = img;
		this.unit = unit;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
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

	@Override
	public String toString() {
		return "Main [mid=" + mid + ", name=" + name + ", material=" + material + ", specifi=" + specifi + ", texture="
				+ texture + ", weight=" + weight + ", img=" + img + ", unit=" + unit + "]";
	}	
}
