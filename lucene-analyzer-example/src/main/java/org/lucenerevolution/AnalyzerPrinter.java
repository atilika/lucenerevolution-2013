/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lucenerevolution;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerPrinter {

    public void printTerms(Analyzer analyzer, String text) throws IOException {
        // Create token stream from reader
        TokenStream stream = analyzer.tokenStream("dummyField", new StringReader(text));

        // Reset stream before token consumption
        stream.reset();

        // Attribute to get the term text for a token
        CharTermAttribute termAttr = stream.addAttribute(CharTermAttribute.class);

        // Output source text
        System.out.println("\ntext: " + text);

        // Analyze text and iterate until end of input
        while (stream.incrementToken()) {
            // Output term text
            System.out.println("  term: " + termAttr);
        }
    }

    public void printTokenDetails(Analyzer analyzer, String text) throws IOException {
        // Create token stream from reader
        TokenStream stream = analyzer.tokenStream("dummy", new StringReader(text));

        // Reset stream before token consumption
        stream.reset();

        // Attribute to get the token term text, offset and type
        CharTermAttribute termAttr = stream.addAttribute(CharTermAttribute.class);
        OffsetAttribute offsetAttr = stream.addAttribute(OffsetAttribute.class);
        TypeAttribute typeAttr = stream.addAttribute(TypeAttribute.class);

        // Output source text
        System.out.println("text: " + text);

        // Analyze text and iterate until end of input
        while (stream.incrementToken()) {
            // Output term text, type and offset
            System.out.println(
                "term: " + termAttr +
                "\ttype: " + typeAttr.type() +
                "\tstart offset: " + offsetAttr.startOffset() +
                "\tend offset: " + offsetAttr.endOffset()
            );
        }
    }
}