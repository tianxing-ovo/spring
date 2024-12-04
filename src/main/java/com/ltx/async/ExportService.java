package com.ltx.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExportService {

    @Async
    public void asyncExport(){
        log.info("导出数据");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
        log.info("导出成功");
    }
}
