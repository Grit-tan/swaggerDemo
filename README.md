## SpringBoot集成Swagger2

### 什么是swagger?

能够设计api, 开发api，以及跟api形成文档的工具

### 为什么要集成swagger？

手写Api文档的几个痛点：

1.文档需要更新的时候，需要再次发送一份给前端，也就是文档更新交流不及时。
2.接口返回结果不明确
3.不能直接在线测试接口，通常需要使用工具，比如postman
4.接口文档太多，不好管理

### 开发步骤

**1.添加swagger2依赖**

```xml
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger2</artifactId>
<version>2.7.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger-ui</artifactId>
<version>2.7.0</version>
</dependency>
```

**2.配置swaggerConfig类**

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${doc.title}")
    private String title;

    @Value("${doc.description}")
    private String descrition;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grit.learning.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(descrition)
                .build();
    }
}
```

当然，上面的title和description，这里是定义在application.properties中

```properties
doc.title=集成swagger测试
doc.description=集成swagger
```

**3.开发指定包下的controller类**

```java
@RestController
@RequestMapping(value = "/helloWorld")
public class HelloWorldController {

    @ApiOperation(value = "hello接口,接口名称", notes = "描述接口的作用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号", required = true, defaultValue = "grit",
                    paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query",
                    dataType = "int")
    })
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(String userAccount, int age) {
        return userAccount + " is " + age + " old ";
    }
}
```

按照上面的模板对应添加自己的即可了。

**4.启动访问**

http://localhost:8999/swagger-ui.html





