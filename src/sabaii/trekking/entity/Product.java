package sabaii.trekking.entity;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model of the product
 * @author Sabaii Soft. SKE10
 *
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@XmlAttribute
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private long id;
	private String name, description, feature, spec, created_at, updated_at;
	private double price, weight, width, height, depth, percent_discount;
	private int quantity;
	@Transient
	private Link link; 
	
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public double getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(double percent_discount) {
		this.percent_discount = percent_discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " " + name + ":";
	}
	
	/**
	 * method to check that the id is equal or not
	 */
	public boolean equals(Object other) {
		if (other == null || other.getClass() != this.getClass()) return false;
		Product contact = (Product) other;
		return contact.getId() == this.getId();
	}
	

	@Override
	public int hashCode() {
		return (id + name + quantity + price + description).hashCode();
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

}