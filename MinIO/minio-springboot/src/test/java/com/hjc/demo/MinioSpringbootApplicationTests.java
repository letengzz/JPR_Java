package com.hjc.demo;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class MinioSpringbootApplicationTests {

    @Test
    void contextLoads() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://192.168.100.200:9000")  //构建一个端点
                .credentials("admin", "password")  //访问的账号和密码
                .build();

    }

    @Resource
    private MinioClient minioClient;

    @Test
    void test() {
        System.out.println(minioClient);
    }

    //判断存储桶是否存在
    @Test
    void existsBucketTest() throws Exception {
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test").build());
        System.out.println("test目录是否存在：" + exists);
    }

    //创建存储桶
    @Test
    void makeBucketTest() throws Exception {
        //判断是否存在
        boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test").build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket("test").build());
            System.out.println("bucket创建成功！");
        } else {
            System.out.println("bucket已经存在，无需再次创建！");
        }
    }

    //列出所有的存储桶
    @Test
    void bucketListTest() throws Exception {
        //获取所有的存储桶
        List<Bucket> buckets = minioClient.listBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.name() + "---" + bucket.creationDate());
        });
    }

    //删除存储桶
    @Test
    void removeBucketTest() throws Exception {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket("test").build());
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println("删除失败");
        }
    }

    //上传文件
    @Test
    void uploadFileTest() throws Exception {
        File file = new File("/Users/cuifendemac/minio.png");
        ObjectWriteResponse writeResponse = minioClient.putObject(PutObjectArgs.builder()
                .bucket("test") //存储到指定的存储桶
                .object("test.png") //存储到指定的对象
                .stream(new FileInputStream(file), file.length(), -1) //上传的文件流  objectSize:文件大小 partSize: 缓冲区大小 一般为-1
                .contentType("image/jpeg") //上传的文件类型
                .build());
        System.out.println("上传成功：" + writeResponse);
    }

    @Test
    void uploadFileTest2() throws Exception {
        ObjectWriteResponse uploadedObject = minioClient.uploadObject(UploadObjectArgs.builder()
                .bucket("test") //存储到指定的存储桶
                .object("test1.png") //存储到指定的对象
                .filename("/Users/cuifendemac/minio.png") //上传的文件路径
                .build());
        System.out.println("上传成功：" + uploadedObject);
    }


    @Test
    void existsObjectTest() throws Exception {
        try {
            StatObjectResponse statObject = minioClient.statObject(StatObjectArgs.builder()
                    .bucket("test") //存储到指定的存储桶
                    .object("test.png") //存储到指定的对象
                    .build());
            System.out.println(statObject);
        } catch (Exception e) {
//        e.printStackTrace();
            System.out.println("文件不存在");
        }
    }

    @Test
    void getUrlTest() throws Exception {
        String url = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket("test") //存储到指定的存储桶
                .object("test1.png") //存储到指定的对象
                .expiry(3, TimeUnit.MINUTES) //过期时间
                .method(Method.GET) //请求方式
                .build());
        System.out.println("url = " + url);
    }

@Test
void objectDownTest() throws Exception {
    GetObjectResponse getObjectResponse = minioClient.getObject(GetObjectArgs.builder()
            .bucket("test")
            .object("test.png")
            .build());
    System.out.println(getObjectResponse.transferTo(new FileOutputStream("/Users/cuifendemac/minio2.png")));
}
@Test
void objectListTest() throws Exception {
    Iterable<Result<Item>> listedObjects = minioClient.listObjects(ListObjectsArgs.builder()
            .bucket("test")
            .build());
    for (Result<Item> result : listedObjects) {
        Item item = result.get();
        System.out.println(item.objectName() +" -- "+item.size() + " -- " + item.lastModified());
    }
}

@Test
void removeObjectTest() throws Exception {
    minioClient.removeObject(RemoveObjectArgs.builder()
            .bucket("test")
            .object("test1.png")
            .build());
    System.out.println("删除成功");
}
@Test
void publicUrlTest() throws Exception {
    String bucketName = "test2";
    //判断是否存在
    boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    if (!exists) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        System.out.println("bucket创建成功！");
    } else {
        System.out.println("bucket已经存在，无需再次创建！");
    }

    String policyJsonString = "{\n" +
            "    \"Version\": \"2012-10-17\",\n" +
            "    \"Statement\": [\n" +
            "        {\n" +
            "            \"Sid\": \"PublicRead\",\n" +
            "            \"Effect\": \"Allow\",\n" +
            "            \"Principal\": {\n" +
            "                \"AWS\": [\n" +
            "                    \"*\"\n" +
            "                ]\n" +
            "            },\n" +
            "            \"Action\": [\n" +
            "                \"s3:GetObject\"\n" +
            "            ],\n" +
            "            \"Resource\": [\n" +
            "                \"arn:aws:s3:::"+bucketName+"/*\"\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    //创建存储桶的时候，设置该存储桶的文件的访问策略，运行公开的读
    minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder()
                    .bucket(bucketName)
                    .config(policyJsonString) //访问策略
                    .build()
    );
}

}
