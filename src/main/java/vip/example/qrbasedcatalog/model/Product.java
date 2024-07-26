package vip.example.qrbasedcatalog.model;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long productId;

    @Column(unique = true)
    private String name;
    private String imagePath;
    private String videoPath;
    private String qrCodePath;
	private String url;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getVideoPath() {
		return videoPath;
	}
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public String getQrCodePath() {
		return qrCodePath;
	}
	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}
    
    
    
}
