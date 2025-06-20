package org.ysu.mall.util;

import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public  class FileUtil {

    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 上传单个文件到MinIO
     */
    public  String uploadFile(MultipartFile file) throws Exception {
        // 检查桶是否存在，不存在则创建
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 上传文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        // 返回文件URL
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        ).object();
    }

    /**
     * 批量上传文件到MinIO
     */
    public  List<String> uploadFiles(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                urls.add(uploadFile(file));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return urls;
    }

    /**
     * 删除单个文件
     * @param objectName 要删除的文件名（从getObject返回的object()值）
     * @return 是否删除成功
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
     * @param objectNames 要删除的文件名列表（从getObject返回的object()值）
     * @return 删除成功的文件列表
     */
    public List<String> deleteFiles(List<String> objectNames) {
        List<String> deletedFiles = new ArrayList<>();
        for (String objectName : objectNames) {
            try {
                deleteFile(objectName);
                deletedFiles.add(objectName);
            } catch (Exception e) {
                // 记录删除失败的文件，可以根据需要处理
            }
        }
        return deletedFiles;
    }

    /**
     * 根据URL删除文件（如果URL包含对象名）
     * @param url 文件URL
     * @return 是否删除成功
     */
    public boolean deleteFileByUrl(String url) throws Exception {
        // 从URL中提取对象名（假设URL格式为 http://minio-server/bucket/objectName）
        String objectName = url.substring(url.lastIndexOf('/') + 1);
        return deleteFile(objectName);
    }

    /**
     * 根据URL批量删除文件
     * @param urls 文件URL列表
     * @return 删除成功的URL列表
     */
    public List<String> deleteFilesByUrls(List<String> urls) {
        List<String> deletedUrls = new ArrayList<>();
        for (String url : urls) {
            try {
                if (deleteFileByUrl(url)) {
                    deletedUrls.add(url);
                }
            } catch (Exception e) {
                // 记录删除失败的URL，可以根据需要处理
            }
        }
        return deletedUrls;
    }
}