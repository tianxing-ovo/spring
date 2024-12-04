package com.ltx.controller;

import com.ltx.async.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
public class ExportController {

    @Resource
    private ExportService exportService;

    /**
     * 异步导出
     */
    @PostMapping("/async-export")
    public ResponseEntity<Map<String, String>> asyncExport() {
        // 生成唯一的任务ID
        String taskId = UUID.randomUUID().toString();
        // 异步导出
        exportService.asyncExport();
        // com.ltx.async.ExportService$$EnhancerBySpringCGLIB$$4f2bdd64
        log.info("{}", exportService.getClass().getName());
        Map<String,String> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("message", "success");
        return ResponseEntity.ok(map);
    }
}
