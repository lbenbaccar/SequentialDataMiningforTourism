import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Map.Entry;
import algorithms.sequentialpatterns.prefixspan.AlgoPrefixSpan;
import algorithms.sequentialpatterns.spam.AlgoCMSPAM;
import algorithms.sequentialpatterns.spam.AlgoTKS;
import algorithms.sequentialpatterns.spam.PatternTKS;
import datastructures.redblacktree.RedBlackTree;
import algorithms.sequentialpatterns.spade_spam_AGP.AlgoCMSPADE;
import algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator;
import algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator_Qualitative;
import algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator;
import algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator_FatBitmap;
import algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator;
import algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator_Qualitative;
import algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.database.SequenceDatabase;
import algorithms.sequential_rules.rulegrowth.AlgoERMiner;
import algorithms.sequential_rules.rulegrowth.AlgoRULEGROWTH;
import algorithms.sequential_rules.topseqrules_and_tns.AlgoTNS;
import algorithms.sequential_rules.topseqrules_and_tns.Rule;
import algorithms.frequentpatterns.negFIN.AlgoNegFIN;
import patterns.itemset_array_integers_with_count.Itemsets;


public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		IM_NegFIN("‪C:/Users/Asus/Desktop/Russia2.txt", "C:/Users/Asus/Desktop/resultNEG.txt");
		SPM_PrefixSpan("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultPS.txt");
		SPM_CMSpade("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultCMD.txt");
		SPM_CMSpam("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultCMM.txt");
		SRM_ERMiner("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultER.txt");
		SRM_RuleGrowth("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultRG.txt");
		SPM_TKS("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultTKS.txt");
		SRM_TNS("‪C:/Users/Asus/Desktop/Russia.txt", "C:/Users/Asus/Desktop/resultTNS.txt");
	}
	
	public static void SPM_PrefixSpan(String input, String output) throws IOException
	{
		AlgoPrefixSpan algo = new AlgoPrefixSpan(); 				
		double minsup_relative = 0.05; 
		algo.setShowSequenceIdentifiers(false);
		algo.runAlgorithm(input, minsup_relative, output);    
		algo.printStatistics();
	}
	
	public static void SPM_CMSpade(String input, String output) throws IOException
	{
        double support = 0.05;
        boolean keepPatterns = true;
        boolean verbose = false;
        AbstractionCreator abstractionCreator = AbstractionCreator_Qualitative.getInstance();
        boolean dfs=true;
        boolean outputSequenceIdentifiers = false;         
        IdListCreator idListCreator = IdListCreator_FatBitmap.getInstance();
        CandidateGenerator candidateGenerator = CandidateGenerator_Qualitative.getInstance();
        SequenceDatabase sequenceDatabase = new SequenceDatabase(abstractionCreator, idListCreator);
        sequenceDatabase.loadFile(input, support);
        AlgoCMSPADE algo = new AlgoCMSPADE(support,dfs,abstractionCreator);        
        algo.runAlgorithm(sequenceDatabase, candidateGenerator,keepPatterns,verbose,output, outputSequenceIdentifiers);
        System.out.println("Relative Minimum support = "+support);
        System.out.println(algo.getNumberOfFrequentPatterns()+ " frequent patterns.");
        System.out.println(algo.printStatistics());
	}
	
	public static void SPM_CMSpam(String input, String output) throws IOException
	{
		AlgoCMSPAM algo = new AlgoCMSPAM(); 
        boolean outputSequenceIdentifiers = false;
        double support = 0.05;
		algo.runAlgorithm(input, output, support, outputSequenceIdentifiers);    
		algo.printStatistics();
	}
	
	public static void SRM_ERMiner(String input, String output) throws IOException
	{
		double minconf = 0.2;
		AlgoERMiner algo = new AlgoERMiner();		
		double minsup = 0.2;  
		algo.runAlgorithm(minsup, minconf, input, output);
		algo.printStats();
	}
	
	public static void SRM_RuleGrowth(String input, String output) throws IOException
	{
		double minconf = 0.1;		
		double minsup = 0.01;  
		AlgoRULEGROWTH algo = new AlgoRULEGROWTH();
		algo.runAlgorithm(minsup, minconf, input, output);
		algo.printStats();
	}

	public static void SPM_TKS(String input, String output) throws IOException
	{				
		int k=30;
		AlgoTKS algo = new AlgoTKS(); 	
		PriorityQueue<PatternTKS> patterns = algo.runAlgorithm(input, output, k);  
		algo.writeResultTofile(output);   
		algo.printStatistics();

	}

	public static void SRM_TNS(String input, String output) throws IOException
	{
		input.sequence_database_array_integers.SequenceDatabase database = new input.sequence_database_array_integers.SequenceDatabase(); 
		database.loadFile(input);
		int k = 2; 
		double minConf = 0.2; 
		int delta =  0;
		AlgoTNS algo = new AlgoTNS();

		RedBlackTree<Rule> kRules = algo.runAlgorithm(k, database, minConf, delta);
		algo.writeResultTofile(output);  
		algo.printStats();
	}
	
	public static void IM_NegFIN(String input, String output) throws IOException {
		double minsup = (double)1/3; 
		AlgoNegFIN algorithm = new AlgoNegFIN();		
		algorithm.runAlgorithm(input, minsup, output);
		algorithm.printStats();
	}

}
