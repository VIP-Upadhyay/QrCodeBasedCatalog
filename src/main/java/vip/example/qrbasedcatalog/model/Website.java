package vip.example.qrbasedcatalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Website {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "web_generator")
    private Long websiteId;

    private String url;
}
