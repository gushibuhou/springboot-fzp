package com.jyp.springboot;


//import org.beetl.core.resource.WebAppResourceLoader;
//import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
//import org.beetl.ext.spring.BeetlSpringViewResolver;
//import org.beetl.sql.core.ClasspathLoader;
//import org.beetl.sql.core.Interceptor;
//import org.beetl.sql.core.UnderlinedNameConversion;
//import org.beetl.sql.core.db.MySqlStyle;
//import org.beetl.sql.ext.DebugInterceptor;
//import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
//import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
//import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	//	-----查看启动的时候为我们注入了哪些bean------start
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
	//	-----查看启动的时候为我们注入了哪些bean------end


	//	-----导入相关的bean,beatlsql的快速启动装配,包括数据源，包扫描，事物管理器等。---------start
	/*
	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			// WebAppResourceLoader 配置root路径是关键
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(patternResolver.getResource("classpath:/templates").getFile().getPath());
			beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//读取配置文件信息
		return beetlGroupUtilConfiguration;

	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}

	//配置包扫描
	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		conf.setBasePackage("com.jyp.springboot.dao");
		conf.setDaoSuffix("Dao");
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("datasource") DataSource datasource) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();

		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		factory.setDbStyle(new MySqlStyle());
		factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
		factory.setNc(new UnderlinedNameConversion());//开启驼峰
		factory.setSqlLoader(new ClasspathLoader("/sql"));//sql文件路径
		return factory;
	}


	//配置数据库
	@Bean(name = "datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().url("jdbc:mysql://127.0.0.1:3306/jeecg_test").username("root").password("123456").build();
	}

	//开启事务
	@Bean(name = "txManager")
	public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("datasource") DataSource datasource) {
		DataSourceTransactionManager dsm = new DataSourceTransactionManager();
		dsm.setDataSource(datasource);
		return dsm;
	}
	*/
	//	-----导入相关的bean,beatlsql的快速启动装配,包括数据源，包扫描，事物管理器等。---------end
}
