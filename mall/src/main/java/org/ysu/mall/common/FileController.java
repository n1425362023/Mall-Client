package org.ysu.mall.common;

import cn.hutool.core.io.resource.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {
        // 实现：保存文件到本地 / 阿里云 OSS / 七牛云 等
        // 返回图片 URL
        return null;
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        // 实现：从本地或云服务中读取文件并返回
        return null;
    }
}
