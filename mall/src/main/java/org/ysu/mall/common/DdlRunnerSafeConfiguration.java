package org.ysu.mall.common;

import com.baomidou.mybatisplus.autoconfigure.DdlApplicationRunner;
import com.baomidou.mybatisplus.extension.ddl.IDdl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Configuration
public class DdlRunnerSafeConfiguration {

    @Bean
    public DdlApplicationRunner ddlApplicationRunner(List<IDdl> ddlList) {
        if (ddlList == null || ddlList.isEmpty()) {
            return new DdlApplicationRunner(List.of()) {
                @Override
                public void run(ApplicationArguments args) {
                    // 空实现，避免 NullBean 报错
                }
            };
        }
        return new DdlApplicationRunner(ddlList);
    }
}