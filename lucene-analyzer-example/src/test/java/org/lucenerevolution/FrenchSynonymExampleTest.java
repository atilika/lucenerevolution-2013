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

import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

public class FrenchSynonymExampleTest {

    private static AnalyzerPrinter analyzerPrinter = new AnalyzerPrinter();

    @Test
    public void testFrenchSynonyms() throws IOException, ParseException {

        String synonyms =
            "# This is a comment and empty lines are ignored)\n" +
            "\n" +
            "appellation d'origine contrôlée, doc\n" +  // equivalence
            "sparkling wine => champagne";              // mapping

        FrenchSynonymAnalyzer frenchSynonymAnalyzer = new FrenchSynonymAnalyzer(
            Version.LUCENE_42, new StringReader(synonyms));

        String french = "Le champagne est protégé par une appellation d'origine contrôlée.";

        analyzerPrinter.printTokenDetails(frenchSynonymAnalyzer, french);
    }
}
