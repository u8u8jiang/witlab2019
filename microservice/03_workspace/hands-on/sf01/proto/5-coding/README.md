# Setup Protoc Compiler

* Extract all to c: proto3  
* Finally, add C: proto3 bin to your PATH (system environment variable)  
* Restart your computer
* test under a console *protoc --version*

```
cd 03_workspace/hands-on/sf01/proto/5-coding/proto
protoc -I./proto --python_out=./python proto/simple.proto
```
