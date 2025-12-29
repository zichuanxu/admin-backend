package org.xu.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xu.admin.annotation.Auth;
import org.xu.admin.common.Result;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.port}")
    private String port;

    // 获取配置文件中定义的上传路径
    @Value("${file.upload.path}")
    private String uploadPath;

    // 获取访问前缀
    @Value("${file.upload.access-path}")
    private String accessPath;

    @PostMapping("/upload")
    @Auth
    public Result<String> upload(@RequestParam MultipartFile file) throws IOException {
        // 1. 获取原文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = FileUtil.extName(originalFilename); // 需要处理空指针，这里简化演示

        // 2. 生成唯一文件名 (防止覆盖)
        String fileName = IdUtil.fastSimpleUUID() + "." + suffix;

        // 3. 创建文件目录对象
        File saveFile = new File(uploadPath + fileName);
        if (!saveFile.getParentFile().exists()) {
            saveFile.getParentFile().mkdirs();
        }

        // 4. 保存文件到磁盘
        file.transferTo(saveFile);

        // 5. 返回可访问的 URL (相对路径或绝对路径均可，这里返回完整URL方便前端)
        // 注意：生产环境通常返回相对路径，IP由前端拼接。这里为了演示简单返回全路径。
        // 假设本地开发 IP 为 localhost
        String url = "http://localhost:" + port + accessPath + fileName;
        return Result.success(url);
    }
}