Lucene Assertions
=================

A small library to help test lucene-based applications, extending [Assert4j](http://joel-costigliola.github.io/assertj/index.html).

Usage
---

QueryAssert:
```java
import com.github.flaxsearch.luceneassert.LuceneAssertions.*;
...
Document doc = createDocument();
Analyzer defaultAnalyzer = new StandardAnalyzer();

Query q = new TermQuery(new Term("field", "term"));
assertThat(q).matches(doc, defaultAnalyzer);
Query q2 = new TermQuery(new Term("field", "nomatch"));
assertThat(q2).doesNotMatch(doc, defaultAnalyzer);
```

TokenStreamAssert:
```java
TokenStream ts = myCustomAnalyzer.tokenStream("field", "some text");
assertThat(ts).nextEquals("some", 0)
              .nextEquals("text", 1)
              .isExhausted();
```