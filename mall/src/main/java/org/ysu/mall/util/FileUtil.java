package org.ysu.mall.util;

import io.minio.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.ysu.mall.config.MinioConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    private final MinioConfig minioConfig;
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;


    public FileUtil(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    @PostConstruct
    public void init() {
        this.minioClient = minioConfig.minioClient();
    }

    /**
     * 上传单个文件并返回公开桶中的永久链接
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 1. 检查桶是否存在，不存在则创建
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }

        // 2. 生成唯一文件名
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 3. 上传文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        // 4. 返回拼接的永久访问 URL（仅适用于公开桶）
        return minioConfig.getEndpoint() + "/" + bucketName + "/" + fileName;
    }

    /**
     * 批量上传文件
     */
    public List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                urls.add(uploadFile(file));
            } catch (Exception e) {
                e.printStackTrace(); // 可换为日志记录
            }
        }
        return urls;
    }

    /**
     * 删除单个文件
     */
    public boolean deleteFile(String objectName) throws Exception {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            return true;
        } catch (Exception e) {
            throw new Exception("删除文件失败: " + e.getMessage());
        }
    }

    /**
     * 批量删除文件
     */
    public List<String> deleteFiles(List<String> objectNames) {
        List<String> deletedFiles = new ArrayList<>();
        for (String objectName : objectNames) {
            try {
                deleteFile(objectName);
                deletedFiles.add(objectName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 根据 URL 删除文件（提取 objectName）
     */
    public boolean deleteFileByUrl(String url) throws Exception {
        String objectName = url.substring(url.lastIndexOf('/') + 1);
        return deleteFile(objectName);
    }

    /**
     * 批量根据 URL 删除文件
     */
    public List<String> deleteFilesByUrls(List<String> urls) {
        List<String> deletedUrls = new ArrayList<>();
        for (String url : urls) {
            try {
                if (deleteFileByUrl(url)) {
                    deletedUrls.add(url);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedUrls;
    }
}
