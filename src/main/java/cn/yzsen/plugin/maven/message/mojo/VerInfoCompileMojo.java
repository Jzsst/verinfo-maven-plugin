package cn.yzsen.plugin.maven.message.mojo;

import cn.yzsen.plugin.maven.message.util.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * @author yzsen
 * @classname: VerInfoCompileMojo
 * @title: version information
 * @description: 核心功能实现
 * @date: 2021/12/30 15:34
 * @copyright 2021 www.yzsen.cn Inc. All rights reserved.
 **/
@Mojo(name = "VerInfoCompileMojo", defaultPhase = LifecyclePhase.COMPILE)
public class VerInfoCompileMojo extends AbstractMojo {

    public static final String FILE_NAME = "release-timestamp.txt";

    /**
     * 初始化 mojo.
     */
    public VerInfoCompileMojo() {
        enable = true;
        fileName = FILE_NAME;
        time = DateUtil.formatDateWithMillisecond(new Date());
    }


    @Parameter
    private boolean enable;

    @Parameter
    private String projectPath;

    @Parameter
    private String projectVersion;

    @Parameter
    private String author;

    @Parameter
    private String ext;

    @Parameter
    private String fileName;

    @Parameter
    private String time;

    @Parameter
    private String staticFile;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (enable) {

            //Initialize judgment data
            List<String> stringList = new LinkedList<>();
            List<String> untitledStringList = new LinkedList<>();
            List<String> filterList = new ArrayList<String>() {{
                add("projectPath");
                add("enable");
                add("FILE_NAME");
                add("staticFile");
                if (fileName.equalsIgnoreCase(FILE_NAME)) {
                    add("fileName");
                }
            }};
            List<String> untitledList = new ArrayList<String>() {{
                add("ext");
            }};

            Class clazz = VerInfoCompileMojo.class;
            Field[] allFields = clazz.getDeclaredFields();
            for (Field field : allFields) {

                field.setAccessible(true);
                try {
                    if (ObjectUtils.allNotNull(field.get(this))) {
                        if (StringUtils.isNotBlank(field.get(this).toString()) && !filterList.contains(field.getName())) {
                            String element = field.getName() + " is : " + field.get(this);
                            this.getLog().info(element);
                            if (!untitledList.contains(field.getName())) {
                                stringList.add(element);
                            } else {
                                untitledStringList.add(field.get(this).toString());
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //读取额外文件
            if (StringUtils.isNotBlank(staticFile)) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(projectPath + staticFile),
                            StandardCharsets.UTF_8);
                    untitledStringList.add("\n");
                    untitledStringList.addAll(lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            File releaseFileDir = new File(projectPath + "/target/classes/");
            if (!releaseFileDir.exists()) {
                releaseFileDir.mkdirs();
            }
            File checkFile = new File(releaseFileDir.getAbsolutePath() + "/" + fileName);
            FileWriter writer = null;
            try {
                if (!checkFile.exists()) {
                    checkFile.createNewFile();
                }
                writer = new FileWriter(checkFile, true);
                writer.append(String.join("\n", stringList)).append("\n").append(String.join("\n", untitledStringList));
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != writer) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
