package vip.example.qrbasedcatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vip.example.qrbasedcatalog.model.Website;

public interface WebRepo extends JpaRepository<Website,Long> {
    Website findByWebsiteId(long Id);
}
