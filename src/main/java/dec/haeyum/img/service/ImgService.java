package dec.haeyum.img.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ImgService {
    Resource getImg(String fileName);


    String downloadImg(String url) ;

    String downloadImg(@NotNull MultipartFile profileImg);

    void deleteImg(String profileImg);

    String downloadImg(Path path);

    ResponseEntity<byte[]> compressImg(String fileName);
}
