pringboot 就不必多说了吧,这货结合dubbo/thrift做微服务棒棒的. 笔者现在做的这个项目就是基于springboot的一堆服务,大约几十个吧. springboot的配置都在application.propertie里,尽管已经约束好了里面的配置,但还有诸多不便性,所以需要一个统一加载机制,来加载公共配置和个性化配置.

springCloud是有这么一套来着.本文就是参考的org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor 不过找着这个类也是扒拉了好久源代码.

好了,啰嗦了这么多,开始贴代码了.



```

public class LoadThirdEnv implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        //此处可以http方式 到配置服务器拉取一堆公共配置+本项目个性配置的json串,拼到Properties里
        //......省略new Properties的过程
       MutablePropertySources propertySources = environment.getPropertySources();
       //addLast 结合下面的 getOrder() 保证顺序 读者也可以试试其他姿势的加载顺序
        propertySources.addLast(new PropertiesPropertySource("thirdEnv", properties));
    }
    @Override
    public int getOrder() {
        //  +1 保证application.propertie里的内容能覆盖掉本配置文件中默认的
        // 如果不想被覆盖 可以去掉 +1  或者 -1  试试
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
    }
}

```

对,没错,就是这么几行. 还不够,还需要在resources/META-INF/spring.factories里添加

```
org.springframework.boot.env.EnvironmentPostProcessor=\
 com.ilaotan.LoadThirdEnv
```

好了,完工. springboot的配置文件参数,可以精简到几个. 其他都从http加载.以后需要改参数,直接配置平台改完,重启这个springboot服务即可.

spring.factories 是springboot的一种加载机制.这里不多赘述.

至于http请求的配置平台,实现得很简单,就是发不同的参数,那边返回json串.


---

本文转自https://my.oschina.net/ilaotan/blog/846456
