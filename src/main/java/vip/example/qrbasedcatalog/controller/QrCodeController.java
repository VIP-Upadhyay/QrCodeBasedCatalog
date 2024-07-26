package vip.example.qrbasedcatalog.controller;

import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.example.qrbasedcatalog.service.QRCodeService;

import java.io.IOException;

@Controller
@RequestMapping("/admin/qrcode")
public class QrCodeController {
    @Autowired
    QRCodeService qrCodeService;

    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCodeWithLogo(
            @PathVariable String name, HttpServletRequest request
    ) throws IOException, WriterException {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        String baseUrl = scheme + "://" + serverName;

        if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
            baseUrl += ":" + serverPort;
        }

        baseUrl += contextPath;

        System.out.println(baseUrl);
        byte[] qrCodeImage = qrCodeService.generateQRCode(baseUrl+"/getProduct/"+name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCodeImage.length);
        return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);

    }
}
