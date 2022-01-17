# Protocol buffers basics I
* create your first messages with protocol buffers

## Introduction - first message
### scalar types
**Number**  
* double/ float   -> float(32 bits), double(64 bits)
* int32/64
* unit32/64
* sint32/64
* fixed32/64
* sfixed32/64  

**Boolean: bool**
* true & false

**String: represents an arbitrary length of text**  
* UTF-8 encoded
* 7-bit ASCII text  

**Bytes: represents an sequence of bytes array.**  

eg. create a message *Person* that has: 
* int32(age)
* string(first name)  
* string(last name)
* bytes(small picture)
* bool(profile verified)
* float(height)  
:lollipop: 1-scalar-types.proto  

## tags
* Tags number from 1 to 15 use 1 byte in space, so use them for frequently populated fields  
* Tags numbered from 16 to 2047 use 2 bytes  
* There’s a concept of reserved tag that we’ll see in the advanced lectures  
:lollipop: 2-repeated-fields.proto

## comments  
* //   this is a comment
* /* ... */ this is a multiline comment
:lollipop: 3-comments.proto  

## Default values for fields   
All fields, if not specified or unknown, will take a default value:  
* bool: false  
* number ( int32 , etc..): 0  
* string : empty string  
* bytes : empty bytes  
* enum : first value  
* repeated : empty list

In proto3, there is no required or optional field  
:lollipop: 4-enums.proto  

