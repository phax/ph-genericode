# ph-genericode

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.helger/ph-genericode/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.helger/ph-genericode) 
[![javadoc](https://javadoc.io/badge2/com.helger/ph-genericode/javadoc.svg)](https://javadoc.io/doc/com.helger/ph-genericode)
[![CodeCov](https://codecov.io/gh/phax/ph-genericode/branch/master/graph/badge.svg)](https://codecov.io/gh/phax/ph-genericode)

Java library for reading and writing OASIS GeneriCode and CVA files.
It supports GeneriCode 0.4 and GeneriCode 1.0 as well as CVA 1.0.

# Usage with Maven

```xml
<dependency>
  <groupId>com.helger</groupId>
  <artifactId>ph-genericode</artifactId>
  <version>x.y.z</version>
</dependency>
```

Technically this library requires [ph-commons](https://github.com/phax/ph-commons) and optionally [ph-poi](https://github.com/phax/ph-poi) for Excel file handling.

# Gradle considerations

This project relies on JDK version based Maven profile activation.
See https://github.com/phax/ph-jaxb-pom#gradle-usage for help on this specific issue. 

# Examples

[Example code for reading GeneriCode v0.4 CodeList files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/genericode/Genericode04CodeListMarshallerTest.java) is available.

[Example code for reading GeneriCode v1.0 CodeList files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/genericode/Genericode10CodeListMarshallerTest.java) is available.

[Example code for reading CVA files](https://github.com/phax/ph-genericode/blob/master/src/test/java/com/helger/cva/CVA10MarshallerTest.java) is available.

# News and noteworthy

* v7.1.2 - work in progress
    * Switched JAXB Maven plugin to `org.jvnet.jaxb:jaxb-maven-plugin` 
* v7.1.1 - 2023-07-31
    * Updated to ph-commons 11.1
* v7.1.0 - 2023-04-22
    * Moved the XML Schema to folder `external/...`
    * Deprecated classes `Genericode(Reader|Validator|Writer)` in favour of the explicit marshallers
    * Using the `xml.xsd` from `ph-xsds-xml` instead of a local one
* v7.0.0 - 2023-01-08
    * Using Java 11 as the baseline
    * Updated to ph-commons 11
    * Using JAXB 4.0 as the baseline
* v6.3.0 - 2021-05-02
    * Updated to ph-commons 10.1
* v6.2.0 - 2021-03-21
    * Updated to ph-commons 10
* v6.1.1 - 2020-09-17
    * Updated to Jakarta JAXB 2.3.3
* v6.1.0 - 2018-11-22
    * Added namespace URI constants in the Cxxx files.
    * Updated to ph-commons 9.2.0
* v6.0.0 - 2017-11-07
    * Updated to ph-commons 9.0.0
* v5.0.1 - 2017-01-20
    * Added new package `com.helger.genericode.builder` with more flexible reader/writer/validator
    * Binds to ph-commons 8.6.x
* v5.0.0 - 2016-06-11
    * Requires JDK 8
    * Binds to ph-commons 8.x
* v4.1.1 - 2015-07-21
    * Last version with JDK 6 support
* v4.1.0 - 2015-07-21   
* v4.0.0 - 2015-07-09
    * Binds to ph-commons 6.x
* v3.3.1 - 2015-03-12
    * Binds to ph-commons 5.x
* v3.3.0 - 2014-08-25

---

My personal [Coding Styleguide](https://github.com/phax/meta/blob/master/CodingStyleguide.md) |
It is appreciated if you star the GitHub project if you like it.