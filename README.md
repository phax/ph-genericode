#ph-genericode

Java library for reading and writing OASIS GeneriCode and CVA files.

#Usage with Maven
```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>ph-genericode</artifactId>
  <version>3.3.0</version>
</dependency>
```

Technically this library requires [ph-commons](https://github.com/phax/ph-commons) and optionally [ph-poi](https://github.com/phax/ph-poi) for Excel file handling.

#Examples

[Example code for reading GeneriCode v0.4 CodeList files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/genericode/Genericode04CodeListMarshallerTest.java) is available.

[Example code for reading GeneriCode v1.0 CodeList files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/genericode/Genericode10CodeListMarshallerTest.java) is available.

[Example code for reading CVA files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/cva/CVA10MarshallerTest.java) is available.
