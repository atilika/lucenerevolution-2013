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

import org.apache.lucene.analysis.ar.ArabicAnalyzer;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.ja.JapaneseAnalyzer;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.IOException;

public class AnalyzerExampleTest {

    private static AnalyzerPrinter printer = new AnalyzerPrinter();

    @Test
    public void testEnglishAnalysis() throws IOException {
        String english = "Pale ale is a beer made through warm fermentation using pale malt and is one of the world's major beer styles.";
        printer.printTerms(new EnglishAnalyzer(Version.LUCENE_42), english);
    }

    @Test
    public void testGermanAnalysis() throws IOException {
        String german = "Das Oktoberfest ist das größte Volksfest der Welt und es findet in der bayerischen Landeshauptstadt München.";
        printer.printTerms(new GermanAnalyzer(Version.LUCENE_42), german);
    }

    @Test
    public void testFrenchAnalysis() throws IOException {
        String french = "Le champagne est protégé par une appellation d'origine contrôlée.";
        printer.printTerms(new FrenchAnalyzer(Version.LUCENE_42), french);
    }

    @Test
    public void testArabicAnalysis() throws IOException {
        String arabic = "تُعْتَبَر القَهْوَة العَرَبِيَّه الأصِـــــــيْلَة رَمْزًا مِن رُمُوْز الكَرَم عِنْد العَرَبْ فِى العَالمَ العَرَبِي.";
        printer.printTerms(new ArabicAnalyzer(Version.LUCENE_42), arabic);
    }

    @Test
    public void testJapaneseAnalysis() throws IOException {
        String japanese = "ＪＲ新宿駅の近くにビールを飲みに行こうか？";
        printer.printTerms(new JapaneseAnalyzer(Version.LUCENE_42), japanese);
    }
}