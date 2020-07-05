package com.atguigu.car.task;

import com.atguigu.car.utils.AppFileUtils;
import com.atguigu.car.utils.SysConstast;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@EnableScheduling// 开启定时任务
public class RecyleTempFileTask {

    /**
     * 每天晚上12点删除，可以使用在线cron生成器
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void recyleTempFile() {
        File file = new File(AppFileUtils.PATH);

        deleteFile(file);
    }

    /**
     * 删除图片，使用递归
     *
     * @param file
     */
    private void deleteFile(File file) {
        // 如果文件不为null
        if (null != file) {
            // 获得file中所有的子包
            File[] files = file.listFiles();
            if (null != files && files.length > 0) {
                for (File file1 : files) {
                    // 如果file1是文件并且以_temp结尾，将其删除
                    if (file1.isFile() && file1.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                        file1.delete();
                    } else {
                        // 如果不是文件，递归遍历
                        deleteFile(file1);
                    }
                }
            }
        }
    }
}
