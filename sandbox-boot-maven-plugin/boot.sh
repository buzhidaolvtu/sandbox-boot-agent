#!/usr/bin/env bash

#sandbox-boot-maven-plugin的逻辑大概就是下面的样子
project=/Users/lvtu/workspace/sandbox-boot-agent

sandbox_home=${project}/jvm-sandbox-fork
loader_home=${project}/sandbox-boot-loader

#打包jvm-sandbox
cd ${sandbox_home}/bin
./sandbox-packages.sh

#打包sandbox-boot-loader
cd ${loader_home}
mvn clean package

#打包sandbox-module
cd ${project}/sandbox-module-example
mvn clean package

#use maven plugin to archive all together
cd ${project}/sandbox-boot-maven-plugin
mvn clean package
#获取原始jvm-sandbox结构
cp -R ${sandbox_home}/target/sandbox target/
#获取sandbox-boot-loader
cp ${loader_home}/target/sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar target/

cd target
jar xvf sandbox-boot-loader-1.0-SNAPSHOT-jar-with-dependencies.jar
if [ -f "sandbox-boot-agent.jar" ]; then
    mv sandbox-boot-agent.jar sandbox-boot-agent.jar.bak
fi
#重新打包成jar，注意后面打包的目录
jar -0cvfm  sandbox-boot-agent.jar META-INF/MANIFEST.MF META-INF net org sandbox