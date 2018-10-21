# sandbox-boot-agent
```text
xxx.jar
xxx/
xxx.jar!xxx/
xxx.jar!xxx.jar
xxx.jar!/xxx.jar!/xxx/

xxx.jar!/sandbox/lib/sandbox-agent.jar!/s  => JarFile
xxx.jar!/sandbox/lib/sandbox-core.jar  => JarFile


CodeSource:        /Users/lvtu/workspace/util4j/target/classes

JarFileArchive    jar:file:/Users/lvtu/workspace/util4j/target/boot.jar!/util4j-1.0-SNAPSHOT.jar!/
ExplodedArchive   jar:file:/var/folders/zm/dbtvgy65019dw_f_6dtqt4mc0000gn/T/util4j-unzip7453092161092096601/util4j-1.0-SNAPSHOT.jar!/

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
