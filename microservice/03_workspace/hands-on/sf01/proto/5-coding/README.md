# Setup Protoc Compiler

* Extract all to c: proto3  
* Finally, add C: proto3 bin to your PATH (system environment variable)  
* Restart your computer
* test under a console *protoc --version*

```
cd 03_workspace/hands-on/sf01/proto/5-coding/proto
# python_out
protoc -I./proto
--python_out=./python proto/simple.proto 
# csharp_out
protoc -I./proto --csharp_out=./python proto/simple.proto


--cpp_out=OUT_DIR
--csharp_out=OUT_DIR
--java_out=OUT_DIR
--js_out=OUT_DIR
--objc_out=OUT_DIR
--php_out=OUT_DIR
--python_out=OUT_DIR
--ruby_out=OUT_DIR

```
