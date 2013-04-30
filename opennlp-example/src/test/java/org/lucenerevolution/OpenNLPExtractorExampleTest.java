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

import opennlp.tools.util.Span;
import org.junit.Test;

import java.io.IOException;

public class OpenNLPExtractorExampleTest {

    private static EnglishExtractorExample extractor;

    static {
        try {
            extractor = new EnglishExtractorExample();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEnglishSentences() {

        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
        String document =
            "Don’t write off Microsoft’s chances in mobile just yet. It may still be struggling to make itself count in the smartphone space but early signs are more promising for Windows plus tablets. Microsoft has gone from having no share of the global tablet OS market in Q1 last year to taking 7.4% one year later, with three million Windows 8 tablets shipped in Q1 2013, according to preliminary figures from Strategy Analytics‘ Global Tablet OS Market Share: Q1 2013 report.";

        for (String sentence : extractor.segmentSentences(document)) {
            System.out.println("sentence: " + sentence);
        }
    }

    @Test
    public void testEnglishSegmentation() {
        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
        String document =
            "Don’t write off Microsoft’s chances in mobile just yet. It may still be struggling to make itself count in the smartphone space but early signs are more promising for Windows plus tablets. Microsoft has gone from having no share of the global tablet OS market in Q1 last year to taking 7.4% one year later, with three million Windows 8 tablets shipped in Q1 2013, according to preliminary figures from Strategy Analytics‘ Global Tablet OS Market Share: Q1 2013 report.";

        for (String sentence : extractor.segmentSentences(document)) {
            System.out.println("sentence: " + sentence);

            for (String token : extractor.tokenizeSentence(sentence)) {
                System.out.println("\t" + token);
            }
        }
    }

    @Test
    public void testEnglishNames() {
        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
        String document =
            "Microsoft, founded in 1975 by Bill Gates and Paul Allen, is a veteran software company, best known for its Microsoft Windows operating system and the Microsoft Office suite of productivity software.";

        for (String sentence : extractor.segmentSentences(document)) {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            Span[] spans = extractor.findNames(tokens);

            for (Span span : spans) {

                System.out.print("person: ");

                for (int i = span.getStart(); i < span.getEnd(); i++) {
                    System.out.print(tokens[i]);
                    if (i < span.getEnd()) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    @Test
    public void testEnglishPartOfSpeech() {
        // Text from http://techcrunch.com/2013/04/25/strategy-analytics-q1-tablet-stats/ (© 2013 AOL Inc.)
        String document =
            "Microsoft, founded in 1975 by Bill Gates and Paul Allen, is a veteran software company, best known for its Microsoft Windows operating system and the Microsoft Office suite of productivity software.";

        for (String sentence : extractor.segmentSentences(document)) {
            System.out.println("sentence: " + sentence);

            String[] tokens = extractor.tokenizeSentence(sentence);

            String[] tags = extractor.tagPartOfSpeech(tokens);
            double[] probs = extractor.getPartOfSpeechProbabilities();

            for (int i = 0; i < tokens.length; i++) {
                System.out.print("token: " + tokens[i]);
                System.out.print("\t");
                System.out.print("pos: " + tags[i]);
                System.out.print("\t");
                System.out.print("probability: " + probs[i]);
                System.out.println();
            }
        }
    }
}
