# 版本信息打印maven插件

## 开发目的

用于标记版本信息

## 核心功能

生成指定数据到指定文件

指定数据：用户信息、项目版本、拓展信息、文件导入

## 使用介绍

```xml

<!--全量配置-->
<build>
    <plugins>
        <plugin>
            <groupId>cn.yzsen.plugin.maven</groupId>
            <artifactId>verinfo-maven-plugin</artifactId>
            <version>1.0.0</version>
            <configuration>
                <!--指定项目版本，一般不做修改-->
                <projectVersion>${project.version}</projectVersion>
                <!--指定项目地址，一般不做修改-->
                <projectPath>${project.basedir}</projectPath>
                
                
                <!--指定用户信息，根据需求修改-->
                <author>[${user.country}]${user.name}</author>
                <!--指定时间信息，根据需求修改-->
                <time>2021-12-31T11:17:40+08:00</time>
                <!--指定输出文件名，根据需求修改-->
                <fileName>release-timestamp.txt</fileName>
                <!--指定需要展示的自定义信息，根据需求修改-->
                <ext>${project.artifactId}</ext>
                <!--指定需要展示的自定义文件路径，根据需求修改-->
                <staticFile>/src/main/resources/banner.txt</staticFile>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>VerInfoCompileMojo</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
```xml
<!--个人配置-->
<plugin>
    <groupId>cn.yzsen.plugin.maven</groupId>
    <artifactId>verinfo-maven-plugin</artifactId>
    <version>1.0.0</version>
    <configuration>
        <projectVersion>${project.version}</projectVersion>
        <author>[${user.country}]${user.name}</author>
        <projectPath>${project.basedir}</projectPath>
        <staticFile>/src/main/resources/banner.txt</staticFile>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>VerInfoCompileMojo</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
## 几句废话
**开发之初只是为了解决部署后不清楚打包时间和版本信息。**

对于插件本身考虑没有太深入，只是满足了目前的基本需求，**issues安排！**

## 开源协议
[MIT](https://gitee.com/jzsst/verinfo-maven-plugin/blob/master/LICENSE)

## 贡献名单
