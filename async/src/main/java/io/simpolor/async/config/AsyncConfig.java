package io.simpolor.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

	// 어플리케이션 레벨의 Async 선언
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("simpolor-async-");
		executor.initialize();

		return executor;
	}

	// 메소드 레벨의 Async 선언
	@Bean("threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("thread-async-");
		executor.initialize();

		return executor;
	}

	/***
	 * setCorePoolSize : 기본 쓰레드 사이즈
	 * setMaxPoolSize : 최대 쓰레드 사이즈
	 * setQueueCapacity : Max 쓰레드가 동작하는 경우 대기하는 queue 사이즈
	 */
}
