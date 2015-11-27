package ch.nihongo.vokabeltrainer.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Niko Reichardt
 */
@ManagedBean
@RequestScoped
public class ChartView implements Serializable {

    private static final long serialVersionUID = 8824801590319201120L;
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;

    @ManagedProperty(value = "#{vocabularyStatistic}")
    private VocabularyStatistic statistics;

    @PostConstruct
    public void init() {
        createPieModel1();
        createPieModel2();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public void setStatistics(VocabularyStatistic statistics) {
        this.statistics = statistics;
    }

    private void createPieModel1() {
        int notLearned = statistics.getGermanWordSize() - statistics.getGermanWrongSize() - statistics.getGermanCorrectSize() - statistics.getGermanNotknownSize();
        pieModel1 = new PieChartModel();

        pieModel1.set("Richtig", statistics.getGermanCorrectSize());
        pieModel1.set("Falsch", statistics.getGermanWrongSize());
        pieModel1.set("Nicht gewusst", statistics.getGermanNotknownSize());
        pieModel1.set("Noch nicht gelernt", notLearned);
        pieModel1.setShowDataLabels(true);
        pieModel1.setShadow(false);
        pieModel1.setSeriesColors("12DE14,F00,FEA015,6CB4FF");
        
        pieModel1.setTitle("Deutsch");
        pieModel1.setLegendPosition("w");
    }
    
    private void createPieModel2() {
        int notLearned = statistics.getJapaneseWordSize() - statistics.getJapaneseWrongSize() - statistics.getJapaneseCorrectSize() - statistics.getJapaneseNotknownSize();
        pieModel2 = new PieChartModel();

        pieModel2.set("Richtig", statistics.getJapaneseCorrectSize());
        pieModel2.set("Falsch", statistics.getJapaneseWrongSize());
        pieModel2.set("Nicht gewusst", statistics.getJapaneseNotknownSize());
        pieModel2.set("Noch nicht gelernt", notLearned);
        pieModel2.setShowDataLabels(true);
        pieModel2.setShadow(false);
        pieModel2.setSeriesColors("12DE14,F00,FEA015,6CB4FF");
        
        pieModel2.setTitle("Japanisch");
        pieModel2.setLegendPosition("w");
    }

}
