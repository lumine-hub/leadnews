package com.heima.minio.test;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

public class MinIOTest {


    public static void main(String[] args) {

        FileInputStream fileInputStream = null;
        try {

            fileInputStream =  new FileInputStream("F:\\dowloads\\index.js");;

            //1.创建minio链接客户端
            MinioClient minioClient = MinioClient.builder()
                    .credentials("minio", "minio123")
                    .endpoint("http://192.168.200.130:9000")
                    .build();
            //2.上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("plugins/js/index.js")  // 目标路径和文件名
                    .contentType("application/javascript")  // 根据文件类型设置
                    .bucket("leadnews")  // 桶名
                    .stream(fileInputStream, fileInputStream.available(), -1)  // 文件流
                    .build();
            minioClient.putObject(putObjectArgs);

            System.out.println("文件上传成功: http://192.168.200.130:9000/leadnews/plugins/js/index.js");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}