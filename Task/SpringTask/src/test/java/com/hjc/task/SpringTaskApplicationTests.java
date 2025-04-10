package com.hjc.task;

import com.hjc.task.config.ScheduleTask;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTaskApplicationTests {

	@Resource
	private ScheduleTask scheduleTask;

	@Test
	void contextLoads() {
		scheduleTask.setCron("0/15 * * * * ?");
	}

}
