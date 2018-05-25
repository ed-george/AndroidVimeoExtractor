[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidVimeoExtractor-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3416) [![](https://jitpack.io/v/ed-george/AndroidVimeoExtractor.svg)](https://jitpack.io/#ed-george/AndroidVimeoExtractor) [![GitHub issues](https://img.shields.io/github/issues/ed-george/AndroidVimeoExtractor.svg)](https://github.com/ed-george/AndroidVimeoExtractor/issues) [![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/ed-george/AndroidVimeoExtractor/master/LICENSE)


*Inspired by [YTVimeoExtractor](https://github.com/lilfaf/YTVimeoExtractor) for iOS.*

### Add to project 

To use this library, simply add JitPack as a repository source in your `build.gradle` at the end of the repositories section:

```groovy
repositories {
   // ...
   maven { url "https://jitpack.io" }
}
```

Step 2: Add the dependency to your dependency list and replace ending with latest version

```groovy
dependencies {
    // ...
    compile 'com.github.ed-george:AndroidVimeoExtractor:<latest-version>'
}
```

That's it!
NOTE: To get the latest version visit [this link](https://jitpack.io/#ed-george/AndroidVimeoExtractor)

### Usage

**Requires Minimum SDK 9 - Android 2.3**

Video information can be extracted from an identifier:

```java
VimeoExtractor.getInstance().fetchVideoWithIdentifier("1234", null, new OnVimeoExtractionListener() {
            @Override
            public void onSuccess(VimeoVideo video) {
                String hdStream = video.getStreams().get("1080p");
                //...
            }

            @Override
            public void onFailure(Throwable throwable) {
                //Error handling here
            }
});
```

Or alternatively from a full video url:

```java
VimeoExtractor.getInstance().fetchVideoWithURL("https://vimeo.com/1234", null, new OnVimeoExtractionListener() {
            @Override
            public void onSuccess(VimeoVideo video) {
                String hdStream = video.getStreams().get("1080p");
                //...
            }

            @Override
            public void onFailure(Throwable throwable) {
                //Error handling here
            }
});
```
Please build and read the documentation for a better oversight of the information available from each video.

#### Private Videos

When requesting information on private videos - pass the referrer parameter as `null`, this will ensure the default header is sent and should avoid any HTTP 403 Forbidden errors.

### License


    The MIT License (MIT)
    
    Copyright (c) 2016 Ed George
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
    
#### Finally...

This library is in no way associated with Vimeo. Please only use this library at your own risk after accepting and acknowledging the [Vimeo Terms of Service](https://vimeo.com/terms).


## Support on Beerpay
Hey dude! Help me out for a couple of :beers:!

[![Beerpay](https://beerpay.io/ed-george/AndroidVimeoExtractor/badge.svg?style=beer-square)](https://beerpay.io/ed-george/AndroidVimeoExtractor)  [![Beerpay](https://beerpay.io/ed-george/AndroidVimeoExtractor/make-wish.svg?style=flat-square)](https://beerpay.io/ed-george/AndroidVimeoExtractor?focus=wish)