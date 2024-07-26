package vip.example.qrbasedcatalog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vip.example.qrbasedcatalog.files.FileStorageServices;
import vip.example.qrbasedcatalog.model.Product;
import vip.example.qrbasedcatalog.model.Website;
import vip.example.qrbasedcatalog.repository.ProductRepository;
import vip.example.qrbasedcatalog.repository.WebRepo;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final WebRepo webRepo;

    public ProductService(ProductRepository productRepository,WebRepo webRepo) {
        this.productRepository = productRepository;
        this.webRepo = webRepo;
    }

    @Autowired
    FileStorageServices storageServices;
//    public Product createProduct()


    public Product saveProduct(Product product) {
        // Generate QR code here
        // Save image and video files

        return productRepository.save(product);
    }

//    public Product getProductByProductId(String productId) {
//        return productRepository.findByProductId(productId);
//    }

    public Product createProduct(String name, MultipartFile image, MultipartFile video) {
        if (image==null){
            return null;
        }
        if (video==null){
            return null;
        }
        Product product = new Product();
        product.setName(name);
        product.setImagePath(storageServices.storeFile(image,name+".jpg"));
        product.setVideoPath(storageServices.storeFile(video,name+".mp4"));
//        Website website = webRepo.findByWebsiteId(1l);
//        if (website!=null)
//            product.setUrl(website.getUrl());
        return saveProduct(product);
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProduct(String name){
        return productRepository.findByName(name);
    }

    public Page<Product> searchProduct(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> results;
        return  productRepository.findByNameContainingIgnoreCase(query, pageable);
    }
    @Transactional
    public void deleteProduct(String name) {
        Product product = getProduct(name);
        storageServices.deleteFile(product.getImagePath());
        storageServices.deleteFile(product.getVideoPath());
        productRepository.delete(product);
    }

    public Product updateProduct(String name, String newName, MultipartFile newImage, MultipartFile newVideo) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            return null;
        }

        if (newName != null && !newName.isEmpty()) {
            product.setName(newName);
        }
//        if (newUrl != null && !newUrl.isEmpty()) {
//            Website website = webRepo.findByWebsiteId(1l);
//            if (website!=null)
//            product.setUrl(website.getUrl());
//        }
        if (newImage != null && !newImage.isEmpty()) {
            product.setImagePath(storageServices.storeFile(newImage, product.getName() + ".jpg"));
        }
        if (newVideo != null && !newVideo.isEmpty()) {
            product.setVideoPath(storageServices.storeFile(newVideo, product.getName() + ".mp4"));
        }

        return saveProduct(product);
    }

    public Website addUpdateWeb(String url){
        Website website = webRepo.findByWebsiteId(1l);
        if(website!=null){
            website.setUrl(website.getUrl());
            return webRepo.save(website);
        }else {
            website = new Website();
            website.setWebsiteId(1l);
            website.setUrl(url);
            return  webRepo.save(website);
        }
    }

    public Website getWebsite(){
        return  webRepo.findByWebsiteId(1l);
    }
}
