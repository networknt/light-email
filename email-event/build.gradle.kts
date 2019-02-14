plugins {
    java
}

dependencies {
    val light4jVersion: String by project
    compile("com.networknt", "service", light4jVersion)
    val avroVersion: String by project
    compile("org.apache.avro", "avro", avroVersion)
}
