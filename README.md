# sandbox-boot-agent
```text
目标：怎样把下面的格式转换成agent可以使用的java.util.jar.JarFile格式
path/path/xxx.jar!/path/path/yyy.jar!/  => JarFile
分解为
path/path/xxx.jar:outer jar archive
/path/path/yyy.jar: nested jar archive : including .class file

实现方式是把outer jar archive解压为目录

-----------------------------

jar archive format:
codeSourcePath:file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox/lib/sandbox-agent.jar!/,
parentFile:file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox/lib,
parent:file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox

sandboxHome for sandbox.properties: file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox
sandboxHome for cfg: file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox
sandboxHome for cfg: file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox
sandboxHome for module: file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox
sandboxHome for provider : file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox
sandboxHome for sandbox-spy.jar: file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox

Caused by: java.io.FileNotFoundException:
file:/Users/lvtu/workspace/sandbox-boot-agent/target/sandbox-boot-agent.jar!/sandbox/lib/sandbox-spy.jar (No such file or directory)

-----------------------------
original agent:
-javaagent:/Users/lvtu/workspace/sandbox-boot-agent/sandbox/lib/sandbox-agent.jar

original path:
codeSourcePath:/Users/lvtu/workspace/sandbox-boot-agent/sandbox/lib/sandbox-agent.jar,
parentFile:/Users/lvtu/workspace/sandbox-boot-agent/sandbox/lib,
parent:/Users/lvtu/workspace/sandbox-boot-agent/sandbox

sandboxHome for sandbox.properties: /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for cfg: /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for cfg: /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for module: /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for provider : /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for sandbox-spy.jar: /Users/lvtu/workspace/sandbox-boot-agent/sandbox
sandboxHome for sandbox-core.jar: /Users/lvtu/workspace/sandbox-boot-agent/sandbox


试验结果：
codeSourcePath:/var/folders/zm/dbtvgy65019dw_f_6dtqt4mc0000gn/T/util4j-unzip5453804723839526918/sandbox/lib/sandbox-agent.jar,
parentFile:/var/folders/zm/dbtvgy65019dw_f_6dtqt4mc0000gn/T/util4j-unzip5453804723839526918/sandbox/lib,
parent:/var/folders/zm/dbtvgy65019dw_f_6dtqt4mc0000gn/T/util4j-unzip5453804723839526918/sandbox

sandbox-boot-agent.jar
	sandbox
		lib
			sandbox-agent.jar
			sandbox-core.jar
			sandbox-spy.jar
```
